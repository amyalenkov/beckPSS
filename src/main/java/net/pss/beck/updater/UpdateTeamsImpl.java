package net.pss.beck.updater;

import net.pss.beck.domain.Team;
import net.pss.beck.domain.Tournament;
import net.pss.beck.parser.ParserHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amyalenkov on 17.01.14.
 */
public class UpdateTeamsImpl extends UpdateUtils implements UpdateTeams {

    private List<Team> teamsNew;
    private List<String> nameTeamNew;

    public UpdateTeamsImpl(Tournament tournament) throws IOException {
        ParserHelper parserHelper = new ParserHelper();
        teamsNew = parserHelper.getAllTeamsInTour(tournament.getName());
        nameTeamNew = toListNameTeams(teamsNew);
    }

    @Override
    public List<String> getTeamNamesForAddInTournament(List<Team> teamsOld) throws IOException {
        List<String> teams = new ArrayList<String>();
        List<String> nameTeamOld = toListNameTeams(teamsOld);
        for(Team team : teamsNew){
            if(!nameTeamOld.contains(team.getName())){
                teams.add(team.getName());
            }
        }
        return teams;
    }

    @Override
    public List<Team> getNewTeamsForAdd(List<Team> allTeams) throws IOException {
        List<Team> teams = new ArrayList<Team>();
        List<String> nameTeamAll = toListNameTeams(allTeams);
        for(Team team : teamsNew){
            if(!nameTeamAll.contains(team.getName())){
                teams.add(team);
            }
        }
        return teams;
    }

    @Override
    public List<Team> getTeamsForDeleteFromTournament(List<Team> teamsOld) throws IOException {
        List<Team> teams = new ArrayList<Team>();
        for(Team team : teamsOld){
            if(!nameTeamNew.contains(team.getName())){
                teams.add(team);
            }
        }
        return teams;
    }
}
