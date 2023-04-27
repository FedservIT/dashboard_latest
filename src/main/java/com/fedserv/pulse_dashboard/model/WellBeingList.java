package com.fedserv.pulse_dashboard.model;

import java.util.List;

public class WellBeingList {
    private List<WellBeing> data;
    private Integer index;
    private String type;

    public List<WellBeing> getData() {
        return data;
    }

    public void setData(List<WellBeing> data) {
        this.data = data;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
