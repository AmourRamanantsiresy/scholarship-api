package com.web.scholarship.utils.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class TimeCalc {
    public static int yearFromOld(int old){
        int currentYear = LocalDate.now().getYear();
        return currentYear - old;
    }

    public static int yearFromDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }
}
