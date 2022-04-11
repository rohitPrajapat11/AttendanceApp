package com.bdappmaniac.bdapp.model;

public class AdminHomeModel {
    private int imageitem;

    public AdminHomeModel(int imageitem, String title) {
        this.imageitem = imageitem;
        this.title = title;
    }

    public int getImageitem() {
        return imageitem;
    }

    public void setImageitem(int imageitem) {
        this.imageitem = imageitem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
}
