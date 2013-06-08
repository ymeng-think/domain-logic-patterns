package com.ymeng.orm.dummy;

import com.ymeng.orm.annotation.Table;

@Table(name = "Animal")
public class Donkey {

    private long id;
    private String name;

    public Donkey(String name) {
        this.name = name;
    }
}
