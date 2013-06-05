package com.ymeng.pattern.domainmodel.strategy;

import com.ymeng.pattern.domainmodel.Contract;
import com.ymeng.pattern.domainmodel.RecognitionStrategy;
import com.ymeng.pattern.domainmodel.RevenueRecognition;

import java.util.List;

public class CompleteRecognitionStrategy implements RecognitionStrategy {

    @Override
    public List<RevenueRecognition> calculateRevenueRecognitions(Contract contract) {
        return null;
    }
}
