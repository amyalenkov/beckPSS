package net.pss.beck.service;

import net.pss.beck.domain.Player;
import net.pss.beck.domain.PlayersInTeams;
import net.pss.beck.domain.Team;

import java.util.List;

/**
 * Created by amyalenkov on 07.01.14.
 */
public interface PlayersInTeamsService {
    public void addPlayerInTeam(PlayersInTeams playersInTeams);
    public void deletePlayerInTeam(PlayersInTeams playersInTeams);
    public List<PlayersInTeams> getPlayersInTeams();
    public List<PlayersInTeams> getPlayersInTeam(Team team);
    public List<Player> getPlayersByTeam(Team team);
    public void deletePlayerByIdInTeam(Player player);
    public PlayersInTeams getPlayersInTeamByTeamAndPlayer(Team team, Player player);
}
