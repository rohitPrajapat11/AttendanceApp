package com.bdappmaniac.bdapp.model;

public class ModelHolidayItems {
    private String occasion;
    private String day;
    private String date;

    public ModelHolidayItems(String occasion, String day, String date) {
        this.occasion = occasion;
        this.day = day;
        this.date = date;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
