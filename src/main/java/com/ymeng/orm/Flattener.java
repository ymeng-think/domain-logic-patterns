package com.ymeng.orm;

import com.ymeng.orm.extracting.FieldNameExtractor;
import com.ymeng.orm.extracting.PrimaryKeyExtractor;
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
        String[] primaryKeys = extractPrimaryKeys(fields);

        FlatObject flatObject = convertToFlatObject(tableName, fields);
        flatObject.registerPrimaryKeys(primaryKeys);

        FlatObjectCollection flatObjects = new FlatObjectCollection();
        flatObjects.add(flatObject);

        return flatObjects;
    }

    private String[] extractPrimaryKeys(Field[] fields) {
        PrimaryKeyExtractor extractor = new PrimaryKeyExtractor(fields);
        return extractor.extract();
    }

    private FlatObject convertToFlatObject(String tableName, Field[] fields) {
        FlatObject flatObject = new FlatObject(tableName);

        for (Field field : fields) {
            field.setAccessible(true);
            flatObject.registerField(extractFieldName(field), getFieldValue(field));
        }

        return flatObject;
    }

    private String extractFieldName(Field field) {
        FieldNameExtractor extractor = new FieldNameExtractor(field);
        return extractor.extract();
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
