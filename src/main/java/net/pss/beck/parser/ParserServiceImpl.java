package net.pss.beck.parser;

import net.pss.beck.domain.Player;
import net.pss.beck.domain.Team;
import net.pss.beck.domain.Tournament;

import java.io.IOException;
import java.util.List;

/**
 * Created by amyalenkov on 06.01.14.
 */
public class ParserServiceImpl extends ParserHelper implements ParserService {
    @Override
    public List<Team> getAllTeams(Tournament tournament) throws IOException {
        return getAllTeamsInTour(tournament.getName());
    }

    @Override
    public List<Player> getAllPlayersOfTeam(Team team, Tournament tournament) throws IOException {
        return getPlayersOfTeam(team.getName(),tournament.getName());
    }
}
