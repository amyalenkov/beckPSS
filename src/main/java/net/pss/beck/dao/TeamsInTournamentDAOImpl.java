package net.pss.beck.dao;

import net.pss.beck.domain.Team;
import net.pss.beck.domain.TeamsInTournament;
import net.pss.beck.domain.Tournament;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amyalenkov on 17.01.14.
 */
@Repository
public class TeamsInTournamentDAOImpl implements TeamsInTournamentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTeamInTournament(TeamsInTournament teamsInTournament) {
        sessionFactory.getCurrentSession().saveOrUpdate(teamsInTournament);
    }

    @Override
    public void deleteTeamInTournament(TeamsInTournament teamsInTournament) {
        sessionFactory.getCurrentSession().delete(teamsInTournament);
    }

    @Override
    public List<TeamsInTournament> getAllTeamsInTournaments() {
        return sessionFactory.getCurrentSession().createQuery("from TeamsInTournament ").list();
    }

    @Override
    public List<TeamsInTournament> getTeamsInTournament(Tournament tournament) {
        return sessionFactory.getCurrentSession().
                createQuery("from TeamsInTournament where tournament.id=" + tournament.getId()).list();
    }

    @Override
    public TeamsInTournament getTeamsInTournament(Team team, Tournament tournament) {
        return (TeamsInTournament) sessionFactory.getCurrentSession().
            createQuery("from TeamsInTournament where team.id=" + team.getId() +
                    " and tournament.id=" + tournament.getId()).uniqueResult();
    }

    public List<Tournament> getTournamentsTeamByTeam(Team team){
        List<Integer> listIdTours = getSession(sessionFactory).
             createSQLQuery("select ID_TOURNAMENT from TEAMS_IN_TOURNAMENT where ID_TEAM=" + team.getId()).list();
        List<Tournament> tours = new ArrayList<Tournament>();
        for(Integer tourId : listIdTours){
            tours.add((Tournament) getSession(sessionFactory).
                    createQuery("from Tournament where id=" + tourId).uniqueResult());
        }
        return tours;
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
