package com.ymeng.pattern.domainmodel;

import com.sun.tools.internal.xjc.reader.xmlschema.ParticleBinder;
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

    public static ProductBuilder MSSql() {
        return new ProductBuilder(new ThreeWayRecognitionStrategy(30, 60));
    }

    public Product build() {
        return product;
    }

    private ProductBuilder(RecognitionStrategy strategy) {
        this.product = new Product(strategy);
    }
}
