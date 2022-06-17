package com.bdappmaniac.bdapp.model;

import java.io.Serializable;

public class ModelEmpTask implements Serializable {
    private  String date;

    public ModelEmpTask(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
