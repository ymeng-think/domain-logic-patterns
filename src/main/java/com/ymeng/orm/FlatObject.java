package com.ymeng.orm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public Set<String> primaryKeys() {
        return copy(primaryKeys);
    }

    public Object value(String fieldName) {
        return fieldMap.get(fieldName);
    }

    public String tableName() {
        return tableName;
    }

    public boolean isNew() {
        return fieldMap.get("id").equals(INVALID_ID);
    }

    public FlatObject clone() {
        FlatObject newObject = new FlatObject(this.tableName());
        copy(this.fieldMap, newObject.fieldMap);
        copy(this.primaryKeys, newObject.primaryKeys);

        return newObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FlatObject that = (FlatObject) o;

        if (fieldMap != null ? !fieldMap.equals(that.fieldMap) : that.fieldMap != null) {
            return false;
        }
        if (primaryKeys != null ? !primaryKeys.equals(that.primaryKeys) : that.primaryKeys != null) {
            return false;
        }
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) {
            return false;
        }

        return true;
    }

    private void excludePrimaryKeys(Set<String> fields) {
        fields.removeAll(primaryKeys);
    }
}
