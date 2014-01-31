package net.pss.beck.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.pss.beck.domain.Player;
import net.pss.beck.domain.Referee;
import net.pss.beck.domain.json.sender.JMatch;
import net.pss.beck.domain.json.sender.JPlayer;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amyalenkov on 04.01.14.
 */
public class TestTest {

    @Test
    public void testMatches() throws IOException {
        String nameTour = "Wyschejschaja Liha";
        ParserHelper parserHelper = new ParserHelper();
        List<MatchParser> matches =  parserHelper.getMatches(nameTour);
        System.out.println(matches.size());

    }

    @Test
    public void testRefereeFromMatch() throws IOException {
        String nameTour = "/en/fk-minsk-torpedo-zhodino/index/spielbericht_2290075.html";
        ParserHelper parserHelper = new ParserHelper();
        Referee referee = parserHelper.getReferee(nameTour);
        System.out.println(referee.getFirstName());
        System.out.println(referee.getLastName());
        System.out.println(referee.getData());
        System.out.println(referee.getCounrty());
    }

    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JMatch jMatch = new JMatch();
        net.pss.beck.domain.json.sender.JTeam jTeam1 = new net.pss.beck.domain.json.sender.JTeam();
        net.pss.beck.domain.json.sender.JTeam jTeam2 = new net.pss.beck.domain.json.sender.JTeam();
        JPlayer jPlayer1 = new JPlayer("11","ln","fn","7");
        JPlayer jPlayer2 = new JPlayer("12","ln2","fn2","11");
        List<JPlayer> jPlayerList1 = new ArrayList<JPlayer>();
        List<JPlayer> jPlayerList2 = new ArrayList<JPlayer>();
        jPlayerList1.add(jPlayer1);
        jPlayerList2.add(jPlayer2);
        jTeam1.setId("21");
        jTeam1.setName("team1");
        jTeam1.setPlayers(jPlayerList1);
        jTeam2.setId("22");
        jTeam2.setName("team2");
        jTeam2.setPlayers(jPlayerList2);
        jMatch.setTeam1(jTeam1);
        jMatch.setTeam2(jTeam2);
        System.out.println(mapper.writeValueAsString(jMatch));
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
