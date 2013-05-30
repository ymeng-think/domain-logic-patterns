package com.ymeng.pattern.transactionscript;

import com.ymeng.pattern.database.DatabaseTest;
import com.ymeng.pattern.database.Recognition;
import org.junit.Test;

import java.util.Date;

import static com.ymeng.builder.DateBuilder.date;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecognitionServiceTest extends DatabaseTest {

    private Recognition recognition;
    private RecognitionService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();

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
}
