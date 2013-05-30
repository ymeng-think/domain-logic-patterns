package com.ymeng.pattern.transactionscript;

import com.ymeng.pattern.database.DatabaseTest;
import com.ymeng.pattern.database.Recognition;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ymeng.builder.DateBuilder.date;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GatewayTest extends DatabaseTest {

    private Gateway gateway;
    private Recognition recognition;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        recognition = new Recognition(connection);
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
        ResultSet result = gateway.findRecognitionsFor(1L, date(2012, 2, 1));

        assertThat(result.next(), is(false));
    }

}
