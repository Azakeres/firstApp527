package com.example.testapp;

public class Items {
    Integer id;
    String title;
    String memo;

    public Items(){}

    public Items(String title, String memo){
        this.title=title;
        this.memo=memo;
    }

    public String getMemo() {
        return memo;
    }

    public String getTitle() {
        return title;
    }

    public Integer getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
