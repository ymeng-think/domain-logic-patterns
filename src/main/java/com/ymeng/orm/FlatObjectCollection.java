package com.ymeng.orm;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.ymeng.orm.strategy.ManipulationStrategyFactory.generateManipulationStrategy;

public class FlatObjectCollection {

    private List<FlatObject> objects = new ArrayList<FlatObject>();

    public FlatObject get(int index) {
        return objects.get(index);
    }

    public void add(FlatObject object) {
        objects.add(object);
    }

    public int size() {
        return objects.size();
    }

    public void save(Connection connection) {
        for (FlatObject object : objects) {
            generateManipulationStrategy(object, connection).execute();
        }
    }
}
