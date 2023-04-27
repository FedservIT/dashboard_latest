package com.fedserv.pulse_dashboard.model;

import java.util.List;

public class CeoSpeakLike {
    List<String> data;
    String speak_id;

    public String getSpeak_id() {
        return speak_id;
    }

    public void setSpeak_id(String speak_id) {
        this.speak_id = speak_id;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}

