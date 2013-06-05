package com.ymeng.pattern.domainmodel;

import java.util.List;

public interface RecognitionStrategy {

    List<RevenueRecognition> calculateRevenueRecognitions(Contract contract);
}
