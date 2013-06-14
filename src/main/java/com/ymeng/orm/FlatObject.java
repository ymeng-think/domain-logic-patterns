package com.ymeng.orm;

import java.sql.Connection;
import java.util.*;

import static com.ymeng.orm.Database.INVALID_ID;
import static com.ymeng.util.Collections.copy;

public class FlatObject {

    private final Map<String, Object> fieldMap = new HashMap<String, Object>();
    private final Set<String> primaryKeys = new HashSet<String>();
    private final String tableName;

    public FlatObject(String tableName) {
        this.tableName = tableName.toLowerCase();
    }

    public void registerField(String name, Object value) {
        fieldMap.put(name.toLowerCase(), value);
    }

    public void registerPrimaryKeys(String... keys) {
        for (String key : keys) {
            primaryKeys.add(key.toLowerCase());
        }
    }

    public Set<String> fields() {
        Set<String> fields = copy(fieldMap.keySet());
        if (isNew()) {
            excludePrimaryKeys(fields);
        }
        return fields;
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

    private void excludePrimaryKeys(Set<String> fields) {
        fields.removeAll(primaryKeys);
    }
}
