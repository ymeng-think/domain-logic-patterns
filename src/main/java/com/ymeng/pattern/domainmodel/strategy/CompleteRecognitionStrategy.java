package com.ymeng.pattern.domainmodel.strategy;

import com.ymeng.pattern.domainmodel.Contract;
import com.ymeng.pattern.domainmodel.RecognitionStrategy;
import com.ymeng.pattern.domainmodel.RevenueRecognition;

import java.util.ArrayList;
import java.util.List;

public class CompleteRecognitionStrategy implements RecognitionStrategy {

    @Override
    public List<RevenueRecognition> calculateRevenueRecognitions(Contract contract) {
        ArrayList<RevenueRecognition> recognitions = new ArrayList<RevenueRecognition>();
        recognitions.add(new RevenueRecognition(contract, contract.recognizedRevenue(), contract.whenSigned()));

        return recognitions;
    }
}
