package com.ymeng.pattern.domainmodel;

import com.ymeng.pattern.common.Money;

import java.util.Date;
import java.util.List;

import static com.ymeng.pattern.common.Money.dollars;

public class Contract {

    private final Product product;
    private final Money revenue;
    private final Date whenSigned;

    public Contract(Product product, double revenue, Date whenSigned) {
        this.product = product;
        this.revenue = dollars(revenue);
        this.whenSigned = whenSigned;
    }

    public Money recognizedRevenue() {
        return revenue;
    }

    public Date whenSigned() {
        return whenSigned;
    }

    public List<RevenueRecognition> calculateRecognitions() {
        return product.calculateRevenueRecognitions(this);
    }
}
