package net.pss.beck.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by amyalenkov on 26.01.14.
 */
@Entity
@Table(name = "MATCH_ANALYSE")
public class MatchAnalyse {

    @Id
    @Column(name = "MATCH_TIME_ID")
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MATCH_ID")
    private Matches match;

    @Column(name = "TIME_ID")
    private String time;

    @Column(name = "STATUS")
    private String status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchTime")
    private List<PushedMarkers> pushedMarkers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchTime")
    private List<Markers> markers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Matches getMatch() {
        return match;
    }

    public void setMatch(Matches match) {
        this.match = match;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
