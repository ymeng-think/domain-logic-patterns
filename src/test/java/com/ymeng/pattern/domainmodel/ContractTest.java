package com.ymeng.pattern.domainmodel;

import com.ymeng.pattern.common.Money;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.ymeng.builder.DateBuilder.date;
import static com.ymeng.matcher.Matchers.eq;
import static com.ymeng.pattern.common.Money.dollars;
import static com.ymeng.pattern.domainmodel.ProductBuilder.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ContractTest {

    private Product msWord;
    private Product msExcel;
    private Product msSql;

    @Before
    public void setUp() throws Exception {
        msWord = MSWord().build();
        msExcel = MSExcel().build();
        msSql = MSSql().build();
    }

    @Test
    public void should_get_recognition_revenue() {
        Contract contract = new Contract(msWord, 100.0, date(2012, 1, 1));

        Money revenue = contract.recognizedRevenue();

        assertThat(revenue, is(dollars(100.0)));
    }

    @Test
    public void should_calculate_revenue_recognitions_of_a_contract_about_word_processors() {
        Contract contract = new Contract(msWord, 100.0, date(2012, 1, 1));

        List<RevenueRecognition> revenueRecognitions = contract.calculateRecognitions();

        assertThat(revenueRecognitions.size(), is(1));
        RevenueRecognition firstRecognition = revenueRecognitions.get(0);
        assertThat(firstRecognition.contract(), is(contract));
        assertThat(firstRecognition.amount(), is(contract.recognizedRevenue()));
        assertThat(firstRecognition.recognizedOn(), eq(contract.whenSigned()));
    }

    @Test
    public void should_calculate_revenue_recognitions_of_a_contract_about_spreadsheets() {
        Contract contract = new Contract(msExcel, 120.0, date(2012, 1, 1));

        List<RevenueRecognition> revenueRecognitions = contract.calculateRecognitions();

        assertThat(revenueRecognitions.size(), is(3));
        RevenueRecognition firstRecognition = revenueRecognitions.get(0);
        assertThat(firstRecognition.contract(), is(contract));
        assertThat(firstRecognition.amount(), is(dollars(40.0)));
        assertThat(firstRecognition.recognizedOn(), eq(date(2012, 1, 1)));
        RevenueRecognition secondRecognition = revenueRecognitions.get(1);
        assertThat(secondRecognition.contract(), is(contract));
        assertThat(secondRecognition.amount(), is(dollars(40.0)));
        assertThat(secondRecognition.recognizedOn(), eq(date(2012, 3, 1)));
        RevenueRecognition thirdRecognition = revenueRecognitions.get(2);
        assertThat(thirdRecognition.contract(), is(contract));
        assertThat(thirdRecognition.amount(), is(dollars(40.0)));
        assertThat(thirdRecognition.recognizedOn(), eq(date(2012, 3, 31)));
    }

    @Test
    public void should_calculate_revenue_recognitions_of_a_contract_about_databases() {
        Contract contract = new Contract(msSql, 120.0, date(2012, 1, 1));

        List<RevenueRecognition> revenueRecognitions = contract.calculateRecognitions();

        assertThat(revenueRecognitions.size(), is(3));
        RevenueRecognition firstRecognition = revenueRecognitions.get(0);
        assertThat(firstRecognition.contract(), is(contract));
        assertThat(firstRecognition.amount(), is(dollars(40.0)));
        assertThat(firstRecognition.recognizedOn(), eq(date(2012, 1, 1)));
        RevenueRecognition secondRecognition = revenueRecognitions.get(1);
        assertThat(secondRecognition.contract(), is(contract));
        assertThat(secondRecognition.amount(), is(dollars(40.0)));
        assertThat(secondRecognition.recognizedOn(), eq(date(2012, 1, 31)));
        RevenueRecognition thirdRecognition = revenueRecognitions.get(2);
        assertThat(thirdRecognition.contract(), is(contract));
        assertThat(thirdRecognition.amount(), is(dollars(40.0)));
        assertThat(thirdRecognition.recognizedOn(), eq(date(2012, 3, 1)));
    }

}
