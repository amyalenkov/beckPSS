package net.pss.beck.domain.json.receiver;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by amyalenkov on 03.01.14.
 */
public class JMarker implements Serializable{
    @JsonProperty("ID")
    private String id;
    @JsonProperty("BasicAction")
    private String basicAction;
    @JsonProperty("AddAction")
    private String addAction;
    @JsonProperty("PartBody")
    private String PartBody;
    @JsonProperty("Player1")
    private String player1;
    @JsonProperty("Team1")
    private String team1;
    @JsonProperty("Player2")
    private String player2;
    @JsonProperty("Team2")
    private String team2;
    @JsonProperty("x")
    private String x;
    @JsonProperty("y")
    private String y;
    @JsonProperty("Time")
    private String time;

    public JMarker(String id, String basicAction, String addAction,
                   String PartBody, String player1, String team1,
                   String player2, String team2, String x, String y, String time) {
        this.id = id;
        this.basicAction = basicAction;
        this.addAction = addAction;
        this.PartBody = PartBody;
        this.player1 = player1;
        this.team1 = team1;
        this.player2 = player2;
        this.team2 = team2;
        this.x = x;
        this.y = y;
        this.time = time;
    }

    public JMarker(){

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
        return PartBody;
    }

    public void setPartBody(String PartBody) {
        this.PartBody = PartBody;
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

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
