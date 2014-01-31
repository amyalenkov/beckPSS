package net.pss.beck.updater;

import net.pss.beck.domain.Player;
import net.pss.beck.domain.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amyalenkov on 17.01.14.
 */
public class UpdateUtils {

    protected List<String> toListNameTeams(List<Team> teams){
        List<String> listTeams = new ArrayList<String>();
        for(Team team : teams){
            listTeams.add(team.getName());
        }
        return listTeams;
    }

    protected List<String> toListPlayersNameData(List<Player> players) {
        List<String> listPlayers = new ArrayList<String>();
        for(Player player : players){
            listPlayers.add(player.getLastName()+player.getData());
        }
        return listPlayers;
    }


}
