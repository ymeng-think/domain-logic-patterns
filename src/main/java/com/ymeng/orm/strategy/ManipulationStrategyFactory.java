package com.ymeng.orm.strategy;

import com.ymeng.orm.FlatObject;

import java.sql.Connection;

public class ManipulationStrategyFactory {

    public static DataManipulationStrategy generateManipulationStrategy(FlatObject object, Connection connection) {
        if (object.isNew()) {
            return new DataInsertStrategy(connection, object);
        }
        return null;
    }
}
