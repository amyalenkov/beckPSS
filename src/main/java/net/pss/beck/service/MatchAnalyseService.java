package net.pss.beck.service;

import net.pss.beck.domain.MatchAnalyse;

/**
 * Created by amyalenkov on 29.01.14.
 */
public interface MatchAnalyseService {
    public MatchAnalyse getMatchAnalyse(Integer matchId,String timeId);

    public void addMatchTime(MatchAnalyse matchAnalyse);
}
