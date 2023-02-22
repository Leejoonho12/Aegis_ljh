package com.example.myapplication1213;

public class scannerVO {

    private String scanner;
    private String name;
    private String beacon;

    public scannerVO(String scanner, String name, String beacon) {
        this.scanner = scanner;
        this.name = name;
        this.beacon = beacon;
    }

    public scannerVO() {
    }

    public String getScanner() {
        return scanner;
    }

    public void setScanner(String scanner) {
        this.scanner = scanner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeacon() {
        return beacon;
    }

    public void setBeacon(String beacon) {
        this.beacon = beacon;
    }
}
