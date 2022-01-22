package com.bdappmaniac.bdapp.model;

import androidx.appcompat.app.AppCompatActivity;

public class TaskItem extends AppCompatActivity {
    private String numberTxt;
    private String taskTxt;

    public TaskItem(String numberTxt, String taskTxt) {
        this.numberTxt = numberTxt;
        this.taskTxt = taskTxt;
    }

    public String getNumberTxt() {
        return numberTxt;
    }

    public void setNumberTxt(String numberTxt) {
        this.numberTxt = numberTxt;
    }

    public String getTaskTxt() {
        return taskTxt;
    }

    public void setTaskTxt(String taskTxt) {
        this.taskTxt = taskTxt;
    }
}
