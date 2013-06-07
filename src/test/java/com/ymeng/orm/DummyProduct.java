package com.ymeng.orm;

import static com.ymeng.orm.Database.INVALID_ID;
import static com.ymeng.orm.Database.current;

public class DummyProduct {

    private long id = INVALID_ID;
    private String name;
    private String type;

    public DummyProduct(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void save() {
        current().save(this);
    }
}
