package net.pss.beck.service;

import net.pss.beck.dao.RefereeDAO;
import net.pss.beck.domain.Referee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by amyalenkov on 07.01.14.
 */
@Service
public class RefereeServiceImpl implements RefereeService {

    @Autowired
    private RefereeDAO refereeDAO;

    @Override
    @Transactional
    public void addReferee(Referee referee) {
        refereeDAO.addReferee(referee);
    }

    @Override
    @Transactional
    public Referee getRefereeByLastNameData(Referee referee) {
        return refereeDAO.getRefereeByLastNameData(referee);
    }

    @Override
    @Transactional
    public List<Referee> getAllReferee(){
        return refereeDAO.getAllReferee();
    }

    @Override
    @Transactional
    public Referee getRefereeById(Referee referee){
        return refereeDAO.getRefereeById(referee);
    }



}
