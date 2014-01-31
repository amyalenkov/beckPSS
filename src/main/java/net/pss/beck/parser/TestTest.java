package net.pss.beck.parser;

import net.pss.beck.domain.Player;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by amyalenkov on 04.01.14.
 */
public class TestTest {

    @Test
    public void testSoccerWay() throws IOException {
        String country = "Belarus";
        String url = "http://int.soccerway.com";
        String urlAdd = "";
        Document doc = Jsoup.connect("http://int.soccerway.com/competitions/?ICID=TN_03").get();
        Elements links = doc.getElementsByClass("expandable").tagName("a");
        for(Element link : links) {
//            System.out.println(link.text());
            if(country.equals(link.text())){
                urlAdd = link.getElementsByAttribute("href").attr("href");
                System.out.println("url add "+urlAdd);
            }
        }

        Document doc1 = Jsoup.connect(url+urlAdd).get();
        Elements links1 = doc1.getElementsByClass("left-tree").tagName("a");
        for(Element link : links1) {
            for(Element elem : link.getElementsByAttribute("title")) {
                System.out.println(elem.tagName("a").text());
            }
        }
    }

    @Test
    public void testTransferMarket() throws IOException {
        String url_de = "http://www.transfermarkt.de";
        String url_en = "http://www.transfermarkt.de/en";
        String euroCamps = "/europa/europa/wettbewerbe.html";
        String nameTour = "Wyschejschaja Liha";
        String team = "FK Gomel";
        String nameTourUrl = "";
        String teamUrl = "";
        Document doc = getDoc(url_en+euroCamps);
        Elements links = doc.getElementsByClass("al").tagName("a");
        for(Element link : links) {
            if(link.text().equals(nameTour)){
                nameTourUrl = link.getElementsByAttribute("href").attr("href");
            }
        }

        Document doc1 = getDoc(url_de+nameTourUrl);
        Elements links1 = doc1.getElementsByAttributeValue("id","vereine").get(0).getElementsByAttributeValue("class","s10");
        for(Element link : links1){
            //todo убрать последний элемент, это не команда
//            System.out.println(link.attr("title"));
            if(link.attr("title").equals(team)){
                teamUrl = link.attr("href");
            }
        }

        Document doc3 = getDoc(url_de+teamUrl);
        Elements links4 = doc3.getElementsByAttributeValue("id","spieler").get(0).getElementsByAttributeValue("class","hell");
        Elements links5 = doc3.getElementsByAttributeValue("id","spieler").get(0).getElementsByAttributeValue("class","dunkel");
        for(Element element : links4){
            System.out.println(element.getElementsByAttribute("src").get(0).attr("title"));
            System.out.println(element.getElementsByAttribute("href").get(0).attr("title"));
            getPlayer(url_de+element.getElementsByAttribute("href").get(0).attr("href"));
            System.out.println("--------");
        }
        for(Element element : links5){
            System.out.println(element.getElementsByAttribute("src").get(0).attr("title"));
            System.out.println(element.getElementsByAttribute("href").get(0).attr("title"));
            getPlayer(url_de+element.getElementsByAttribute("href").get(0).attr("href"));
            System.out.println("--------");
        }
    }

    public void getPlayer(String urlPlayer) throws IOException {
        Document doc = getDoc(urlPlayer);
        Elements links = doc.getElementsByAttributeValue("class","tabelle_spieler s10");
        for(Element link : links) {
            //todo обработка по дате
            System.out.println(link.getElementsByAttributeValue("class","s10").text());
            System.out.println(link.getElementsByAttributeValue("class", "country-name s10").text());
        }
    }

    public Document getDoc(String url) throws IOException {
        return Jsoup.connect(url).
                userAgent("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36")
                .timeout(0).followRedirects(true).execute().parse();
    }

    @Test
    public void testHelpParserTeams() throws IOException {
        String nameTour = "Wyschejschaja Liha";
        ParserHelper parserHelper = new ParserHelper();
        parserHelper.getAllTeamsInTour(nameTour);
    }

    @Test
    public void testHelpParserPlayers() throws IOException {
        String nameTour = "Wyschejschaja Liha";
        String nameTeam = "BATE Borisov";
        ParserHelper parserHelper = new ParserHelper();
        for(Player player : parserHelper.getPlayersOfTeam(nameTeam,nameTour)){
            System.out.println(player.getCounrty());
            System.out.println(player.getData());
            System.out.println(player.getFirstName());
            System.out.println(player.getLastName());
        }
    }

}
