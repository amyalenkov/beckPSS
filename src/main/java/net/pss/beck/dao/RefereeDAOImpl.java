package net.pss.beck.dao;

import net.pss.beck.domain.Referee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by amyalenkov on 07.01.14.
 */
@Repository
public class RefereeDAOImpl implements RefereeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addReferee(Referee referee) {
        sessionFactory.getCurrentSession().saveOrUpdate(referee);
    }
}
