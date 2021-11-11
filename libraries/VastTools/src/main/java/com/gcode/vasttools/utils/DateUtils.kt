package com.gcode.vasttools.utils

import com.gcode.vasttools.annotation.DateFormat
import com.gcode.vasttools.annotation.DateFormat.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Date utils
 */
object DateUtils {
    var locale: Locale = Locale.CHINA
        private set

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
            return TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
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
     * Returns the date and time formatting object
     */
    @JvmOverloads
    fun datetimeFormat(
        @DateFormat.DateFormatString dateFormat: String = TIME_FORMAT
    ): SimpleDateFormat {
        return SimpleDateFormat(dateFormat, locale)
    }

    /**
     * Parse date and time from [timeString] in [timeStringFormat] format.
     *
     * @return if [timeString] If the parsing fails, it returns [Date] object
     */
    @JvmOverloads
    fun datetimeFromString(
        timeString: String,
        @DateFormat.DateFormatString timeStringFormat: String = TIME_FORMAT
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
        @DateFormat.DateFormatString dateFormat: String = TIME_FORMAT
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
        @DateFormat.GmtFormatString gmtFormat: String = currentTimeZone,
        @DateFormat.DateFormatString dateFormat: String = TIME_FORMAT
    ): String {
        val formatter = SimpleDateFormat(dateFormat, locale)
        formatter.timeZone = TimeZone.getTimeZone(gmtFormat);
        return formatter.format(Date())
    }

    /**
     * Get current local time string by parsing the [utcTime] in [dateFormat] format.
     */
    fun dateTimeFromGMT(utcTime: String,@DateFormat.DateFormatString dateFormat: String = TIME_FORMAT): String {
        val utcFormatter = SimpleDateFormat(dateFormat,locale) //UTC time format
        utcFormatter.timeZone = TimeZone.getTimeZone("UTC")
        var gpsUTCDate: Date? = null
        try {
            gpsUTCDate = utcFormatter.parse(utcTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val localFormatter =
            SimpleDateFormat(dateFormat,locale) //Local time format
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
    fun weekStartTime(@DateFormat.YearFormatString yearFormat: String = DATE_FORMAT): String {
        val simpleDateFormat = SimpleDateFormat(yearFormat, locale)
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
    fun weekEndTime(@DateFormat.YearFormatString yearFormat: String = DATE_FORMAT): String {
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
        @DateFormat.YearFormatString yearFormat: String = DATE_FORMAT
    ): String {
        val simpleDateFormat = SimpleDateFormat(yearFormat, locale)
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
        @DateFormat.YearFormatString yearFormat: String = DATE_FORMAT
    ): String {
        val simpleDateFormat = SimpleDateFormat(yearFormat, locale)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
        return simpleDateFormat.format(calendar.time)
    }

    fun setLocale(locale: Locale) {
        DateUtils.locale = locale
    }
}