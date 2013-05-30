package com.ymeng.pattern.transactionscript;

import org.junit.Test;

import static com.ymeng.matcher.DateEqualMatcher.eq;
import static com.ymeng.pattern.transactionscript.Money.dollars;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MoneyTest {

    @Test
    public void should_add_money_correctly() {
        Money oneDollar = dollars(1);

        assertThat(oneDollar.add(dollars(2)), is(dollars(3)));
    }

    @Test
    public void should_allocate_money_equally_when_allocated_is_integer() {
        Money sixDollars = dollars(6);

        Money[] allocation = sixDollars.allocate(2);

        assertThat(allocation.length, is(2));
        assertThat(allocation[0], is(dollars(3)));
        assertThat(allocation[1], is(dollars(3)));
    }
}
