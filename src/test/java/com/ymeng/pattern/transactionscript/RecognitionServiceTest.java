package com.ymeng.pattern.transactionscript;

import com.ymeng.pattern.common.Money;
import com.ymeng.database.Contract;
import com.ymeng.database.DatabaseTest;
import com.ymeng.database.Product;
import com.ymeng.database.Recognition;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static com.ymeng.builder.DateBuilder.date;
import static com.ymeng.matcher.Matchers.nextRowContains;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RecognitionServiceTest extends DatabaseTest {

    private Recognition recognition;
    private RecognitionService service;
    private Contract contract;
    private Product product;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        product = new Product(connection);
        contract = new Contract(connection);
        recognition = new Recognition(connection);
        service = new RecognitionService(connection);
    }

    @Test
    public void should_get_recognition_revenue() {
        Date asOf = date(2012, 2, 1);
        recognition.insert(1L, 100, asOf);

        Money money = service.recognitionRevenue(1L, asOf);

        assertThat(money, is(Money.dollars(100)));
    }

    @Test
    public void should_calculate_revenue_recognitions_of_a_contract_about_word_processors() throws SQLException {
        long contractID = 1L;
        Date dateSigned = date(2012, 2, 1);
        double revenue = 100.0;
        buildContract("Microsoft Word", "W", contractID, revenue, dateSigned);

        service.calculateRevenueRecognitions(contractID);

        ResultSet result = recognition.findAll();
        assertThat(result, nextRowContains(contractID, revenue, dateSigned));
        assertThat(result.next(), is(false));
        result.close();
    }

    @Test
    public void should_calculate_revenue_recognitions_of_a_contract_about_spreadsheets() throws SQLException {
        long contractID = 1L;
        Date dateSigned = date(2012, 1, 1);
        double revenue = 120.0;
        buildContract("Microsoft Excel", "S", contractID, revenue, dateSigned);

        service.calculateRevenueRecognitions(contractID);

        ResultSet result = recognition.findAll();
        assertThat(result, nextRowContains(contractID, 40.0, date(2012, 1, 1)));
        assertThat(result, nextRowContains(contractID, 40.0, date(2012, 3, 1)));
        assertThat(result, nextRowContains(contractID, 40.0, date(2012, 3, 31)));
        assertThat(result.next(), is(false));
        result.close();
    }

    @Test
    public void should_calculate_revenue_recognitions_of_a_contract_about_databases() throws SQLException {
        long contractID = 1L;
        Date dateSigned = date(2012, 1, 1);
        double revenue = 120.0;
        buildContract("Microsoft SQL Server", "D", contractID, revenue, dateSigned);

        service.calculateRevenueRecognitions(contractID);

        ResultSet result = recognition.findAll();
        assertThat(result, nextRowContains(contractID, 40.0, date(2012, 1, 1)));
        assertThat(result, nextRowContains(contractID, 40.0, date(2012, 1, 31)));
        assertThat(result, nextRowContains(contractID, 40.0, date(2012, 3, 1)));
        assertThat(result.next(), is(false));
        result.close();
    }

    private void buildContract(String productName, String productType, long contractID, double revenue, Date dateSigned) {
        product.insert(1L, productName, productType);
        contract.insert(contractID, 1L, revenue, dateSigned);
    }
}
