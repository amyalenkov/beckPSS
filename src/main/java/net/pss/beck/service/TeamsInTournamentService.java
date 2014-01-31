package net.pss.beck.service;

import net.pss.beck.domain.Team;
import net.pss.beck.domain.TeamsInTournament;
import net.pss.beck.domain.Tournament;

import java.util.List;

/**
 * Created by amyalenkov on 17.01.14.
 */
public interface TeamsInTournamentService {
    public void addTeamInTournament(TeamsInTournament teamsInTournament);
    public void deleteTeamInTournament(TeamsInTournament teamsInTournament);
    public List<TeamsInTournament> getAllTeamsInTournaments();
    public List<TeamsInTournament> getTeamsInTournament(Tournament tournament);
    public TeamsInTournament getTeamInTournament(Team team, Tournament tournament);
    public List<Tournament> getTournamentsByTeam(Team team);
}
