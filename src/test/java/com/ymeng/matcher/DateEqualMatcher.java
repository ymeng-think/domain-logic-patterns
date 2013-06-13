package com.ymeng.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

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
        DateWrapper d1 = DateWrapper.wrap(date1);
        DateWrapper d2 = DateWrapper.wrap(date2);
        return d1.equals(d2);
    }

    private static class DateWrapper {

        private final int year;
        private final int month;
        private final int day;

        private DateWrapper(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            year = calendar.get(YEAR);
            month = calendar.get(MONTH);
            day = calendar.get(DAY_OF_MONTH);
        }

        public static DateWrapper wrap(Date date) {
            return new DateWrapper(date);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            DateWrapper that = (DateWrapper) o;
            return day == that.day && month == that.month && year == that.year;
        }
    }
}
