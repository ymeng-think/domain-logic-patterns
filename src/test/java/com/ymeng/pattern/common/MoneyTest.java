package com.ymeng.pattern.common;

import org.junit.Test;

import static com.ymeng.pattern.common.Money.dollars;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

    @Test
    public void should_allocate_money_equally_when_allocated_is_decimal() {
        Money sixDollars = dollars(3);

        Money[] allocation = sixDollars.allocate(2);

        assertThat(allocation.length, is(2));
        assertThat(allocation[0], is(dollars(1.5)));
        assertThat(allocation[1], is(dollars(1.5)));
    }

    @Test
    public void should_arrange_remainder_to_the_top_allocation() {
        Money sixDollars = dollars(4);

        Money[] allocation = sixDollars.allocate(3);

        assertThat(allocation.length, is(3));
        assertThat(allocation[0], is(dollars(1.33)));
        assertThat(allocation[1], is(dollars(1.33)));
        assertThat(allocation[2], is(dollars(1.34)));
    }
}
