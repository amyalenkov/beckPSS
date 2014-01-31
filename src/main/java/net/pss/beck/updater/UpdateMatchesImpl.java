package net.pss.beck.updater;

import net.pss.beck.domain.Tournament;
import net.pss.beck.parser.MatchParser;
import net.pss.beck.parser.ParserService;
import net.pss.beck.parser.ParserServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * Created by amyalenkov on 23.01.14.
 */
public class UpdateMatchesImpl implements UpdateMatches {


    @Override
    public List<MatchParser> getMatchesFromTournament(Tournament tournament) throws IOException {
        ParserService parserService = new ParserServiceImpl();
        return parserService.getAllMatchesInTournament(tournament.getName());
    }
}
