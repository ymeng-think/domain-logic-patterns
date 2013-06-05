package com.ymeng.pattern.transactionscript;

import com.ymeng.pattern.common.Money;
import com.ymeng.pattern.database.QueryException;
import com.ymeng.pattern.transactionscript.sql.Gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static com.ymeng.pattern.common.DateUtil.addDays;

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
        } finally {
            gateway.close();
        }
    }

    public void calculateRevenueRecognitions(long contractNumber) {
        ResultSet contracts = gateway.findContract(contractNumber);

        Money totalRevenue = null;
        Date recognitionDate = null;
        String type = null;
        try {
            contracts.next();

            totalRevenue = Money.dollars(contracts.getDouble("revenue"));
            recognitionDate = new Date(contracts.getDate("date_signed").getTime());
            type = contracts.getString("type");
        } catch (SQLException e) {
            throw new QueryException(e);
        } finally {
            gateway.close();
        }

        if (type.equals("S")) {
            Money[] allocation = totalRevenue.allocate(3);
            gateway.insertRecognition(contractNumber, allocation[0], recognitionDate);
            gateway.insertRecognition(contractNumber, allocation[1], addDays(recognitionDate, 60));
            gateway.insertRecognition(contractNumber, allocation[2], addDays(recognitionDate, 90));
        } else if (type.equals("W")) {
            gateway.insertRecognition(contractNumber, totalRevenue, recognitionDate);
        } else if (type.equals("D")) {
            Money[] allocation = totalRevenue.allocate(3);
            gateway.insertRecognition(contractNumber, allocation[0], recognitionDate);
            gateway.insertRecognition(contractNumber, allocation[1], addDays(recognitionDate, 30));
            gateway.insertRecognition(contractNumber, allocation[2], addDays(recognitionDate, 60));
        }
    }

}
