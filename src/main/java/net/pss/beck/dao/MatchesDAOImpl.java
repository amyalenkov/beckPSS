package net.pss.beck.dao;

import net.pss.beck.domain.Matches;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by amyalenkov on 07.01.14.
 */
@Repository
public class MatchesDAOImpl implements MatchesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addMatches(Matches matches) {
        sessionFactory.getCurrentSession().save(matches);
    }
}
