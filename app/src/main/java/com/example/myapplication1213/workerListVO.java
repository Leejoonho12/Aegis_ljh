package com.example.myapplication1213;

public class workerListVO {

    private String name;
    private String id;

    public workerListVO(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public workerListVO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
