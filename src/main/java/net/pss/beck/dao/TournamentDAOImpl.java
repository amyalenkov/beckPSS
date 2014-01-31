package net.pss.beck.dao;

import net.pss.beck.domain.Team;
import net.pss.beck.domain.Tournament;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amyalenkov on 05.01.14.
 */
@Repository
public class TournamentDAOImpl implements TournamentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTournament(Tournament tournament) {
        sessionFactory.getCurrentSession().saveOrUpdate(tournament);
    }

    @Override
    public List<Tournament> getListTournaments() {
        return sessionFactory.getCurrentSession().createQuery("from Tournament ").list();
    }

    @Override
    public Tournament getTournamentById(Integer id) {
        return (Tournament) getSession(sessionFactory).
                createQuery("from Tournament where id="+id).uniqueResult();
    }

    public List<Team> getTeamsFromTournament(Integer id){
        List<Integer> teamIdlList = getSession(sessionFactory).
                createSQLQuery("select ID_TEAM from TEAMS_IN_TOURNAMENT WHERE ID_TOURNAMENT =" + id).list();
        List<Team> teams = new ArrayList<Team>();
        for(Integer teamId : teamIdlList){
            teams.add((Team) getSession(sessionFactory).
                    createQuery("from Team where id="+teamId).uniqueResult());
        }
        return teams;
    }

    public void addTeamsInTournament(Tournament tournament,List<Team> teamsAdd){
        StringBuilder sql = new StringBuilder();
        int i = 0;
        for(Team team : teamsAdd){
            sql.append("("+tournament.getId()+","+team.getId()+")");
            if(teamsAdd.size()-1 != i){
                sql.append(",");
            }
            i++;
        }
        getSession(sessionFactory).
                createSQLQuery("insert into TEAMS_IN_TOURNAMENT (ID_TOURNAMENT,ID_TEAM)" +
                        "values ("+tournament.getId()+")");
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
