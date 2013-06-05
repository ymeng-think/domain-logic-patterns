package com.ymeng.pattern.domainmodel;

import com.ymeng.pattern.common.Money;
import org.junit.Test;

import static com.ymeng.builder.DateBuilder.date;
import static com.ymeng.pattern.common.Money.dollars;
import static com.ymeng.pattern.domainmodel.ProductBuilder.MSWord;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContractTest {

    @Test
    public void should_get_recognition_revenue() {
        Product msWord = MSWord().build();
        Contract contract = new Contract(msWord, 100.0, date(2012, 1, 1));

        Money revenue = contract.recognizedRevenue();

        assertThat(revenue, is(dollars(100.0)));
    }

}
