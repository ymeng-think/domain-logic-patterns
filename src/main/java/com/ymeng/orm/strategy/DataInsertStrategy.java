package com.ymeng.orm.strategy;

import com.ymeng.orm.FlatObject;
import com.ymeng.pattern.database.InsertRowException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.ymeng.util.CollectionWrapper.collect;

public class DataInsertStrategy implements DataManipulationStrategy {

    private final Connection connection;
    private final FlatObject object;

    public DataInsertStrategy(Connection connection, FlatObject object) {
        this.connection = connection;
        this.object = object;
    }

    @Override
    public void execute() {
        PreparedStatement command = null;
        try {
            command = connection.prepareStatement(generateInsertStatement());
            setParameters(command);
            command.execute();
        } catch (SQLException e) {
            throw new InsertRowException();
        } finally {
            close(command);
        }
    }

    private String generateInsertStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("INSERT INTO ").append(object.tableName())
                .append("(").append(generateFieldString()).append(") ")
                .append("VALUE(").append(generateParameterString()).append(")");

        return statement.toString();
    }

    private String generateParameterString() {
        List<String> params = newList(object.fields().size(), "?");
        return collect(params).join(",");
    }

    private <T> List<T> newList(int size, T defaultValue) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            list.add(defaultValue);
        }

        return list;
    }

    private void setParameters(PreparedStatement command) throws SQLException {
        Set<String> fields = object.fields();
        int columnIndex = 1;
        for (String field : fields) {
            command.setObject(columnIndex++, object.value(field));
        }
    }

    private String generateFieldString() {
        return collect(object.fields()).join(",");
    }

    private void close(PreparedStatement command) {
        try {
            command.close();
        } catch (SQLException e) {}
    }
}
