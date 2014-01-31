package net.pss.beck.dao;

import net.pss.beck.domain.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by amyalenkov on 30.01.14.
 */
@Repository
public class UsersDAOImpl implements UsersDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Users getUserByName(String name) {
        return (Users) getSession(sessionFactory).
            createQuery("from Users where name=\'"+name+"\'").uniqueResult();
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
