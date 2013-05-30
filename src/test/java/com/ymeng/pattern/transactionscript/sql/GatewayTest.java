package com.ymeng.pattern.transactionscript.sql;

import com.ymeng.pattern.database.Contract;
import com.ymeng.pattern.database.DatabaseTest;
import com.ymeng.pattern.database.Product;
import com.ymeng.pattern.database.Recognition;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ymeng.builder.DateBuilder.date;
import static com.ymeng.matcher.DateEqualMatcher.eq;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GatewayTest extends DatabaseTest {

    private Gateway gateway;
    private Recognition recognition;
    private Contract contract;
    private Product product;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        recognition = new Recognition(connection);
        contract = new Contract(connection);
        product = new Product(connection);
        gateway = new Gateway(connection);
    }

    @Test
    public void should_find_recognitions_from_database() throws SQLException {
        recognition.insert(1L, 100, date(2012, 2, 1));
        recognition.insert(2L, 100, date(2012, 2, 2));

        ResultSet result = gateway.findRecognitionsFor(1L, date(2012, 2, 1));

        assertThat(result.next(), is(true));
        assertThat(result.getDouble(1), is(100.0));
        assertThat(result.next(), is(false));
    }

    @Test
    public void should_NOT_find_any_recognitions_when_key_is_NOT_match() throws SQLException {
        recognition.insert(1L, 100, date(2012, 2, 1));

        ResultSet result = gateway.findRecognitionsFor(1L, date(2012, 1, 31));

        assertThat(result.next(), is(false));
    }

    @Test
    public void should_find_contract_from_database() throws SQLException {
        product.insert(1L, "spreadsheet", "S");
        contract.insert(1L, 1L, 100, date(2011, 1, 1));
        contract.insert(2L, 1L, 500, date(2011, 10, 1));

        ResultSet result = gateway.findContract(1L);

        assertThat(result.next(), is(true));
        assertThat(result.getDouble(1), is(100.0));
        assertThat(result.getDate(2), eq(date(2011, 1, 1)));
        assertThat(result.getString(3), is("S"));
        assertThat(result.next(), is(false));
    }

    @Test
    public void should_NOT_find_any_contract_when_key_is_NOT_match() throws SQLException {
        product.insert(1L, "spreadsheet", "S");
        contract.insert(1L, 1L, 100, date(2011, 1, 1));

        ResultSet result = gateway.findContract(2L);

        assertThat(result.next(), is(false));
    }

}
