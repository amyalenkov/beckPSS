package net.pss.beck.updater;

import net.pss.beck.domain.Player;
import net.pss.beck.domain.Team;
import net.pss.beck.domain.Tournament;
import net.pss.beck.parser.ParserHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amyalenkov on 17.01.14.
 */
public class UpdatePlayersInTeamImpl extends UpdateUtils implements UpdatePlayersInTeam {

    private List<Player> playersNew;
    private List<String> playersNameDataNew;
    private Map<String, Player> numberPlayerNew;

    public UpdatePlayersInTeamImpl(Team team, Tournament tournament) throws IOException {
        ParserHelper parserHelper = new ParserHelper();
        playersNew = parserHelper.getPlayersOfTeam(team.getName(), tournament.getName());
        playersNameDataNew = toListPlayersNameData(playersNew);
        numberPlayerNew = parserHelper.getNumberPlayer();
    }

    @Override
    public List<Player> getNewPlayersForAdd(List<Player> allPlayers) {
        List<Player> players = new ArrayList<Player>();
        List<String> nameDataPlayerAll = toListPlayersNameData(allPlayers);
        for (Player player : playersNew) {
            if (!nameDataPlayerAll.contains(player.getLastName() + player.getData())) {
                players.add(player);
            }
        }
        return players;
    }

    @Override
    public Map<String, Player> getNewPlayersForAddInTeam(List<Player> playersOld) {
        Map<String, Player> numberPlayer = new HashMap<String, Player>();
        List<String> nameDataPlayerOld = toListPlayersNameData(playersOld);
        for (String number : numberPlayerNew.keySet()) {
            if (!nameDataPlayerOld.contains(
                    numberPlayerNew.get(number).getLastName() + numberPlayerNew.get(number).getData())) {
                numberPlayer.put(number, numberPlayerNew.get(number));
            }
        }
        return numberPlayer;
    }

    @Override
    public List<Player> getPlayersForDeleteInTeam(List<Player> playersInTeamOld) {
        List<Player> players = new ArrayList<Player>();
        for (Player player : playersInTeamOld) {
            if (!playersNameDataNew.contains(player.getLastName() + player.getData())) {
                players.add(player);
            }
        }
        return players;
    }

    @Override
    public Map<String, Player> getPlayersForUpdateNumberInTeam(Map<String, Player> playersInTeamOld) {
        Map<String, Player> updatePlayer = new HashMap<String, Player>();
        for (String number : numberPlayerNew.keySet()) {
            if (!playersInTeamOld.containsKey(number)) {
                updatePlayer.put(number, numberPlayerNew.get(number));
            } else {
                String nameDataNew = numberPlayerNew.get(number).getLastName() +
                        numberPlayerNew.get(number).getLastName();
                String nameDataOld = playersInTeamOld.get(number).getLastName() +
                        playersInTeamOld.get(number).getLastName();
                if (!nameDataNew.equals(nameDataOld)) {
                    updatePlayer.put(number, numberPlayerNew.get(number));
                }
            }
        }
        return updatePlayer;
    }


}
