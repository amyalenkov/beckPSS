package net.pss.beck.domain.json.sender;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by amyalenkov on 25.01.14.
 */
public class JTeam {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("players")
    private List<JPlayer> players;

    public JTeam() {
    }

    public JTeam(String id, String name, List<JPlayer> players) {
        this.id = id;
        this.name = name;
        this.players = players;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<JPlayer> players) {
        this.players = players;
    }
}
