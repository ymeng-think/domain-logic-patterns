package com.ymeng.orm;

import com.ymeng.orm.extracting.TableNameExtractor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Flattener {

    private final Object target;

    public Flattener(Object target) {
        this.target = target;
    }

    public List<FlatObject> flatten() {
        String tableName = extractTableName();
        Field[] fields = extractFields();
        FlatObject flatObject = convertToFlatObject(tableName, fields);

        ArrayList<FlatObject> flatObjects = new ArrayList<FlatObject>();
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
