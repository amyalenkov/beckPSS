package net.pss.beck.parser;

/**
 * Created by amyalenkov on 20.01.14.
 */
public class MatchParser {
    private String data;
    private String stage;
    private String team1;
    private String team2;
    private String refereeLink;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getRefereeLink() {
        return refereeLink;
    }

    public void setRefereeLink(String refereeLink) {
        this.refereeLink = refereeLink;
    }
}
