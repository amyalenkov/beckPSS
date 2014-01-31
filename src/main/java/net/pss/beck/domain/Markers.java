package net.pss.beck.domain;

import javax.persistence.*;

/**
 * Created by amyalenkov on 26.01.14.
 */
@Entity
@Table(name = "MARKERS")
public class Markers {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MATCH_TIME_ID")
    private MatchAnalyse matchTime;

    @Column(name = "ID_MARKER")
    private String idMarker;

    @Column(name = "BASIC_ACTION")
    private String basicAction;

    @Column(name = "ADD_ACTION")
    private String addAction;

    @Column(name = "PART_BODY")
    private String partBody;

    @Column(name = "Player1")
    private String player1;

    @Column(name = "Team1")
    private String team1;

    @Column(name = "Player2")
    private String player2;

    @Column(name = "Team2")
    private String team2;

    @Column(name = "Time")
    private String time;

    public String getIdMarker() {
        return idMarker;
    }

    public void setIdMarker(String idMarker) {
        this.idMarker = idMarker;
    }

    public String getBasicAction() {
        return basicAction;
    }

    public void setBasicAction(String basicAction) {
        this.basicAction = basicAction;
    }

    public String getAddAction() {
        return addAction;
    }

    public void setAddAction(String addAction) {
        this.addAction = addAction;
    }

    public String getPartBody() {
        return partBody;
    }

    public void setPartBody(String partBody) {
        this.partBody = partBody;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MatchAnalyse getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(MatchAnalyse matchTime) {
        this.matchTime = matchTime;
    }
}
