package net.pss.beck.dao;

import net.pss.beck.domain.Markers;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by amyalenkov on 30.01.14.
 */
@Repository
public class MarkersDAOImpl implements MarkersDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addMarker(Markers marker) {
        getSession(sessionFactory).save(marker);
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
