package net.pss.beck.service;

import net.pss.beck.dao.TournamentDAO;
import net.pss.beck.domain.Team;
import net.pss.beck.domain.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by amyalenkov on 05.01.14.
 */
@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentDAO tournamentDAO;

    @Override
    @Transactional
    public void addTournament(Tournament tournament) {
        tournamentDAO.addTournament(tournament);
    }

    @Override
    @Transactional
    public List<Tournament> getListTournaments() {
        return tournamentDAO.getListTournaments();
    }

    @Override
    @Transactional
    public Tournament getTournamentById(Integer id) {
        return tournamentDAO.getTournamentById(id);
    }

    @Override
    @Transactional
    public List<Team> getTeamsFromTournament(Integer id) {
        return tournamentDAO.getTeamsFromTournament(id);
    }

    @Override
    @Transactional
    public void addTeamsInTournament(Tournament tournament,List<Team> teamsAdd){
        tournamentDAO.addTeamsInTournament(tournament,teamsAdd);
    }
}
