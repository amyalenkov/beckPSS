package net.pss.beck.domain.json.sender;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by amyalenkov on 25.01.14.
 */
public class JMatch {
    @JsonProperty("id")
    private String id;

    @JsonProperty("tourName")
    private String tourName;

    @JsonProperty("stage")
    private String stage;

    @JsonProperty("data")
    private String data;

    @JsonProperty("referee")
    private String referee;

    @JsonProperty("team1")
    private JTeam team1;

    @JsonProperty("team2")
    private JTeam team2;


    public JMatch() {
    }

    public JMatch(JTeam team1, JTeam team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public JTeam getTeam1() {
        return team1;
    }

    public void setTeam1(JTeam team1) {
        this.team1 = team1;
    }

    public JTeam getTeam2() {
        return team2;
    }

    public void setTeam2(JTeam team2) {
        this.team2 = team2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
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

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }
}
