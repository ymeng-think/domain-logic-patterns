package com.ymeng.builder;

import java.util.Calendar;
import java.util.Date;

public final class DateBuilder {

    public static Date date(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);

        return calendar.getTime();
    }

    private DateBuilder() {
    }
}
