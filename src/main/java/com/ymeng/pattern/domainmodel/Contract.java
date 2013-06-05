package com.ymeng.pattern.domainmodel;

import com.ymeng.pattern.common.Money;

import java.util.Date;
import java.util.List;

import static com.ymeng.pattern.common.Money.dollars;

public class Contract {

    private Product product;
    private double revenue;
    private Date whenSigned;

    public Money recognizedRevenue(Date asOf) {
        return dollars(0);
    }

    public List<RevenueRecognition> calculateRecognitions() {
        return product.calculateRevenueRecognitions(this);
    }

}
