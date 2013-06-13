package com.ymeng.orm.dummy;

import com.ymeng.orm.annotation.Field;
import com.ymeng.orm.annotation.PrimaryKey;
import com.ymeng.orm.annotation.Table;

import static com.ymeng.orm.Database.INVALID_ID;

@Table(name = "Animal")
public class Donkey {

    private long id = INVALID_ID;

    @PrimaryKey
    private long ssid = INVALID_ID;

    @Field(name = "nick_name")
    private String name;

    public Donkey(String name) {
        this.name = name;
    }
}
