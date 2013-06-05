package com.ymeng.pattern.domainmodel;

import com.ymeng.pattern.domainmodel.strategy.CompleteRecognitionStrategy;

public class ProductBuilder {

    private Product product;

    public static ProductBuilder MSWord() {
        return new ProductBuilder(new CompleteRecognitionStrategy());
    }

    public Product build() {
        return product;
    }

    private ProductBuilder(RecognitionStrategy strategy) {
        this.product = new Product(strategy);
    }
}
