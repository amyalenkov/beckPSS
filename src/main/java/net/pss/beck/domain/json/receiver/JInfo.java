package net.pss.beck.domain.json.receiver;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by amyalenkov on 03.01.14.
 */
public class JInfo implements Serializable{
    @JsonProperty("matchId")
    private String matchId;

    @JsonProperty("timeId")
    private String timeId;

    @JsonProperty("userName")
    private String userName;

    public JInfo(String matchId, String timeId, String userName) {
        this.matchId = matchId;
        this.timeId = timeId;
        this.userName = userName;
    }

    public JInfo(){

    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
