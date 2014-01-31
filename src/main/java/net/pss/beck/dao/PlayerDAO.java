package net.pss.beck.dao;

import net.pss.beck.domain.Player;

import java.util.List;

/**
 * Created by amyalenkov on 05.01.14.
 */
public interface PlayerDAO {

    public void addPlayer(Player player);
    public List getPlayers();
    public Player getPlayerById(Integer id);
    public Player getPlayerByLastNameAndData(String lastName, String data);
}
