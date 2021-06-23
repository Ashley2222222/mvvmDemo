package com.gci.mvvmdemo;

public class User {

    private String name;
    private String headerUrl;

    public User(String name, String headerUrl){
        this.name = name;
        this.headerUrl = headerUrl;
    }

    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }
}
