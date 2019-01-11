package com.lorispinna.test.models;

public class Repo {
    private Integer id;
    private String full_name;

    public Repo(Integer id, String full_name) {
        this.id = id;
        this.full_name = full_name;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    @Override
    public String toString() {
        return this.getFullName() + " " + this.getId();
    }
}
