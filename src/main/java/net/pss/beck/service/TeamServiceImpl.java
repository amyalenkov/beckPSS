package net.pss.beck.service;

import net.pss.beck.dao.TeamDAO;
import net.pss.beck.domain.Player;
import net.pss.beck.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by amyalenkov on 05.01.14.
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDAO teamDAO;

    @Override
    @Transactional
    public void addTeam(Team team) {
        teamDAO.addTeam(team);
    }

    @Override
    @Transactional
    public List<Team> getListTeams() {
        return teamDAO.getListTeams();
    }

    @Override
    @Transactional
    public Map<Player, Integer> getPlayersByTeamId(Integer id){
        return teamDAO.getPlayersByTeamId(id);
    }

    @Override
    @Transactional
    public Team getTeamById(Integer id){
        return teamDAO.getTeamById(id);
    }

    @Override
    @Transactional
    public Team getTeamByName(String name){
        return teamDAO.getTeamByName(name);
    }

}
