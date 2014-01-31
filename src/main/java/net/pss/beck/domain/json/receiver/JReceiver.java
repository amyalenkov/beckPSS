package net.pss.beck.domain.json.receiver;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by amyalenkov on 03.01.14.
 */
public class JReceiver implements Serializable{
    @JsonProperty("info")
    private JInfo info;

    @JsonProperty("markers")
    private List<JMarker> markers;

    public JReceiver(JInfo info, List<JMarker> markers) {
        this.info = info;
        this.markers = markers;
    }

    public JReceiver(){

    }

    public JInfo getInfo() {
        return info;
    }

    public void setInfo(JInfo info) {
        this.info = info;
    }

    public List<JMarker> getMarkers() {
        return markers;
    }

    public void setMarkers(List<JMarker> markers) {
        this.markers = markers;
    }

}
