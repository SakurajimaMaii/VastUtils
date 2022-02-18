package com.gcode.vasttools.utils

import com.gcode.vasttools.annotation.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.Throws

/**
 * Date utils
 */
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