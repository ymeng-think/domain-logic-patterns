package com.ymeng.orm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.ymeng.orm.Database.INVALID_ID;

public class FlatObject {

    private final Map<String, Object> fieldMap = new HashMap<String, Object>();
    private final String tableName;

    public FlatObject(String tableName) {
        this.tableName = tableName;
    }

    public void registerField(String name, Object value) {
        fieldMap.put(name, value);
    }

    public Set<String> fields() {
        return fieldMap.keySet();
    }

    public Object value(String fieldName) {
        return fieldMap.get(fieldName);
    }

    public String tableName() {
        return tableName;
    }

    public boolean isNew() {
        return (Long)fieldMap.get("id") == INVALID_ID;
    }
}
