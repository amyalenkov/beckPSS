package net.pss.beck.domain;

import javax.persistence.*;

/**
 * Created by amyalenkov on 26.01.14.
 */
@Entity
@Table(name = "PUSHED_MARKERS")
public class PushedMarkers {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MATCH_TIME_ID")
    private MatchAnalyse matchTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users user;

    @Column(name = "MARKER_FIRST")
    private String markerFirst;

    @Column(name = "MARKER_LAST")
    private String markerLast;

    @Column(name = "FULL_TIME")
    private Boolean fullTime;

    public MatchAnalyse getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(MatchAnalyse matchTime) {
        this.matchTime = matchTime;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getMarkerFirst() {
        return markerFirst;
    }

    public void setMarkerFirst(String markerFirst) {
        this.markerFirst = markerFirst;
    }

    public String getMarkerLast() {
        return markerLast;
    }

    public void setMarkerLast(String markerLast) {
        this.markerLast = markerLast;
    }

    public Boolean getFullTime() {
        return fullTime;
    }

    public void setFullTime(Boolean fullTime) {
        this.fullTime = fullTime;
    }
}
