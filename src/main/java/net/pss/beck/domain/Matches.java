package net.pss.beck.domain;

import javax.persistence.*;

/**
 * Created by amyalenkov on 07.01.14.
 */
@Entity
@Table(name = "MATCHES")
public class Matches {
    @Id
    @Column(name = "ID_MATCH")
    @GeneratedValue
    private Integer id;

    @Column(name = "DATA")
    private String data;

    @Column(name = "STAGE")
    private String stage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_REFEREE")
    private Referee referee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TOURNAMENT")
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TEAM_1")
    private Team team1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TEAM_2")
    private Team team2;



    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
