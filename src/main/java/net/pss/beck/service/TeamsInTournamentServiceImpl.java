package net.pss.beck.service;

import net.pss.beck.dao.TeamsInTournamentDAO;
import net.pss.beck.domain.Team;
import net.pss.beck.domain.TeamsInTournament;
import net.pss.beck.domain.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by amyalenkov on 17.01.14.
 */
@Service
public class TeamsInTournamentServiceImpl implements TeamsInTournamentService {

    @Autowired
    private TeamsInTournamentDAO teamsInTournamentDAO;

    @Override
    @Transactional
    public void addTeamInTournament(TeamsInTournament teamsInTournament) {
        teamsInTournamentDAO.addTeamInTournament(teamsInTournament);
    }

    @Override
    @Transactional
    public void deleteTeamInTournament(TeamsInTournament teamsInTournament) {
        teamsInTournamentDAO.deleteTeamInTournament(teamsInTournament);
    }

    @Override
    @Transactional
    public List<TeamsInTournament> getAllTeamsInTournaments() {
        return teamsInTournamentDAO.getAllTeamsInTournaments();
    }

    @Override
    @Transactional
    public List<TeamsInTournament> getTeamsInTournament(Tournament tournament) {
        return teamsInTournamentDAO.getTeamsInTournament(tournament);
    }

    @Override
    @Transactional
    public TeamsInTournament getTeamInTournament(Team team, Tournament tournament) {
        return teamsInTournamentDAO.getTeamsInTournament(team,tournament);
    }

    @Override
    @Transactional
    public List<Tournament> getTournamentsByTeam(Team team){
        return teamsInTournamentDAO.getTournamentsTeamByTeam(team);
    }

}
