package net.pss.beck.dao;

import net.pss.beck.domain.Matches;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Matches> getAllMatchesInTournament(Integer tourId){
        return sessionFactory.getCurrentSession().createQuery("from Matches where tournament.id="+tourId).list();
    }

    public Matches getMatchById(Integer idMatch){
        return (Matches) getSession(sessionFactory).createQuery("from Matches where id="+idMatch).uniqueResult();
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
