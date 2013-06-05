package com.ymeng.pattern.domainmodel;

import java.util.List;

public class Product {

    private RecognitionStrategy strategy;

    public Product(RecognitionStrategy strategy) {
        this.strategy = strategy;
    }

    public List<RevenueRecognition> calculateRevenueRecognitions(Contract contract) {
        return strategy.calculateRevenueRecognitions(contract);
    }
}
