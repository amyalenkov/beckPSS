package net.pss.beck.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.pss.beck.domain.*;
import net.pss.beck.domain.json.receiver.JInfo;
import net.pss.beck.domain.json.receiver.JMarker;
import net.pss.beck.domain.json.receiver.JReceiver;
import net.pss.beck.domain.json.receiver.JTeam;
import net.pss.beck.domain.json.sender.JMatch;
import net.pss.beck.domain.json.sender.JPlayer;
import net.pss.beck.parser.MatchParser;
import net.pss.beck.parser.ParserService;
import net.pss.beck.parser.ParserServiceImpl;
import net.pss.beck.service.*;
import net.pss.beck.updater.UpdateMatches;
import net.pss.beck.updater.UpdateMatchesImpl;
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

    @Autowired
    private MatchAnalyseService matchAnalyseService;

    @Autowired
    private PushedMarkersService pushedMarkersService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private MarkerService markerService;

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

    @RequestMapping("/matches/{tourId}")
    public String getMatchesInTournament(@PathVariable("tourId") Integer tourId, Model model) {
        List<Matches> matchesList = matchesService.getAllMatchesInTournament(tourId);
        model.addAttribute("matches", matchesList);
        return "matches";
    }

    @RequestMapping(value ="/createMatches", method = RequestMethod.GET)
    public void createMatches(@RequestParam Integer id) throws IOException {
        Tournament tournament = tournamentService.getTournamentById(id);
        System.out.println(tournament.getName());
        UpdateMatches updateMatches = new UpdateMatchesImpl();
        List<MatchParser> matchParserList = updateMatches.getMatchesFromTournament(tournament);
        for(MatchParser matchParser : matchParserList){
            Matches match = new Matches();
            match.setTournament(tournament);
            System.out.println(tournament.getName());
            match.setData(matchParser.getData());
            System.out.println(matchParser.getData());
            match.setStage(matchParser.getStage());
            System.out.println(matchParser.getStage());
            Team team1 = teamService.getTeamByName(matchParser.getTeam1());
            Team team2 = teamService.getTeamByName(matchParser.getTeam2());
            match.setTeam1(team1);
            System.out.println(team1.getId());
            match.setTeam2(team2);
            System.out.println(team2.getId());
            match.setReferee(getReferee(matchParser));
            matchesService.addMatches(match);
            System.out.println("-----------------"+match.getData());
        }
//        return "matches/"+id;
    }

    private Referee getReferee(MatchParser matchParser) throws IOException {
        ParserService parserService = new ParserServiceImpl();
        Referee refereeNew = parserService.getRefereeFromMatch(matchParser.getRefereeLink());
        System.out.println("ref "+refereeNew.getLastName());
        List<Referee> refereeList = refereeService.getAllReferee();
        List<String> refLastNameAndData = new ArrayList<String>();
        for(Referee referee : refereeList){
            refLastNameAndData.add(referee.getLastName()+referee.getData());
        }
        if(!refLastNameAndData.contains(refereeNew.getLastName()+refereeNew.getData())){
            System.out.println("add ref");
            refereeService.addReferee(refereeNew);
            return refereeService.getRefereeByLastNameData(refereeNew);
        }
        else {
            System.out.println("get ref");
            return refereeService.getRefereeByLastNameData(refereeNew);
        }
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    public String saveMarkers(@RequestBody String markers) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JReceiver receiver = mapper.readValue(markers, JReceiver.class);
        JInfo info = receiver.getInfo();
        List<JMarker> jMarkers = receiver.getMarkers();
        MatchAnalyse matchAnalyse = getMatchTime(Integer.valueOf(info.getMatchId()), info.getTimeId());
        PushedMarkers pushedMarker = new PushedMarkers();
        System.out.println(matchAnalyse.getId());
        pushedMarker.setMatchTime(matchAnalyse);
        pushedMarker.setMarkerFirst(info.getMarkerFirst());
        pushedMarker.setMarkerLast(info.getMarkerLast());
        pushedMarker.setFullTime(false);
        pushedMarker.setUser(usersService.getUserByName(info.getUserName()));
        pushedMarkersService.addPushedMarkers(pushedMarker);
        addMarkers(matchAnalyse,jMarkers);
        return "Hello";
    }

    private void addMarkers(MatchAnalyse matchAnalyse, List<JMarker> jMarkers){
        for(JMarker jMarker : jMarkers){
            Markers markers = new Markers();
            markers.setMatchTime(matchAnalyse);
            markers.setIdMarker(jMarker.getId());
            markers.setAddAction(jMarker.getAddAction());
            markers.setBasicAction(jMarker.getBasicAction());
            markers.setPartBody(jMarker.getPartBody());
            markers.setTeam1(jMarker.getTeam1());
            markers.setPlayer1(jMarker.getPlayer1());
            markers.setTeam2(jMarker.getTeam2());
            markers.setPlayer2(jMarker.getPlayer2());
            markers.setTime(jMarker.getTime());
            markerService.addMarker(markers);
        }
        if(jMarkers.get(jMarkers.size()-1).getBasicAction().equals("FULL TIME")){
            matchAnalyse.setStatus("complete");
            matchAnalyseService.addMatchTime(matchAnalyse);
        }
    }

    private MatchAnalyse getMatchTime(Integer matchId, String timeId){
        MatchAnalyse matchAnalyse = matchAnalyseService.getMatchAnalyse(matchId,timeId);
        if(matchAnalyse == null){
            matchAnalyse = new MatchAnalyse();
            matchAnalyse.setMatch(matchesService.getMatchById(matchId));
            matchAnalyse.setTime(timeId);
            matchAnalyse.setStatus("incomplete");
            matchAnalyseService.addMatchTime(matchAnalyse);
            return matchAnalyseService.getMatchAnalyse(matchId,timeId);
        }else {
            return matchAnalyseService.getMatchAnalyse(matchId,timeId);
        }
    }

    @RequestMapping(value = "/getMatchById", method = RequestMethod.GET)
    @ResponseBody
    public String getMatchInfoById(@RequestParam Integer idMatch) throws IOException {
        System.out.println("idMatch "+idMatch);
        Matches matches =  matchesService.getMatchById(idMatch);
        System.out.println(matches.getTeam1());
        Team team1 = teamService.getTeamById(matches.getTeam1().getId());
        Team team2 = teamService.getTeamById(matches.getTeam2().getId());
        List<Player> playersTeam1 = playersInTeamsService.getPlayersByTeam(team1);
        List<Player> playersTeam2 = playersInTeamsService.getPlayersByTeam(team2);
        ObjectMapper mapper = new ObjectMapper();
        JMatch jMatch = new JMatch();
        net.pss.beck.domain.json.sender.JTeam jTeam1 = new net.pss.beck.domain.json.sender.JTeam();
        net.pss.beck.domain.json.sender.JTeam jTeam2 = new net.pss.beck.domain.json.sender.JTeam();

        List<JPlayer> jPlayerList1 = new ArrayList<JPlayer>();
        List<JPlayer> jPlayerList2 = new ArrayList<JPlayer>();
        for(Player player : playersTeam1){
            jPlayerList1.add(new JPlayer(String.valueOf(player.getId()),player.getLastName(),player.getFirstName(),
                    playersInTeamsService.getPlayersInTeamByTeamAndPlayer(team1,player).getNumber()));
        }
        for(Player player : playersTeam2){
            jPlayerList2.add(new JPlayer(String.valueOf(player.getId()),player.getLastName(),player.getFirstName(),
                    playersInTeamsService.getPlayersInTeamByTeamAndPlayer(team2,player).getNumber()));
        }
        jTeam1.setId(String.valueOf(team1.getId()));
        jTeam1.setName(team1.getName());
        jTeam1.setPlayers(jPlayerList1);
        jTeam2.setId(String.valueOf(team2.getId()));
        jTeam2.setName(team2.getName());
        jTeam2.setPlayers(jPlayerList2);
        jMatch.setTeam1(jTeam1);
        jMatch.setTeam2(jTeam2);
        jMatch.setId(String.valueOf(idMatch));
        jMatch.setData(matches.getData());
        jMatch.setStage(matches.getStage());
        jMatch.setReferee(matches.getReferee().getLastName());
        jMatch.setTourName(matches.getTournament().getName()+matches.getTournament().getData());
        return mapper.writeValueAsString(jMatch);
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
//        teamService.addReferee(team);
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

        refereeService.addReferee(referee);
        matchesService.addMatches(matches);

        playersInTeamsService.addPlayerInTeam(playersInTeams1);
        playersInTeamsService.addPlayerInTeam(playersInTeams2);

        return "redirect:/index";
    }

}
