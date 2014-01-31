package net.pss.beck.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by amyalenkov on 26.01.14.
 */
@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "USER_ROLE")
    private String role;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<PushedMarkers> pushedMarkers;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}
