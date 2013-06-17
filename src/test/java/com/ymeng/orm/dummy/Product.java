package com.ymeng.orm.dummy;

import static com.ymeng.orm.Database.INVALID_ID;
import static com.ymeng.orm.Database.current;

public class Product {

    private int id = INVALID_ID;
    private String name;
    private String type;

    public Product(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void save() {
        current().save(this);
    }

    public static Product load(int id) {
        return current().loadById(Product.class, id);
    }
}
