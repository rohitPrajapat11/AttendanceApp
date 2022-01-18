package com.bdappmaniac.bdapp.model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarDateModel {
    private Date data;

    public CalendarDateModel(Date data) {
        this.data = data;
    }

    public Date getDates() {
        return data;
    }

    public String getDay() {
        return new SimpleDateFormat("EE", Locale.getDefault()).format(data);
    }
}
