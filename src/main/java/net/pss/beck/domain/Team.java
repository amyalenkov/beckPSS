package net.pss.beck.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by amyalenkov on 05.01.14.
 */
@Entity
@Table(name = "TEAMS")
public class Team implements Serializable {

    @Id
    @Column(name = "ID_TEAM")
    @GeneratedValue
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COUNTRY")
    private String counrty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private List<TeamsInTournament> tournaments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team")
    private List<PlayersInTeams> players;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team1")
    private List<Matches> matchesListHome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team2")
    private List<Matches> matchesListAway;

    public String getCounrty() {
        return counrty;
    }

    public void setCounrty(String counrty) {
        this.counrty = counrty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Matches> getMatchesListAway() {
        return matchesListAway;
    }

    public void setMatchesListAway(List<Matches> matchesListAway) {
        this.matchesListAway = matchesListAway;
    }

    public List<Matches> getMatchesListHome() {
        return matchesListHome;
    }

    public void setMatchesListHome(List<Matches> matchesListHome) {
        this.matchesListHome = matchesListHome;
    }

    public List<PlayersInTeams> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayersInTeams> teams) {
        this.players = teams;
    }


    public List<TeamsInTournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<TeamsInTournament> tournaments) {
        this.tournaments = tournaments;
    }
}
