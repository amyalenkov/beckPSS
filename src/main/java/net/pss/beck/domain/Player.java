package net.pss.beck.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by amyalenkov on 05.01.14.
 */
@Entity
@Table(name = "PLAYERS")
public class Player {
    @Id
    @Column(name = "ID_PLAYER")
    @GeneratedValue
    private Integer id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DATA")
    private String data;

    @Column(name = "COUNTRY")
    private String counrty;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "player")
    private List<PlayersInTeams> playerInTeam;

    public String getCounrty() {
        return counrty;
    }

    public void setCounrty(String counrty) {
        this.counrty = counrty;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<PlayersInTeams> getPlayerInTeam() {
        return playerInTeam;
    }

    public void setPlayerInTeam(List<PlayersInTeams> playerInTeam) {
        this.playerInTeam = playerInTeam;
    }
}
