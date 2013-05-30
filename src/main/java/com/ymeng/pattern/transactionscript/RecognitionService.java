package com.ymeng.pattern.transactionscript;

import com.ymeng.pattern.database.QueryException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RecognitionService {

    private Gateway gateway;

    public RecognitionService(Connection connection) {
        this.gateway = new Gateway(connection);
    }

    public Money recognitionRevenue(long contractNumber, Date asOf) {
        Money result = Money.dollars(0);

        ResultSet resultSet = gateway.findRecognitionsFor(contractNumber, asOf);
        try {
            while(resultSet.next()) {
                result = result.add(Money.dollars(resultSet.getDouble("amount")));
            }
            return result;
        } catch (SQLException e) {
            throw new QueryException(e);
        }

    }
}
