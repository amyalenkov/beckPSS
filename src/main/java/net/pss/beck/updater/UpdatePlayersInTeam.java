package net.pss.beck.updater;

import net.pss.beck.domain.Player;

import java.util.List;
import java.util.Map;

/**
 * Created by amyalenkov on 17.01.14.
 */
public interface UpdatePlayersInTeam {
    public List<Player> getNewPlayersForAdd(List<Player> allPlayers);
    public Map<String, Player> getNewPlayersForAddInTeam(List<Player> playersOld);
    public List<Player> getPlayersForDeleteInTeam(List<Player> playersInTeamOld);
    public Map<String, Player> getPlayersForUpdateNumberInTeam(Map<String, Player> playersInTeamOld);
}
