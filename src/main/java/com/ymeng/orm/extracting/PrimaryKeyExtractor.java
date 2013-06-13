package com.ymeng.orm.extracting;

import com.ymeng.orm.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PrimaryKeyExtractor {

    private final Field[] fields;

    public PrimaryKeyExtractor(Field[] fields) {
        this.fields = fields;
    }

    public String[] extract() {
        if (!hasFieldsMarkedByPrimaryKey()) {
            return new String[] {"id"};
        }

        return getMarkedPrimaryKeys();
    }

    private boolean hasFieldsMarkedByPrimaryKey() {
        for (Field field : fields) {
            if (isMarkedByPrimaryKey(field)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMarkedByPrimaryKey(Field field) {
        PrimaryKey annotation = field.getAnnotation(PrimaryKey.class);
        return annotation != null;
    }

    private String[] getMarkedPrimaryKeys() {
        List<String> keys = new ArrayList<String>();
        for (Field field : fields) {
            if (isMarkedByPrimaryKey(field)) {
                keys.add(field.getName());
            }
        }

        return keys.toArray(new String[keys.size()]);
    }
}
