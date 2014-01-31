package net.pss.beck.service;

import net.pss.beck.domain.Matches;

import java.util.List;

/**
 * Created by amyalenkov on 07.01.14.
 */
public interface MatchesService {
    public void addMatches(Matches matches);

    public List<Matches> getAllMatchesInTournament(Integer tourId);

    public Matches getMatchById(Integer idMatch);
}
