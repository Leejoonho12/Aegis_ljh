package com.example.myapplication1213;

public class TellVO {

    private String name;
    private String tnum;

    public TellVO(String name, String tnum) {
        this.name = name;
        this.tnum = tnum;
    }

    public TellVO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTnum() {
        return tnum;
    }

    public void setTnum(String tnum) {
        this.tnum = tnum;
    }
}
