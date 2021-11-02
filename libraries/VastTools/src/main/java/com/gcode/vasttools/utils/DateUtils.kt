package com.gcode.vasttools.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Date utils
 * @see [https://github.com/yudu233/AndroidUtils/blob/master/library/src/main/java/com/coder/rain/library/utils/DateHelper.java]
 * @constructor Create empty Date utils
 */
object DateUtils {
    private var locale:Locale = Locale.CHINA

    // Returns the date and time formatting object
    fun datetimeFormat(): SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale)
    }

    // Returns the date and time formatting object without second
    fun noSecondFormat(): SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd HH:mm", locale)
    }

    /**
     * Parse date and time from string
     * @param timeString
     * @return
     */
    fun datetimeFromString(timeString: String): Date? {
        return try {
            datetimeFormat().parse(timeString)
        } catch (e: ParseException) {
            e.printStackTrace()
            Date()
        }
    }

    // Return short time string format yyyy-MM-dd
    fun getStringDateShort(date: Date): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", locale)
        return formatter.format(date)
    }

    // Format date time as string
    fun datetimeToString(date: Date): String {
        return datetimeFormat().format(date)
    }

    fun datetimeToNoSecond(date: Date?): String {
        return if (date == null) "" else noSecondFormat().format(date)
    }

    fun dealDateFormat(oldDateStr: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", locale) //yyyy-MM-dd'T'HH:mm:ss.SSS
        var date: Date? = null
        try {
            date = format.parse(oldDateStr)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }

    // GMT time format conversion
    fun dateTimeFromGMT(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale("CHINESE", "CHINA"))
        formatter.applyPattern("yyyy年MM月dd日 HH:mm")
        return formatter.format(Date())
    }

    // Convert time interval into readable information
    private fun humanInterval(date: Date?, nowTime: Date): String {
        if (date == null) return ""
        // long diff = nowTime - date.getTime();
        var diff = nowTime.time - date.time
        diff /= 1000
        if (diff < 60) return "刚刚"
        if (diff < 3600) return (diff / 60).toString() + "minutes ago"
        if (diff > 3600 && diff < 3600 * 24) return (diff / 3600).toString() + "hours ago"
        return if (diff < 3600 * 24 * 7 && diff > 3600 * 24) (diff / (3600 * 24)).toString() + "days ago" else getStringDateShort(
            date
        )
    }

    fun humanInterval(date: Date?): String {
        return humanInterval(date, Date())
    }

    // Determine whether it is within the same minute
    fun inSameMinute(one: Date?, tow: Date?): Boolean {
        return if (one == null || tow == null) false else one.time / 60000 == tow.time / 60000
    }

    // Determine whether it is within the same minute by long
    fun inSameMinuteByLong(one: Long, tow: Long): Boolean {
        return one / 60000 == tow / 60000
    }

    // get min time
    fun minDate(): Date {
        val result = Date()
        result.time = GregorianCalendar().let {
            it.set(1900, 1, 1)
            it.timeInMillis
        }
        return result
    }

    val nowTime: String
        get() {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", locale)
            return dateFormat.format(Date())
        }

    // Start on Sunday to get the timestamp of the beginning of the week
    fun getWeekStartTime(calendar: Calendar): String {
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd", locale)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        return simpleDateFormat.format(calendar.time)
    }

    /**
     * Timestamp of the end of the week
     * @param calendar
     * @return
     */
    fun getWeekEndTime(calendar: Calendar): String {
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd", locale)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
        return simpleDateFormat.format(calendar.time)
    }

    // Start timestamp of the week. Monday is the first day of the week
    val weekStartTime: String
        get() {
            val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
            val cal: Calendar = Calendar.getInstance()
            var dayOfWeek: Int = cal.get(Calendar.DAY_OF_WEEK) - 1
            if (dayOfWeek == 0) {
                dayOfWeek = 7
            }
            cal.add(Calendar.DATE, -dayOfWeek + 1)
            return simpleDateFormat.format(cal.time)
        }

    // The end timestamp of the week. Monday is the first day of the week
    val weekEndTime: String
        get() {
            val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
            val cal: Calendar = Calendar.getInstance()
            var dayOfWeek: Int = cal.get(Calendar.DAY_OF_WEEK) - 1
            if (dayOfWeek == 0) {
                dayOfWeek = 7
            }
            cal.add(Calendar.DATE, -dayOfWeek + 7)
            return simpleDateFormat.format(cal.time)
        }

    // Start on Sunday to get the timestamp of the beginning of the week
    fun getWeekStartTimeNoYear(calendar: Calendar): String {
        val simpleDateFormat = SimpleDateFormat("MM/dd", Locale.getDefault())
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        return simpleDateFormat.format(calendar.time)
    }

    // Timestamp of the end of the week
    fun getWeekEndTimeNoYear(calendar: Calendar): String {
        val simpleDateFormat = SimpleDateFormat("MM/dd", Locale.getDefault())
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
        return simpleDateFormat.format(calendar.time)
    }

    fun setLocale(locale: Locale){
        DateUtils.locale = locale
    }
}