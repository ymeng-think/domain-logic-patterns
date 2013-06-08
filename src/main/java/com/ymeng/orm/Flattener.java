package com.ymeng.orm;

import com.ymeng.orm.extracting.TableNameExtractor;

import java.lang.reflect.Field;

public class Flattener {

    private final Object target;

    public Flattener(Object target) {
        this.target = target;
    }

    public FlatObjectCollection flatten() {
        String tableName = extractTableName();
        Field[] fields = extractFields();
        FlatObject flatObject = convertToFlatObject(tableName, fields);

        FlatObjectCollection flatObjects = new FlatObjectCollection();
        flatObjects.add(flatObject);

        return flatObjects;
    }

    private FlatObject convertToFlatObject(String tableName, Field[] fields) {
        FlatObject flatObject = new FlatObject(tableName);

        for (Field field : fields) {
            field.setAccessible(true);
            flatObject.registerField(field.getName(), getFieldValue(field));
        }

        return flatObject;
    }

    private Object getFieldValue(Field field) {
        try {
            return field.get(target);
        } catch (IllegalAccessException e) {
            throw new FieldValueAccessException();
        }
    }

    private String extractTableName() {
        TableNameExtractor extractor = new TableNameExtractor(target.getClass());
        return extractor.extract();
    }

    private Field[] extractFields() {
        Class<?> targetClass = target.getClass();
        return targetClass.getDeclaredFields();
    }

}
