package net.pss.beck.service;

import net.pss.beck.domain.Team;
import net.pss.beck.domain.Tournament;

import java.util.List;

/**
 * Created by amyalenkov on 05.01.14.
 */
public interface TournamentService {
    public void addTournament(Tournament tournament);
    public List<Tournament> getListTournaments();
    public Tournament getTournamentById(Integer id);
    public List<Team> getTeamsFromTournament(Integer id);
    public void addTeamsInTournament(Tournament tournament, List<Team> teamsAdd);
}
