package net.pss.beck.domain.json.sender;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by amyalenkov on 25.01.14.
 */
public class JPlayer {
    @JsonProperty("id")
    private String id;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("number")
    private String number;

    public JPlayer() {
    }

    public JPlayer(String id, String lastName, String firstName, String number) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
