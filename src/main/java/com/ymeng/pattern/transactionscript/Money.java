package com.ymeng.pattern.transactionscript;

public final class Money {

    private static final double MONEY_THRESHOLD = 0.001;
    private double dollars;

    public static Money dollars(double dollars) {
        return new Money(dollars);
    }

    public Money add(Money money) {
        return Money.dollars(this.value() + money.value());
    }

    public Money[] allocate(int count) {
        double average = this.value() / count;

        Money[] allocation = new Money[count];
        double total = 0.0;
        for (int i = 0; i < allocation.length - 1; i++) {
            double value = keep2Precision(average);
            allocation[i] = dollars(value);
            total += value;
        }

        allocation[allocation.length - 1] = dollars(keep2Precision(this.value() - total));

        return allocation;
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

    private static double keep2Precision(double number) {
        return ((int)((number + MONEY_THRESHOLD) * 100)) / 100.0d;
    }
}
