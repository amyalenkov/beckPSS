package net.pss.beck.updater;

import net.pss.beck.domain.Tournament;
import net.pss.beck.parser.MatchParser;

import java.io.IOException;
import java.util.List;

/**
 * Created by amyalenkov on 31.01.14.
 */
public interface UpdateMatches {
    public List<MatchParser> getMatchesFromTournament(Tournament tournament) throws IOException;

}
