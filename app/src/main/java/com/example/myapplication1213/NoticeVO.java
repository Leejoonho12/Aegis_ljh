package com.example.myapplication1213;

public class NoticeVO {

    private String title;
    private String content;
    private String writer;
    private String day;

    public NoticeVO(String title, String content, String writer, String day) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.day = day;
    }

    public NoticeVO(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
