package com.ymeng.orm;

public class DummyProduct {

    private long id;
    private String name;
    private String type;

    public DummyProduct(String name, String type) {

    }

    public void save() {
        Database.current().save(this);
    }
}
