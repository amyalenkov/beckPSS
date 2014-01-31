package net.pss.beck.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by amyalenkov on 07.01.14.
 */
@Entity
@Table(name = "REFEREES")
public class Referee {
    @Id
    @Column(name = "ID_REFEREE")
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "referee")
    private List<Matches> matchesList;

    public List<Matches> getMatchesList() {
        return matchesList;
    }

    public void setMatchesList(List<Matches> matchesList) {
        this.matchesList = matchesList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCounrty() {
        return counrty;
    }

    public void setCounrty(String counrty) {
        this.counrty = counrty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
