package com.ymeng.orm;

import java.util.*;

import static com.ymeng.orm.Database.INVALID_ID;
import static com.ymeng.util.Collections.copy;

public class FlatObject {

    private final Map<String, Object> fieldMap = new HashMap<String, Object>();
    private final Set<String> primaryKeys = new HashSet<String>();
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

    public void registerPrimaryKeys(String... keys) {
        Collections.addAll(primaryKeys, keys);
    }

    public boolean isNew() {
        return (Long)fieldMap.get("id") == INVALID_ID;
    }

    private void excludePrimaryKeys(Set<String> fields) {
        if (primaryKeys.size() == 0) {
            fields.remove("id");
            return;
        }

        fields.removeAll(primaryKeys);
    }
}
