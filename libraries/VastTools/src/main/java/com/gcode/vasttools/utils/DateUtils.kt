/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gcode.vasttools.utils

import androidx.annotation.StringDef
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.Throws

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 15:27
// Description: Help you get time and other related information.
// Documentation: [DateUtils](https://sakurajimamaii.github.io/VastDocs/document/en/DateUtils.html)


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

@Retention(AnnotationRetention.SOURCE)
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

@Retention(AnnotationRetention.SOURCE)
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

@Retention(AnnotationRetention.SOURCE)
@StringDef(DATE_FORMAT, FORMAT_YYYY_MM, FORMAT_YYYY, FORMAT_YYYY2MM2DD)
annotation class YearFormatString

object DateUtils {
    /**
     * Get current time
     */
    val currentTime: String
        get() {
            return dateTimeToGMT()
        }

    /**
     * Get current time zone
     */
    val currentTimeZone: String
        get() {
            return TimeZone.getDefault().getDisplayName(true, TimeZone.SHORT, Locale.getDefault())
        }

    /**
     * Get min time
     */
    fun minDate(): Date {
        val result = Date()
        result.time = GregorianCalendar().let {
            it.set(1900, 1, 1)
            it.timeInMillis
        }
        return result
    }

    /**
     * @return Get the minimum time string in the given format.
     */
    @JvmOverloads
    fun minDateToString(@DateFormatString dateFormat: String = TIME_FORMAT): String {
        return datetimeToString(minDate(), dateFormat)
    }

    /**
     * Returns the date and time formatting object.
     */
    private fun datetimeFormat(
        @DateFormatString dateFormat: String
    ): SimpleDateFormat {
        return SimpleDateFormat(dateFormat, Locale.getDefault())
    }

    /**
     * Get date object by parsing [timeString] in [timeStringFormat] format.
     *
     * @return If [timeString] parsing fails, it returns 'null' object.Otherwise, it returns date object.
     * @throws [ParseException] If [timeString] parsing fails.
     */
    @Throws(ParseException::class)
    fun datetimeFromString(
        timeString: String,
        @DateFormatString timeStringFormat: String
    ): Date? {
        return try {
            datetimeFormat(timeStringFormat).parse(timeString)
        } catch (e: ParseException) {
            e.printStackTrace()
            Date()
        }
    }

    /**
     * Parse [date] according to the format of [dateFormat]
     * into a date/time string.
     *
     * If you don't set the [date] or [dateFormat],it will
     * parse current time in [dateFormat] format by default.
     */
    @JvmOverloads
    fun datetimeToString(
        date: Date = Date(),
        @DateFormatString dateFormat: String = TIME_FORMAT
    ): String {
        return datetimeFormat(dateFormat).format(date)
    }

    /**
     * According to the [dateFormat] format,
     * returns a time string in the time zone
     * given by the [gmtFormat].
     *
     * If you don't set the [gmtFormat] or [dateFormat],it will
     * parse current local time in [TIME_FORMAT] format by default.
     */
    @JvmOverloads
    fun dateTimeToGMT(
        @GmtFormatString gmtFormat: String = currentTimeZone,
        @DateFormatString dateFormat: String = TIME_FORMAT
    ): String {
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone(gmtFormat)
        return formatter.format(Date())
    }

    /**
     * Get current local time string by parsing the [utcTime] in [dateFormat] format.
     */
    fun dateTimeFromGMT(
        utcTime: String,
        @DateFormatString dateFormat: String
    ): String {
        val utcFormatter = SimpleDateFormat(dateFormat, Locale.getDefault()) //UTC time format
        utcFormatter.timeZone = TimeZone.getTimeZone("UTC")
        var gpsUTCDate: Date? = null
        try {
            gpsUTCDate = utcFormatter.parse(utcTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val localFormatter =
            SimpleDateFormat(dateFormat, Locale.getDefault()) //Local time format
        localFormatter.timeZone = TimeZone.getDefault()
        return localFormatter.format(gpsUTCDate!!.time)
    }

    /**
     * Get the **start** timestamp of the week.
     * **Monday is the first day of the week.**
     *
     * @return WeekStartTime parsed in [yearFormat] format
     */
    @JvmOverloads
    fun weekStartTime(@YearFormatString yearFormat: String = DATE_FORMAT): String {
        val simpleDateFormat = SimpleDateFormat(yearFormat, Locale.getDefault())
        val cal: Calendar = Calendar.getInstance()
        var dayOfWeek: Int = cal.get(Calendar.DAY_OF_WEEK) - 1
        if (dayOfWeek == 0) {
            dayOfWeek = 7
        }
        cal.add(Calendar.DATE, -dayOfWeek + 1)
        return simpleDateFormat.format(cal.time)
    }

    /**
     * Get the **end** timestamp of the week.
     * **Monday is the first day of the week.**
     *
     * @return WeekEndTime parsed in [yearFormat] format
     */
    @JvmOverloads
    fun weekEndTime(@YearFormatString yearFormat: String = DATE_FORMAT): String {
        val simpleDateFormat = SimpleDateFormat(yearFormat, Locale.getDefault())
        val cal: Calendar = Calendar.getInstance()
        var dayOfWeek: Int = cal.get(Calendar.DAY_OF_WEEK) - 1
        if (dayOfWeek == 0) {
            dayOfWeek = 7
        }
        cal.add(Calendar.DATE, -dayOfWeek + 7)
        return simpleDateFormat.format(cal.time)
    }

    /**
     * Get the **start** timestamp of the week.
     * **SUNDAY is the first day of the week.**
     *
     * @return WeekStartTime parsed in [yearFormat] format
     */
    @JvmOverloads
    fun getWeekStartTime(
        calendar: Calendar = Calendar.getInstance(),
        @YearFormatString yearFormat: String = DATE_FORMAT
    ): String {
        val simpleDateFormat = SimpleDateFormat(yearFormat, Locale.getDefault())
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        return simpleDateFormat.format(calendar.time)
    }

    /**
     * Get the **end** timestamp of the week.
     * **SUNDAY is the first day of the week.**
     *
     * @return WeekEndTime parsed in [yearFormat] format
     */
    @JvmOverloads
    fun getWeekEndTime(
        calendar: Calendar = Calendar.getInstance(),
        @YearFormatString yearFormat: String = DATE_FORMAT
    ): String {
        val simpleDateFormat = SimpleDateFormat(yearFormat, Locale.getDefault())
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
        return simpleDateFormat.format(calendar.time)
    }
}