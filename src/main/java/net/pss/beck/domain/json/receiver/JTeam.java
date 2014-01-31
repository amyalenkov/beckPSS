package net.pss.beck.domain.json.receiver;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by amyalenkov on 11.01.14.
 */
public class JTeam {

    @JsonProperty("teamId")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("counrty")
    private String counrty;

    public JTeam(){

    }

    public JTeam(Integer id, String name, String counrty) {
        this.id = id;
        this.name = name;
        this.counrty = counrty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCounrty() {
        return counrty;
    }

    public void setCounrty(String counrty) {
        this.counrty = counrty;
    }
}
