package net.pss.beck.service;

import net.pss.beck.dao.MatchAnalyseDAO;
import net.pss.beck.domain.MatchAnalyse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by amyalenkov on 29.01.14.
 */
@Service
public class MatchAnalyseServiceImpl implements MatchAnalyseService {

    @Autowired
    MatchAnalyseDAO matchAnalyseDAO;

    @Override
    @Transactional
    public MatchAnalyse getMatchAnalyse(Integer matchId, String timeId) {
        return matchAnalyseDAO.getMatchAnalyse(matchId,timeId);
    }

    @Override
    @Transactional
    public void addMatchTime(MatchAnalyse matchAnalyse){
        matchAnalyseDAO.addMatchTime(matchAnalyse);
    }

}
