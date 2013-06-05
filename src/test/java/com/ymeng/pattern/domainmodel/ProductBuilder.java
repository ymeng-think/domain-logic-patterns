package com.ymeng.pattern.domainmodel;

import com.ymeng.pattern.domainmodel.strategy.CompleteRecognitionStrategy;
import com.ymeng.pattern.domainmodel.strategy.ThreeWayRecognitionStrategy;

public class ProductBuilder {

    private Product product;

    public static ProductBuilder MSWord() {
        return new ProductBuilder(new CompleteRecognitionStrategy());
    }

    public static ProductBuilder MSExcel() {
        return new ProductBuilder(new ThreeWayRecognitionStrategy(60, 90));
    }

    public Product build() {
        return product;
    }

    private ProductBuilder(RecognitionStrategy strategy) {
        this.product = new Product(strategy);
    }
}
