package com.ymeng.pattern.domainmodel;

import com.ymeng.pattern.common.Money;

import java.util.Date;

public class RevenueRecognition {

    private final Contract contract;
    private final Money amount;
    private final Date recognizedOn;

    public RevenueRecognition(Contract contract, Money amount, Date recognizedOn) {
        this.contract = contract;
        this.amount = amount;
        this.recognizedOn = recognizedOn;
    }

    public Contract contract() {
        return contract;
    }

    public Money amount() {
        return amount;
    }

    public Date recognizedOn() {
        return recognizedOn;
    }
}
