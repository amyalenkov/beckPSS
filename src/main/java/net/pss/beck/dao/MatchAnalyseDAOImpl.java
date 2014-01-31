package net.pss.beck.dao;

import net.pss.beck.domain.MatchAnalyse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by amyalenkov on 29.01.14.
 */
@Repository
public class MatchAnalyseDAOImpl implements MatchAnalyseDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public MatchAnalyse getMatchAnalyse(Integer matchId, String timeId) {
        return (MatchAnalyse) getSession(sessionFactory).createQuery(
                "from MatchAnalyse where match.id=" + matchId + " and time=\'" + timeId + "\'").uniqueResult();

    }

    @Override
    public void addMatchTime(MatchAnalyse matchAnalyse){
        getSession(sessionFactory).saveOrUpdate(matchAnalyse);
    }

    private Session getSession(SessionFactory sessionFactory){
        Session session = null;
        try{
            session = sessionFactory.getCurrentSession();
        }catch(HibernateException hex){
            hex.printStackTrace();
        }
        if(session == null && !session.isOpen()){
            session =  sessionFactory.openSession();
        }
        return session;
    }

}
