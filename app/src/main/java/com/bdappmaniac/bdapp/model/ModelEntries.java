package com.bdappmaniac.bdapp.model;

public class ModelEntries {
    private String Check_in;
    private String Check_out;
    private String work_Hrs;

    public ModelEntries(String check_in, String check_out, String work_Hrs) {
        Check_in = check_in;
        Check_out = check_out;
        this.work_Hrs = work_Hrs;
    }

    public String getCheck_in() {
        return Check_in;
    }

    public void setCheck_in(String check_in) {
        Check_in = check_in;
    }

    public String getCheck_out() {
        return Check_out;
    }

    public void setCheck_out(String check_out) {
        Check_out = check_out;
    }

    public String getWork_Hrs() {
        return work_Hrs;
    }

    public void setWork_Hrs(String work_Hrs) {
        this.work_Hrs = work_Hrs;
    }
}
