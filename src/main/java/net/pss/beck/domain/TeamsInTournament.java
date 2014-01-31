package net.pss.beck.domain;

import javax.persistence.*;

/**
 * Created by amyalenkov on 15.01.14.
 */
@Entity
@Table(name = "TEAMS_IN_TOURNAMENT")
public class TeamsInTournament {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TOURNAMENT")
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TEAM")
    private Team team;

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
