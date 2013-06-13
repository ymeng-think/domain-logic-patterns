package com.ymeng.orm.extracting;

import com.ymeng.orm.annotation.Table;

public class TableNameExtractor {

    private final Class<?> clazz;

    public TableNameExtractor(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String extract() {
        if (isMarkedByTable()) {
            return extractTableNameFromAnnotation();
        }
        return clazz.getSimpleName();
    }

    private boolean isMarkedByTable() {
        Table annotation = clazz.getAnnotation(Table.class);
        return annotation != null;
    }

    private String extractTableNameFromAnnotation() {
        Table annotation = clazz.getAnnotation(Table.class);
        return annotation.name();
    }
}
