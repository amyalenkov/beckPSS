package net.pss.beck.parser;

import net.pss.beck.domain.Player;
import net.pss.beck.domain.Referee;
import net.pss.beck.domain.Team;
import net.pss.beck.domain.Tournament;

import java.io.IOException;
import java.util.List;

/**
 * Created by amyalenkov on 06.01.14.
 */
public interface ParserService {
    public List<Team> getAllTeams(Tournament tournament) throws IOException;
    public List<Player> getAllPlayersOfTeam(Team team, Tournament tournament) throws IOException;
    public List<MatchParser> getAllMatchesInTournament(String tournamentName) throws IOException;
    public Referee getRefereeFromMatch(String matchUlr) throws IOException;
}
