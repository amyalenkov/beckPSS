package net.pss.beck.service;

import net.pss.beck.dao.PlayerDAO;
import net.pss.beck.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by amyalenkov on 05.01.14.
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDAO playerDAO;

    @Override
    @Transactional
    public void addPlayer(Player player) {
        playerDAO.addPlayer(player);
    }

    @Override
    @Transactional
    public List<Player> getPlayers() {
        return playerDAO.getPlayers();
    }

    @Override
    @Transactional
    public Player getPlayerById(Integer id){
        return playerDAO.getPlayerById(id);
    }

    @Override
    @Transactional
    public Player getPlayerByLastNameAndData(String lastName, String data){
        return playerDAO.getPlayerByLastNameAndData(lastName, data);
    }



}
