package com.ymeng.pattern.common;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DATE;

public final class DateUtil {

    public static Date addDays(Date date, int delta) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(DATE, delta);

        return calendar.getTime();
    }

    private DateUtil() {
    }
}
