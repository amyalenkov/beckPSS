package net.pss.beck.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by amyalenkov on 05.01.14.
 */
@Entity
@Table(name = "TOURNAMENTS")
public class Tournament {

    @Id
    @Column(name = "ID_TOURNAMENT")
    @GeneratedValue
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATA")
    private String data;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournament")
    private List<TeamsInTournament> teams;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tournament")
    private List<Matches> matchesList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Matches> getMatchesList() {
        return matchesList;
    }

    public void setMatchesList(List<Matches> matchesList) {
        this.matchesList = matchesList;
    }

    public List<TeamsInTournament> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamsInTournament> teams) {
        this.teams = teams;
    }
}
