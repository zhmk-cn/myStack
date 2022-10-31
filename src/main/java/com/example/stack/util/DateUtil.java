package com.example.stack.util;

import com.example.stack.model.User;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtil {

    private static final String FORMATDATE = "yyyy-MM-dd";
    private static final String FORMATDATETIME = "yyyy-MM-dd HH:mm:ss";


    private static String pattern = "yyyy-MM-dd";

    //DateTimeFormatter的类是不可变的，并且是线程安全的。
    private static final DateTimeFormatter getShortDateFormat = DateTimeFormatter.ofPattern(pattern);

    /**
     * 将yyyy-MM-dd HH:mm:ss 转为yyyy-MM-dd
     *
     * @param
     * @return
     */
    public static String formatDate(Date date) {
        return getShortDateFormat.format(dateToLocalDate(date));
    }

    public static Date parse(String date) {
        return localDateTimeToDate(LocalDateTime.parse(date, getShortDateFormat));
    }

    /**
     * 日期转为LocalDate
     *
     * @param date 日期
     * @return LocalDateTime
     */
    public static LocalDate dateToLocalDate(final Date date) {
        if (null == date) {
            return null;
        }
        final Instant instant = date.toInstant();
        final ZoneId zoneId = ZoneId.systemDefault();
        final LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }

    /**
     * LocalDateTime转为日期
     *
     * @param localDateTime LocalDateTime
     * @return 日期
     */
    public static Date localDateTimeToDate(final LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zdt = localDateTime.atZone(zoneId);
        final Date date = Date.from(zdt.toInstant());
        return date;
    }
}
