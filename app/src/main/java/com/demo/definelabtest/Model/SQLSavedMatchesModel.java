package com.demo.definelabtest.Model;

public class SQLSavedMatchesModel {

    private String id,name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SQLSavedMatchesModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public SQLSavedMatchesModel() {
    }
}
