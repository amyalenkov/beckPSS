package net.pss.beck.dao;

import net.pss.beck.domain.MatchAnalyse;

/**
 * Created by amyalenkov on 29.01.14.
 */
public interface MatchAnalyseDAO {
    public MatchAnalyse getMatchAnalyse(Integer matchId,String timeId);

    public void addMatchTime(MatchAnalyse matchAnalyse);
}
