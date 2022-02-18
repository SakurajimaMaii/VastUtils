@file:JvmName("DateFormat")

package com.gcode.vasttools.annotation

import androidx.annotation.StringDef

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate: 2021/11/11
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/11
 */

// Time Format
const val DATE_FORMAT = "yyyy-MM-dd"
const val TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
const val FORMAT_YYYY_MM = "yyyy-MM"
const val FORMAT_YYYY = "yyyy"
const val FORMAT_HH_MM = "HH:mm"
const val FORMAT_HH_MM_SS = "HH:mm:ss"
const val FORMAT_MM_SS = "mm:ss"
const val FORMAT_MM_DD_HH_MM = "MM-dd HH:mm"
const val FORMAT_MM_DD_HH_MM_SS = "MM-dd HH:mm:ss"
const val FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm"
const val FORMAT_YYYY2MM2DD = "yyyy.MM.dd"
const val FORMAT_YYYY2MM2DD_HH_MM = "yyyy.MM.dd HH:mm"
const val FORMAT_MMCDD_HH_MM = "MM月dd日 HH:mm"
const val FORMAT_MMCDD = "MM月dd日"
const val FORMAT_YYYYCMMCDD = "yyyy年MM月dd日"

// GMT Format
const val GMT_PLUS_ZONE = "GMT+00:00"
const val GMT_PLUS_ONE = "GMT+01:00"
const val GMT_PLUS_TWO = "GMT+02:00"
const val GMT_PLUS_THREE = "GMT+03:00"
const val GMT_PLUS_FOUR = "GMT+04:00"
const val GMT_PLUS_FIVE = "GMT+05:00"
const val GMT_PLUS_SIX = "GMT+06:00"
const val GMT_PLUS_SEVEN = "GMT+07:00"
const val GMT_PLUS_EIGHT = "GMT+08:00"
const val GMT_PLUS_NINE = "GMT+09:00"
const val GMT_PLUS_TEN = "GMT+10:00"
const val GMT_PLUS_ELEVEN = "GMT+11:00"
const val GMT_PLUS_TWELVE = "GMT+12:00"
const val GMT_MINUS_ONE = "GMT-01:00"
const val GMT_MINUS_TWO = "GMT-02:00"
const val GMT_MINUS_THREE = "GMT-03:00"
const val GMT_MINUS_FOUR = "GMT-04:00"
const val GMT_MINUS_FIVE = "GMT-05:00"
const val GMT_MINUS_SIX = "GMT-06:00"
const val GMT_MINUS_SEVEN = "GMT-07:00"
const val GMT_MINUS_EIGHT = "GMT-08:00"
const val GMT_MINUS_NINE = "GMT-09:00"
const val GMT_MINUS_TEN = "GMT-10:00"
const val GMT_MINUS_ELEVEN = "GMT-11:00"
const val GMT_MINUS_TWELVE = "GMT-12:00"

@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@StringDef(
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
)
annotation class DateFormatString

@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@StringDef(
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
)
annotation class GmtFormatString

@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@StringDef(DATE_FORMAT, FORMAT_YYYY_MM, FORMAT_YYYY, FORMAT_YYYY2MM2DD)
annotation class YearFormatString