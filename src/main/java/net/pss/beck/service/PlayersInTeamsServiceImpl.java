package net.pss.beck.service;

import net.pss.beck.dao.PlayersInTeamsDAO;
import net.pss.beck.domain.Player;
import net.pss.beck.domain.PlayersInTeams;
import net.pss.beck.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by amyalenkov on 07.01.14.
 */
@Service
public class PlayersInTeamsServiceImpl implements PlayersInTeamsService {

    @Autowired
    private PlayersInTeamsDAO playersInTeamsDAO;

    @Override
    @Transactional
    public void addPlayerInTeam(PlayersInTeams playersInTeams) {
        playersInTeamsDAO.addPlayerInTeam(playersInTeams);
    }

    @Override
    @Transactional
    public void deletePlayerInTeam(PlayersInTeams playersInTeams) {
        playersInTeamsDAO.deletePlayerInTeam(playersInTeams);
    }

    @Override
    @Transactional
    public List<PlayersInTeams> getPlayersInTeams() {
        return playersInTeamsDAO.getPlayersInTeams();
    }

    @Override
    @Transactional
    public List<PlayersInTeams> getPlayersInTeam(Team team) {
        return playersInTeamsDAO.getPlayersInTeam(team);
    }

    @Override
    @Transactional
    public List<Player> getPlayersByTeam(Team team){
        return playersInTeamsDAO.getPlayersByTeam(team);
    }

    @Override
    @Transactional
    public void deletePlayerByIdInTeam(Player player){
        playersInTeamsDAO.deletePlayerByIdInTeam(player);
    }

    @Override
    @Transactional
    public PlayersInTeams getPlayersInTeamByTeamAndPlayer(Team team, Player player){
        return playersInTeamsDAO.getPlayersInTeamByTeamAndPlayer(team, player);
    }

}
