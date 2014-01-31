package net.pss.beck.dao;

import net.pss.beck.domain.Player;
import net.pss.beck.domain.PlayersInTeams;
import net.pss.beck.domain.Team;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amyalenkov on 07.01.14.
 */
@Repository
public class PlayersInTeamsDAOImpl implements PlayersInTeamsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPlayerInTeam(PlayersInTeams playersInTeams) {
        sessionFactory.getCurrentSession().saveOrUpdate(playersInTeams);
    }

    @Override
    public void deletePlayerInTeam(PlayersInTeams playersInTeams) {
        sessionFactory.getCurrentSession().delete(playersInTeams);
    }

    @Override
    public List<PlayersInTeams> getPlayersInTeams() {
        return sessionFactory.getCurrentSession().createQuery("from PlayersInTeams ").list();
    }

    @Override
    public List<PlayersInTeams> getPlayersInTeam(Team team) {
        return sessionFactory.getCurrentSession().
                createQuery("from PlayersInTeams where team.id=" + team.getId()).list();
    }

    @Override
    public List<Player> getPlayersByTeam(Team team){
        List<Integer> playerIdlList = getSession(sessionFactory).
                createSQLQuery("select ID_PLAYER from PLAYERS_IN_TEAMS WHERE ID_TEAM =" + team.getId()).list();
        List<Player> players = new ArrayList<Player>();
        for(Integer playerId : playerIdlList){
            players.add((Player) getSession(sessionFactory).
                    createQuery("from Player where id=" + playerId).uniqueResult());
        }
        return players;
    }

    public void deletePlayerByIdInTeam(Player player){
        sessionFactory.getCurrentSession().
                createSQLQuery("delete from PLAYERS_IN_TEAMS where ID_PLAYER=" + player.getId());
    }

    public PlayersInTeams getPlayersInTeamByTeamAndPlayer(Team team, Player player){
        return (PlayersInTeams) sessionFactory.getCurrentSession().
                createQuery("from PlayersInTeams where team.id=" + team.getId()+
                " and player.id="+player.getId()).uniqueResult();
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
