package net.pss.beck.dao;

import net.pss.beck.domain.Player;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by amyalenkov on 05.01.14.
 */
@Repository
public class PlayerDAOImpl implements PlayerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPlayer(Player player) {
        sessionFactory.getCurrentSession().save(player);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Player> getPlayers() {
        return sessionFactory.getCurrentSession().createQuery("from Player").list();
    }

    public Player getPlayerById(Integer id){
        return (Player) sessionFactory.getCurrentSession().
            createQuery("from Player where id="+id).uniqueResult();
    }

    public Player getPlayerByLastNameAndData(String lastName, String data){
        return (Player) sessionFactory.getCurrentSession().
            createQuery("from Player where lastName=\'"+lastName+"\' and data=\'"+data+"\'").uniqueResult();
    }

}
