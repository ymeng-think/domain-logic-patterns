package com.ymeng.pattern;

import com.ymeng.database.Contract;
import com.ymeng.database.Product;
import com.ymeng.database.Recognition;
import com.ymeng.pattern.transactionscript.RecognitionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static com.ymeng.builder.DateBuilder.date;
import static com.ymeng.database.DatabaseOperator.cleanAllTables;
import static com.ymeng.database.DatabaseOperator.connect;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        Connection connection = connect();
        cleanAllTables(connection);
        long contractID = 1L;
        buildContract(connection, "Microsoft Excel", "S", contractID, 120, date(2012, 1, 1));

        String patternName = readInPatternName("Please enter domain logic pattern: ");

        Pattern pattern = createPattern(patternName, connection);
        pattern.calculateRevenueRecognitions(contractID);

        printRevenueRecognitions(connection);

        connection.close();
    }

    private static void printRevenueRecognitions(Connection connection) throws SQLException {
        ResultSet resultSet = new Recognition(connection).findAll();

        while(resultSet.next()) {
            printRecognitionRow(resultSet);
        }

        resultSet.close();
    }

    private static void printRecognitionRow(ResultSet resultSet) throws SQLException {
        StringBuilder text = new StringBuilder();
        text.append("contract:").append(resultSet.getLong("contract")).append(", ");
        text.append("amount:").append(resultSet.getDouble("amount")).append(", ");
        text.append("recognized_on:").append(resultSet.getDate("recognized_on"));

        System.out.println(text);
    }

    private static String readInPatternName(String tip) throws IOException {
        System.out.print(tip);
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        return bufferRead.readLine();
    }

    private static Pattern createPattern(String patternName, Connection connection) {
        patternName = patternName.toLowerCase();
        if (patternName.equals("transaction script")) {
            return new TransactionScript(connection);
        }
        throw new IllegalArgumentException("Can NOT identify domain logic pattern name");
    }

    private static void buildContract(Connection connection, String productName, String productType, long contractID, double revenue, Date dateSigned) {
        final long productID = 1L;
        new Product(connection).insert(productID, productName, productType);
        new Contract(connection).insert(contractID, productID, revenue, dateSigned);
    }


    private static interface Pattern {
        void calculateRevenueRecognitions(long contractNumber);
    }

    private static class TransactionScript implements Pattern {

        private final RecognitionService service;

        public TransactionScript(Connection connection) {
            service = new RecognitionService(connection);
        }

        @Override
        public void calculateRevenueRecognitions(long contractNumber) {
            service.calculateRevenueRecognitions(contractNumber);
        }
    }
}
