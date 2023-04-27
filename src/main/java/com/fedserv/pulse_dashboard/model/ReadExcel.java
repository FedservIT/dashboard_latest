package com.fedserv.pulse_dashboard.model;

public class ReadExcel {
    String date;
    String title;
    String data1;

    public ReadExcel(String date, String title, String data1) {
        this.date = date;
        this.title = title;
        this.data1 = data1;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ReadExcel{");
        sb.append("date='").append(date).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", data1='").append(data1).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
