package net.pss.beck.parser;

import net.pss.beck.domain.Player;
import net.pss.beck.domain.Referee;
import net.pss.beck.domain.Team;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amyalenkov on 06.01.14.
 */
public class ParserHelper {

    private static final String URL = "http://www.transfermarkt.de";
    private static final String URL_EURO_CHAMPS =
            "http://www.transfermarkt.de/en/europa/europa/wettbewerbe.html";

    Map<String, Player> numberPlayer = new HashMap<String, Player>();

    public Map<String, Player> getNumberPlayer() {
        return numberPlayer;
    }

    public List<MatchParser> getMatches(String tournamentName) throws IOException {
        Document doc = getDoc(URL + getUrlTournamentName(tournamentName));
        String urlMatches = null;
        for (Element element : doc.getElementsByClass("navipoint_sub_first")) {
            if (element.text().equals("Season Schedule")) {
                urlMatches = element.attr("href");
            }
        }
        List<MatchParser> matchParserList = new ArrayList<MatchParser>();
        Document matches = getDoc(URL + urlMatches);
        matchParserList.addAll(getMatch("fl",matches));
        matchParserList.addAll(getMatch("fr",matches));
        return matchParserList;

    }

    private List<MatchParser> getMatch(String tagClassName, Element matches){
        List<MatchParser> matchParserList = new ArrayList<MatchParser>();
        for (Element element : matches.getElementsByClass(tagClassName)) {
            Elements elements = element.getElementsByClass("standard_tabelle");
            if (elements.size() != 0) {
                String stage = "";
                for (Element element1 : element.getElementsByTag("p").attr("class", "hl_startseite mt10")) {
                    stage = element1.text();
                }
                for (Element element1 : elements) {
                    for (Element element2 : element1.getElementsByAttributeValue("class","hell lhg")) {
                        MatchParser matchParser = new MatchParser();

                        for (Element element3 : element2.getElementsByAttributeValue("class", "wid10 s10 wsnw ch")) {
                            matchParser.setData(element3.attr("title"));
                        }
                        matchParser.setStage(stage);
                        Elements elements2 = element2.getElementsByAttributeValue("class","ar");
                        matchParser.setTeam1(elements2.get(0).getElementsByTag("a").get(0).attr("title"));
                        matchParser.setTeam2(element2.getElementsByAttributeValue("class", "al").get(0).
                                getElementsByTag("a").get(0).attr("title"));
                        matchParser.setRefereeLink(elements2.get(1).getElementsByTag("a").get(0).attr("href"));
                        matchParserList.add(matchParser);
                    }
                }
            }
        }
        return matchParserList;
    }

    public Referee getReferee(String matchUlr) throws IOException {
        Referee referee = new Referee();
        Document match = getDoc(URL + matchUlr);
        String name = match.getElementsByAttributeValue("class","s10 fff tdu").get(4).attr("title");
        referee.setFirstName(name.replaceAll("\\s\\w+", ""));
        referee.setLastName(name.replaceAll("\\w+\\s", ""));
        String refereeUrl = match.getElementsByAttributeValue("class","s10 fff tdu").get(4).attr("href");
        Document refereeDoc = getDoc(URL + refereeUrl);
        for(Element element : refereeDoc.getElementsByAttributeValue("class","tabelle_spieler")){
            String data = element.getElementsByAttributeValue("class","s10").get(1).text();
            String country = element.getElementsByAttributeValue("class","flaggen_sprite sprite_land_18 vt mt4").get(0).attr("title");
            referee.setData(data.replaceAll("\\D+",""));
            referee.setCounrty(country);
        }
        return referee;
    }

    public List<Team> getAllTeamsInTour(String tournamentName) throws IOException {
        List<Team> teams = new ArrayList<Team>();
        Team team;
        Document doc = getDoc(URL + getUrlTournamentName(tournamentName));
        Elements country = doc.getElementsByAttributeValue("class", "s18 tdn");
        String countryName = country.get(0).attr("title");

        Elements links1 = doc.getElementsByAttributeValue("id", "vereine").get(0).
                getElementsByAttributeValue("class", "s10");
        //выбираем команды без последнего элемента
        for (int i = 0; i < links1.size() - 1; i++) {
//            System.out.println(links1.get(i).attr("title"));
            team = new Team();
            team.setCounrty(countryName);
            team.setName(links1.get(i).attr("title"));
            teams.add(team);
        }
        return teams;
    }

    public List<Player> getPlayersOfTeam(String teamName, String tournamentName) throws IOException {
        List<Player> players = new ArrayList<Player>();
        Player player;
        Document doc = getDoc(URL + getTeamUrl(teamName, tournamentName));
        Elements links1 = doc.getElementsByAttributeValue("id", "spieler").get(0).getElementsByAttributeValue("class", "hell");
        Elements links2 = doc.getElementsByAttributeValue("id", "spieler").get(0).getElementsByAttributeValue("class", "dunkel");
        for (Element element : links1) {
            String firstLastName = element.getElementsByAttribute("href").get(0).attr("title");
            String firstName = firstLastName.replaceAll("\\s\\D+", "");
            String lastName = firstLastName.replaceAll("\\D+\\s", "");
            String number = element.getElementsByAttribute("src").get(0).attr("title");
            player = new Player();
            player.setFirstName(firstName);
            player.setLastName(lastName);
            getPlayer(player, URL + element.getElementsByAttribute("href").get(0).attr("href"));
            players.add(player);
            numberPlayer.put(number.replaceAll("\\D+", ""), player);

        }
        for (Element element : links2) {
            String firstLastName = element.getElementsByAttribute("href").get(0).attr("title");
            String firstName = firstLastName.replaceAll("\\s\\D+", "");
            String lastName = firstLastName.replaceAll("\\D+\\s", "");
            String number = element.getElementsByAttribute("src").get(0).attr("title");
            player = new Player();
            player.setFirstName(firstName);
            player.setLastName(lastName);
            getPlayer(player, URL + element.getElementsByAttribute("href").get(0).attr("href"));
            players.add(player);
            numberPlayer.put(number.replaceAll("\\D+", ""), player);

        }
        return players;
    }

    private void getPlayer(Player player, String urlPlayer) throws IOException {
        Document doc = getDoc(urlPlayer);
        Elements links = doc.getElementsByAttributeValue("class", "tabelle_spieler s10");
        for (Element link : links) {
            String country = link.getElementsByAttributeValue("class", "country-name s10").text();
            String data = link.getElementsByAttributeValue("class", "s10").text();
            player.setData(data.replaceAll("\\D", ""));
            player.setCounrty(country);
        }
    }

    private String getTeamUrl(String teamName, String tournamentName) throws IOException {
        String teamUrl = "";
        String tourName = getUrlTournamentName(tournamentName);
        Document doc1 = getDoc(URL + tourName);
        Elements links1 = doc1.getElementsByAttributeValue("id", "vereine").get(0).getElementsByAttributeValue("class", "s10");
        for (Element link : links1) {
            if (link.attr("title").equals(teamName)) {
                teamUrl = link.attr("href");
            }
        }
        if (teamUrl.equals("")) {
            throw new Error("TeamName \'" + teamName + "\' is not present on " + URL + tourName);
        }
        return teamUrl;
    }

    private String getUrlTournamentName(String tournamentName) throws IOException {
        String nameTourUrl = "";
        Document doc = getDoc(URL_EURO_CHAMPS);
        Elements links = doc.getElementsByClass("al").tagName("a");
        for (Element link : links) {
            if (link.text().equals(tournamentName)) {
                nameTourUrl = link.getElementsByAttribute("href").attr("href");
            }
        }
        if (nameTourUrl.equals("")) {
            throw new Error("Tournament \'" + tournamentName + "\' is not present on " + URL_EURO_CHAMPS);
        }
        return nameTourUrl;
    }

    private Document getDoc(String url) throws IOException {
        return Jsoup.connect(url).
                userAgent("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36")
                .timeout(0).followRedirects(true).execute().parse();
    }

}
