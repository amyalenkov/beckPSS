package net.pss.beck.service;

import net.pss.beck.domain.Player;
import net.pss.beck.domain.Team;

import java.util.List;
import java.util.Map;

/**
 * Created by amyalenkov on 05.01.14.
 */
public interface TeamService {
    public void addTeam(Team team);
    public List<Team> getListTeams();
    public Map<Player, Integer> getPlayersByTeamId(Integer id);
    public Team getTeamById(Integer id);
    public Team getTeamByName(String name);
}
