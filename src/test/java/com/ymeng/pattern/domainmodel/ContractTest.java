package com.ymeng.pattern.domainmodel;

import com.ymeng.pattern.common.Money;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.ymeng.builder.DateBuilder.date;
import static com.ymeng.matcher.DateEqualMatcher.eq;
import static com.ymeng.pattern.common.Money.dollars;
import static com.ymeng.pattern.domainmodel.ProductBuilder.MSWord;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContractTest {

    private Product msWord;

    @Before
    public void setUp() throws Exception {
        msWord = MSWord().build();
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

}
