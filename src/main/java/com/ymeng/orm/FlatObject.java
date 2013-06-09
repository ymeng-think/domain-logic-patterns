package com.ymeng.orm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.ymeng.orm.Database.INVALID_ID;
import static com.ymeng.util.Collections.copy;

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
        Set<String> fields = copy(fieldMap.keySet());
        if (isNew()) {
            excludeKeyFields(fields);
        }
        return fields;
    }

    private void excludeKeyFields(Set<String> fields) {
        fields.remove("id");
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
