package com.ymeng.orm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FlatObject {

    private Map<String, Object> fieldMap = new HashMap<String, Object>();

    public void registerField(String name, Object value) {
        fieldMap.put(name, value);
    }

    public Set<String> fields() {
        return fieldMap.keySet();
    }

    public Object getValue(String fieldName) {
        return fieldMap.get(fieldName);
    }
}
