package com.ymeng.orm.extracting;

import com.ymeng.orm.annotation.Field;

public class FieldNameExtractor {

    private final java.lang.reflect.Field field;

    public FieldNameExtractor(java.lang.reflect.Field field) {
        this.field = field;
    }

    public String extract() {
        if (isMarkedByField()) {
            return extractFieldNameFromAnnotation();
        }
        return field.getName();
    }

    private boolean isMarkedByField() {
        Field annotation = field.getAnnotation(Field.class);
        return annotation != null;
    }

    private String extractFieldNameFromAnnotation() {
        Field annotation = field.getAnnotation(Field.class);
        return annotation.name();
    }
}
