package net.pss.beck.dao;

import net.pss.beck.domain.Player;
import net.pss.beck.domain.Team;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amyalenkov on 05.01.14.
 */
@Repository
public class TeamDAOImpl implements TeamDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTeam(Team team) {
        sessionFactory.getCurrentSession().saveOrUpdate(team);
    }

    @Override
    public List<Team> getListTeams() {
        return sessionFactory.getCurrentSession().createQuery("from Team ").list();
    }

    public Team getTeamById(Integer id){
        return (Team) sessionFactory.
                getCurrentSession().createQuery("from Team where id="+id).uniqueResult();
    }

    @Override
    public Map<Player, Integer> getPlayersByTeamId(Integer id){
        List<Integer> playersId = sessionFactory.getCurrentSession().
                createSQLQuery("select ID_PLAYER from PLAYERS_IN_TEAMS WHERE ID_TEAM =" + id).list();
        List<String> numbers = sessionFactory.getCurrentSession().
                createSQLQuery("select NUMBER from PLAYERS_IN_TEAMS WHERE ID_TEAM =" + id).list();

        Map<Player,Integer> playerNumber = new HashMap<Player, Integer>();
        int i = 0;
        for(Integer idPlayer : playersId){
            playerNumber.put((Player) sessionFactory.getCurrentSession().
                    createQuery("from Player where id=" + idPlayer).uniqueResult(),
                    Integer.valueOf(numbers.get(i)));
            i++;
        }
        return playerNumber;
    }

    public Team getTeamByName(String name){
        return (Team) getSession(sessionFactory).createQuery("from Team where name='"+name+"'").uniqueResult();
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
