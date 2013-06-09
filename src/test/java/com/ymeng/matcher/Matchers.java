package com.ymeng.matcher;

import java.util.Date;

public final class Matchers {

    public static DataRowEqualMatcher nextRowContains(Object... expected) {
        return new DataRowEqualMatcher(expected);
    }

    public static DateEqualMatcher eq(Date expected) {
        return new DateEqualMatcher(expected);
    }

    public static ListEqualMatcher<String> contains(String... items) {
        return new ListEqualMatcher<String>(items);
    }

    public static NewObjectMatcher newObject() {
        return new NewObjectMatcher();
    }

    public static NoMoreDataRowMatcher noMoreRow() {
        return new NoMoreDataRowMatcher();
    }

    private Matchers() {
    }
}