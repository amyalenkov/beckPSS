package net.pss.beck.service;

import net.pss.beck.dao.MatchesDAO;
import net.pss.beck.domain.Matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by amyalenkov on 07.01.14.
 */
@Service
public class MatchesServiceImpl implements MatchesService {

    @Autowired
    MatchesDAO matchesDAO;

    @Override
    @Transactional
    public void addMatches(Matches matches) {
        matchesDAO.addMatches(matches);
    }
}
