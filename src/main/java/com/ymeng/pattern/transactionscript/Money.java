package com.ymeng.pattern.transactionscript;

public final class Money {

    private double dollars;

    public static Money dollars(double dollars) {
        return new Money(dollars);
    }

    public Money add(Money money) {
        return Money.dollars(this.value() + money.value());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Money money = (Money) o;
        if (Double.compare(money.dollars, dollars) != 0) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        long temp = dollars != +0.0d ? Double.doubleToLongBits(dollars) : 0L;
        return (int) (temp ^ (temp >>> 32));
    }

    private double value() {
        return dollars;
    }

    private Money(double dollars) {
        this.dollars = dollars;
    }
}
