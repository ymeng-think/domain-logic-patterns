package com.ymeng.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class DateEqualMatcher extends TypeSafeMatcher<Date> {

    private final Date expected;

    public DateEqualMatcher(Date expected) {
        this.expected = expected;
    }

    @Override
    public boolean matchesSafely(Date item) {
        return compare(expected, item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }

    private static boolean compare(Date date1, Date date2) {
        return date1.getYear() == date2.getYear() &&
                date1.getMonth() == date2.getMonth() &&
                date1.getDate() == date2.getDate();
    }
}
