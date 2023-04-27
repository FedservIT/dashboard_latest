package com.fedserv.pulse_dashboard.model;

public class Announcement {
    String dept_code;
    String entry_date;
    String filenetID;
    String title;

    public String getDept_code() {
        return dept_code;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    public String getFilenetID() {
        return filenetID;
    }

    public void setFilenetID(String filenetID) {
        this.filenetID = filenetID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
