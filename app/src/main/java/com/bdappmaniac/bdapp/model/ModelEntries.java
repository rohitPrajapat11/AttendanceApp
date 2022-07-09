package com.bdappmaniac.bdapp.model;

public class ModelEntries {
    private String Check_in;
    private String Check_out;


    public ModelEntries(String check_in, String check_out) {
        Check_in = check_in;
        Check_out = check_out;

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


}
