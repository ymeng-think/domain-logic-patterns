package com.ymeng.pattern.domainmodel.strategy;

import com.ymeng.pattern.common.Money;
import com.ymeng.pattern.domainmodel.Contract;
import com.ymeng.pattern.domainmodel.RecognitionStrategy;
import com.ymeng.pattern.domainmodel.RevenueRecognition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ymeng.pattern.common.DateUtil.addDays;

public class ThreeWayRecognitionStrategy implements RecognitionStrategy {

    private final int firstRecognitionOffset;
    private final int secondRecognitionOffset;

    public ThreeWayRecognitionStrategy(int firstRecognitionOffset, int secondRecognitionOffset) {
        this.firstRecognitionOffset = firstRecognitionOffset;
        this.secondRecognitionOffset = secondRecognitionOffset;
    }

    @Override
    public List<RevenueRecognition> calculateRevenueRecognitions(Contract contract) {
        ArrayList<RevenueRecognition> recognitions = new ArrayList<RevenueRecognition>();

        Money[] allocates = contract.recognizedRevenue().allocate(3);
        Date[] recognizedOnList = new Date[] {
                contract.whenSigned(),
                addDays(contract.whenSigned(), firstRecognitionOffset),
                addDays(contract.whenSigned(), secondRecognitionOffset)
        };

        for (int i = 0; i < allocates.length; i++) {
            recognitions.add(new RevenueRecognition(contract, allocates[i], recognizedOnList[i]));
        }

        return recognitions;
    }
}
