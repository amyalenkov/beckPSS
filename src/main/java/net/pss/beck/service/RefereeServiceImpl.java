package net.pss.beck.service;

import net.pss.beck.dao.RefereeDAO;
import net.pss.beck.domain.Referee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by amyalenkov on 07.01.14.
 */
@Service
public class RefereeServiceImpl implements RefereeService {

    @Autowired
    private RefereeDAO refereeDAO;

    @Override
    @Transactional
    public void addTeam(Referee referee) {
        refereeDAO.addReferee(referee);
    }
}
