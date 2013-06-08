package com.ymeng.orm.strategy;

import com.ymeng.orm.FlatObject;

import java.sql.Connection;

public class DataInsertStrategy implements DataManipulationStrategy {

    public DataInsertStrategy(Connection connection, FlatObject object) {
    }

    @Override
    public void execute() {

    }
}
