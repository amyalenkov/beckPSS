package net.pss.beck.updater;

import net.pss.beck.domain.Team;

import java.io.IOException;
import java.util.List;

/**
 * Created by amyalenkov on 17.01.14.
 */
public interface UpdateTeams {
    public List<String> getTeamNamesForAddInTournament(List<Team> teamsOld) throws IOException;
    public List<Team> getNewTeamsForAdd(List<Team> allTeams) throws IOException;
    public List<Team> getTeamsForDeleteFromTournament(List<Team> teamsOld) throws IOException;

}
