package net.pss.beck.domain;

import javax.persistence.*;

/**
 * Created by amyalenkov on 07.01.14.
 */
@Entity
@Table(name = "PLAYERS_IN_TEAMS")
public class PlayersInTeams {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "NUMBER")
    private String number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TEAM")
    private Team team;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PLAYER")
    private Player player;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
