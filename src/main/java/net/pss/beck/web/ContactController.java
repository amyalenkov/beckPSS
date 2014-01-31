package net.pss.beck.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.pss.beck.domain.*;
import net.pss.beck.domain.json.JSender;
import net.pss.beck.domain.json.JTeam;
import net.pss.beck.parser.ParserServiceImpl;
import net.pss.beck.service.*;
import net.pss.beck.updater.UpdatePlayersInTeamImpl;
import net.pss.beck.updater.UpdateTeamsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private RefereeService refereeService;

    @Autowired
    private MatchesService matchesService;

    @Autowired
    private PlayersInTeamsService playersInTeamsService;

    @Autowired
    private TeamsInTournamentService teamsInTournamentService;

//	@RequestMapping("/index")
//	public String listTours(Map<String, Object> map) {
//
//		map.put("contact", new Contact());
//		map.put("contactList", contactService.listContact());
//
//		return "contact";
//	}

    @RequestMapping("/index")
    public String listTours(Model model) {
        model.addAttribute("tours", tournamentService.getListTournaments());
        List<Team> teams = new ArrayList<Team>();


        model.addAttribute("teams", teams);
        return "review";
    }

    @RequestMapping(value = "/choiseTour", method = RequestMethod.GET)
    public
    @ResponseBody
    String getTeamsInTour(@RequestParam String id) throws JsonProcessingException {
        System.out.println("tourname " + id);
        List<Team> teams = tournamentService.getTeamsFromTournament(Integer.valueOf(id));
        List<JTeam> jTeams = new ArrayList<JTeam>();
        for(Team team : teams){
            jTeams.add(new JTeam(team.getId(),team.getName(),team.getCounrty()));
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(jTeams);
    }

    @RequestMapping(value = "/updateTeams", method = RequestMethod.GET)
    public void updateTeamsInTour(@RequestParam Integer id) throws IOException {
        Tournament tournament = tournamentService.getTournamentById(id);
        UpdateTeamsImpl updateTeams = new UpdateTeamsImpl(tournament);

        List<Team> allTeams = teamService.getListTeams();
        for (Team team : updateTeams.getNewTeamsForAdd(allTeams)){
            System.out.println("create "+team.getName());
            teamService.addTeam(team);
        }

        List<Team> teamsOld = tournamentService.getTeamsFromTournament(id);
        for(Team team : updateTeams.getTeamsForDeleteFromTournament(teamsOld)){
            System.out.println("delete " + team.getName());
            teamsInTournamentService.deleteTeamInTournament(
                    teamsInTournamentService.getTeamInTournament(team, tournament));
        }

        for(String teamName : updateTeams.getTeamNamesForAddInTournament(teamsOld)){
            Team team = teamService.getTeamByName(teamName);
            System.out.println("add "+team.getName()+" in tour "+tournament.getName());
            TeamsInTournament teamsInTournament = new TeamsInTournament();
            teamsInTournament.setTeam(team);
            teamsInTournament.setTournament(tournament);
            teamsInTournamentService.addTeamInTournament(teamsInTournament);

        }
    }

    @RequestMapping(value = "/updatePlayersInTeam", method = RequestMethod.GET)
    public void updatePlayersInTeam(@RequestParam String teamName) throws IOException {
        System.out.println("teamName "+teamName);
        Team team = teamService.getTeamByName(teamName);
        Tournament tournament = teamsInTournamentService.getTournamentsByTeam(team).get(0);
        UpdatePlayersInTeamImpl updatePlayersInTeam = new UpdatePlayersInTeamImpl(team,tournament);

        for(Player player : updatePlayersInTeam.getNewPlayersForAdd(playerService.getPlayers())){
            System.out.println("create "+player.getLastName());
            playerService.addPlayer(player);
        }

        List<Player> playersInTeamOld = playersInTeamsService.getPlayersByTeam(team);
        for(Player player : updatePlayersInTeam.getPlayersForDeleteInTeam(playersInTeamOld)){
            System.out.println("delete "+player.getId());
            for(PlayersInTeams playersInTeams : playersInTeamsService.getPlayersInTeam(team)){
                if(playersInTeams.getPlayer().getId().equals(player.getId())){
                    playersInTeamsService.deletePlayerInTeam(playersInTeams);
                }
            }
        }


        Map<String,Player> numberPlayerNew = updatePlayersInTeam.getNewPlayersForAddInTeam(playersInTeamOld);
        for(String number : numberPlayerNew.keySet()){
            System.out.println("add "+number+ " in team "+team.getName());
            PlayersInTeams playersInTeams = new PlayersInTeams();
            playersInTeams.setNumber(number);
            playersInTeams.setTeam(team);
            playersInTeams.setPlayer(playerService.getPlayerByLastNameAndData(
                numberPlayerNew.get(number).getLastName(),numberPlayerNew.get(number).getData()));
            playersInTeamsService.addPlayerInTeam(playersInTeams);
        }

        Map<String,Player> numberPlayer = new HashMap<String, Player>();
        for(PlayersInTeams playersInTeams : playersInTeamsService.getPlayersInTeam(team)){
            Player player = playerService.getPlayerById(playersInTeams.getPlayer().getId());
            numberPlayer.put(playersInTeams.getNumber(),player);
        }

        Map<String,Player> numberPlayerUpdate
                = updatePlayersInTeam.getPlayersForUpdateNumberInTeam(numberPlayer);
        System.out.println("-- "+numberPlayerUpdate.size());
        for(String number : numberPlayerUpdate.keySet()){
            System.out.println("update "+number+ " in team "+team.getName());
            Player player = playerService.getPlayerByLastNameAndData(
                    numberPlayerNew.get(number).getLastName(),numberPlayerNew.get(number).getData());
            PlayersInTeams playersInTeamsUpdate =
                    playersInTeamsService.getPlayersInTeamByTeamAndPlayer(team, player);
            playersInTeamsUpdate.setNumber(number);

            playersInTeamsService.addPlayerInTeam(playersInTeamsUpdate);
        }

    }

    @RequestMapping("/teams/{teamId}")
    public String getTeamInfo(@PathVariable("teamId") Integer teamId, Model model) {
        Team team = teamService.getTeamById(teamId);
        model.addAttribute("team",team);
        model.addAttribute("players", playersInTeamsService.getPlayersInTeam(team));
        return "teams";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contact") Contact contact,
                             BindingResult result) {

        contactService.addContact(contact);

        return "redirect:/index";
    }

    @RequestMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable("contactId") Integer contactId) {

        contactService.removeContact(contactId);

        return "redirect:/index";
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    public String check(@RequestBody String markers) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JSender sender = mapper.readValue(markers, JSender.class);

        return "Hello";
    }

    @RequestMapping(value = "/save_all_tours", method = RequestMethod.POST)
    public String saveTours() {
//        Tournament tournament = new Tournament();
//        tournament.setName("Wyschejschaja Liha");
//        tournament.setData("2013");
//        tournamentService.addTournament(tournament);
        Tournament tournament = tournamentService.getListTournaments().get(0);
        System.out.println(tournament.getName());
//        List<Team> teams = teamService.getListTeams();
//        tournament.setTeams(teams);
//        tournamentService.addTournament(tournament);
        return "redirect:/index";
    }

    @RequestMapping(value = "/save_teams", method = RequestMethod.POST)
    public String saveTeams() throws IOException {
        Tournament tournament = new Tournament();
        tournament.setName("Wyschejschaja Liha");
        ParserServiceImpl parserService = new ParserServiceImpl();
        for (Team team : parserService.getAllTeams(tournament)) {
            List<Player> players = parserService.getAllPlayersOfTeam(team, tournament);
            for (Player player : players) {
                playerService.addPlayer(player);
            }
//            team.setPlayers(players);
            teamService.addTeam(team);
        }

        return "redirect:/index";
    }

    @RequestMapping(value = "/save_player", method = RequestMethod.GET)
    public String savePlayer() throws IOException {
        Tournament tournament = tournamentService.getTournamentById(2);
        System.out.println(tournament.getName());
//        List<Team> teams = tournament.getTeams();
        List<Team> teams = tournamentService.getTeamsFromTournament(2);
        for (Team team : teams) {
            System.out.println(team.getName());
        }
//        tournament.setName("Wyschejschaja Liha");
//        Team team = new Team();
//        team.setName("FK Gomel");
//        Player player = new Player();
//        player.setLastName("sdf");
//        player.setNumber("7");
//        List<Player> players = new ArrayList<Player>();
//        players.add(player);
//        team.setPlayers(players);
//        teamService.addTeam(team);
//        player.setTeam(team);
//        playerService.addPlayer(player);
//        ParserServiceImpl parserService = new ParserServiceImpl();
//        for(Player player : parserService.getAllPlayersOfTeam(team, tournament)){
//            playerService.addPlayer(player);
//        }
        return "redirect:/index";


    }

    @RequestMapping(value = "/checkPlay", method = RequestMethod.POST)
    public String Test() throws IOException {
        List<PlayersInTeams> playersInTeamses = playersInTeamsService.getPlayersInTeams();
        System.out.println("size " + playersInTeamses.size());
        Team team = new Team();
        team.setId(1);
        List<PlayersInTeams> playersInTeamses1 = playersInTeamsService.getPlayersInTeam(team);
        System.out.println("size1 " + playersInTeamses1.size());
//        System.out.println("name "+playersInTeamses1.get(0).get);
        Player player = playersInTeamses1.get(0).getPlayer();
        Team team1 = playersInTeamses1.get(0).getTeam();
        System.out.println("namePL " + player.getLastName());
        System.out.println("nameT " + team1.getName());

        playersInTeamsService.deletePlayerInTeam(playersInTeamses.get(0));

        return "redirect:/index";
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String TestHand() throws IOException {
        Tournament tournament = new Tournament();
        PlayersInTeams playersInTeams1 = new PlayersInTeams();
        PlayersInTeams playersInTeams2 = new PlayersInTeams();
        Team team1 = new Team();
        Team team2 = new Team();
        Player player1 = new Player();
        Player player2 = new Player();
        Referee referee = new Referee();
        Matches matches = new Matches();
        List<Player> players1 = new ArrayList<Player>();
        List<Player> players2 = new ArrayList<Player>();
        List<Team> teams = new ArrayList<Team>();
        tournament.setName("Wyschejschaja Liha");
        tournament.setData("2013");
        team1.setName("FK Gomel");
        team1.setCounrty("BLR");
        team2.setName("BATE");
        team2.setCounrty("BLR");
        teams.add(team1);
        teams.add(team2);
        player1.setCounrty("BLR");
        player1.setData("12332");
        player1.setFirstName("FN1");
        player1.setLastName("LN1");
        player2.setCounrty("BLR");
        player2.setData("1233122");
        player2.setFirstName("FN2");
        player2.setLastName("LN2");
        players1.add(player1);
        players2.add(player2);
        referee.setCounrty("dsd");
        referee.setData("dsd");
        referee.setFirstName("FN");
        referee.setLastName("LN");
//        team1.setPlayers(players1);
//        team2.setPlayers(players2);
//        tournament.setTeams(teams);
        matches.setData("122121");
        matches.setStage("final");
        matches.setTeam1(team1);
        matches.setTeam2(team2);
        matches.setReferee(referee);
        matches.setTournament(tournament);

        playersInTeams1.setNumber("15");
        playersInTeams1.setPlayer(player1);
        playersInTeams1.setTeam(team1);
        playersInTeams2.setNumber("12");
        playersInTeams2.setPlayer(player2);
        playersInTeams2.setTeam(team2);


        teamService.addTeam(team1);
        teamService.addTeam(team2);

        tournamentService.addTournament(tournament);
        playerService.addPlayer(player1);
        playerService.addPlayer(player2);

        refereeService.addTeam(referee);
        matchesService.addMatches(matches);

        playersInTeamsService.addPlayerInTeam(playersInTeams1);
        playersInTeamsService.addPlayerInTeam(playersInTeams2);

        return "redirect:/index";
    }

}
