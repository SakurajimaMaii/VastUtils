package com.gcode.vasttools.annotation;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate: 2021/11/11
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/11
 */
public class DateFormat{

    // Time Format
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YYYY_MM = "yyyy-MM";
    public static final String FORMAT_YYYY = "yyyy";
    public static final String FORMAT_HH_MM = "HH:mm";
    public static final String FORMAT_HH_MM_SS = "HH:mm:ss";
    public static final String FORMAT_MM_SS = "mm:ss";
    public static final String FORMAT_MM_DD_HH_MM = "MM-dd HH:mm";
    public static final String FORMAT_MM_DD_HH_MM_SS = "MM-dd HH:mm:ss";
    public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_YYYY2MM2DD = "yyyy.MM.dd";
    public static final String FORMAT_YYYY2MM2DD_HH_MM = "yyyy.MM.dd HH:mm";
    public static final String FORMAT_MMCDD_HH_MM = "MM月dd日 HH:mm";
    public static final String FORMAT_MMCDD = "MM月dd日";
    public static final String FORMAT_YYYYCMMCDD = "yyyy年MM月dd日";

    // GMT Format
    public static final String GMT_PLUS_ZONE = "GMT+00:00";
    public static final String GMT_PLUS_ONE = "GMT+01:00";
    public static final String GMT_PLUS_TWO = "GMT+02:00";
    public static final String GMT_PLUS_THREE = "GMT+03:00";
    public static final String GMT_PLUS_FOUR = "GMT+04:00";
    public static final String GMT_PLUS_FIVE = "GMT+05:00";
    public static final String GMT_PLUS_SIX = "GMT+06:00";
    public static final String GMT_PLUS_SEVEN = "GMT+07:00";
    public static final String GMT_PLUS_EIGHT = "GMT+08:00";
    public static final String GMT_PLUS_NINE = "GMT+09:00";
    public static final String GMT_PLUS_TEN = "GMT+10:00";
    public static final String GMT_PLUS_ELEVEN = "GMT+11:00";
    public static final String GMT_PLUS_TWELVE = "GMT+12:00";
    public static final String GMT_MINUS_ONE = "GMT-01:00";
    public static final String GMT_MINUS_TWO = "GMT-02:00";
    public static final String GMT_MINUS_THREE = "GMT-03:00";
    public static final String GMT_MINUS_FOUR = "GMT-04:00";
    public static final String GMT_MINUS_FIVE = "GMT-05:00";
    public static final String GMT_MINUS_SIX = "GMT-06:00";
    public static final String GMT_MINUS_SEVEN = "GMT-07:00";
    public static final String GMT_MINUS_EIGHT = "GMT-08:00";
    public static final String GMT_MINUS_NINE = "GMT-09:00";
    public static final String GMT_MINUS_TEN = "GMT-10:00";
    public static final String GMT_MINUS_ELEVEN = "GMT-11:00";
    public static final String GMT_MINUS_TWELVE = "GMT-12:00";


    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            DATE_FORMAT,
            TIME_FORMAT,
            FORMAT_YYYY_MM,
            FORMAT_YYYY,
            FORMAT_HH_MM,
            FORMAT_HH_MM_SS,
            FORMAT_MM_SS,
            FORMAT_MM_DD_HH_MM,
            FORMAT_MM_DD_HH_MM_SS,
            FORMAT_YYYY_MM_DD_HH_MM,
            FORMAT_YYYY2MM2DD,
            FORMAT_YYYY2MM2DD_HH_MM,
            FORMAT_MMCDD_HH_MM,
            FORMAT_MMCDD,
            FORMAT_YYYYCMMCDD
    })
    public @interface DateFormatString {}

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            GMT_PLUS_ZONE,
            GMT_PLUS_ONE,
            GMT_PLUS_TWO,
            GMT_PLUS_THREE,
            GMT_PLUS_FOUR,
            GMT_PLUS_FIVE,
            GMT_PLUS_SIX,
            GMT_PLUS_SEVEN,
            GMT_PLUS_EIGHT,
            GMT_PLUS_NINE,
            GMT_PLUS_TEN,
            GMT_PLUS_ELEVEN,
            GMT_PLUS_TWELVE,
            GMT_MINUS_ONE,
            GMT_MINUS_TWO,
            GMT_MINUS_THREE,
            GMT_MINUS_FOUR,
            GMT_MINUS_FIVE,
            GMT_MINUS_SIX,
            GMT_MINUS_SEVEN,
            GMT_MINUS_EIGHT,
            GMT_MINUS_NINE,
            GMT_MINUS_TEN,
            GMT_MINUS_ELEVEN,
            GMT_MINUS_TWELVE
    })
    public @interface GmtFormatString {}

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            DATE_FORMAT,
            FORMAT_YYYY_MM,
            FORMAT_YYYY,
            FORMAT_YYYY2MM2DD
    })
    public @interface YearFormatString {}
}