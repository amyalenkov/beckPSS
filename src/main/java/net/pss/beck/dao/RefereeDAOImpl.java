package net.pss.beck.dao;

import net.pss.beck.domain.Referee;
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
public class RefereeDAOImpl implements RefereeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addReferee(Referee referee) {
        sessionFactory.getCurrentSession().saveOrUpdate(referee);
    }

    @Override
    public Referee getRefereeByLastNameData(Referee referee) {
        return (Referee) getSession(sessionFactory).createQuery("from Referee " +
            " where lastName=\'"+referee.getLastName()+"\' and data=\'"+referee.getData()+"\'").uniqueResult();
    }

    @Override
    public List<Referee> getAllReferee(){
        return getSession(sessionFactory).createQuery("from Referee ").list();
    }

    public Referee getRefereeById(Referee referee){
        return (Referee) getSession(sessionFactory).createQuery(
                "from Referee where id="+referee.getId()).uniqueResult();
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
