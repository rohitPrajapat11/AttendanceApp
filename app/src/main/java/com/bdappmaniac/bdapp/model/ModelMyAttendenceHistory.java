package com.bdappmaniac.bdapp.model;

public class ModelMyAttendenceHistory {

    private String Day;
    private String Date;

    public ModelMyAttendenceHistory(String day, String date) {
        Day = day;
        Date = date;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
