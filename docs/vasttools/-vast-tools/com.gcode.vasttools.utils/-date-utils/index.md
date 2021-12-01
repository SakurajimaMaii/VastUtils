//[VastTools](../../../index.md)/[com.gcode.vasttools.utils](../index.md)/[DateUtils](index.md)

# DateUtils

[androidJvm]\
object [DateUtils](index.md)

Date utils

## Functions

| Name | Summary |
|---|---|
| [datetimeFormat](datetime-format.md) | [androidJvm]<br>@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()<br>fun [datetimeFormat](datetime-format.md)(dateFormat: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = TIME_FORMAT): [SimpleDateFormat](https://developer.android.com/reference/kotlin/java/text/SimpleDateFormat.html)<br>Returns the date and time formatting object |
| [dateTimeFromGMT](date-time-from-g-m-t.md) | [androidJvm]<br>fun [dateTimeFromGMT](date-time-from-g-m-t.md)(utcTime: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), dateFormat: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = TIME_FORMAT): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Get current local time string by parsing the [utcTime](date-time-from-g-m-t.md) in [dateFormat](date-time-from-g-m-t.md) format. |
| [datetimeFromString](datetime-from-string.md) | [androidJvm]<br>@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()<br>fun [datetimeFromString](datetime-from-string.md)(timeString: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeStringFormat: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = TIME_FORMAT): [Date](https://developer.android.com/reference/kotlin/java/util/Date.html)?<br>Parse date and time from [timeString](datetime-from-string.md) in [timeStringFormat](datetime-from-string.md) format. |
| [dateTimeToGMT](date-time-to-g-m-t.md) | [androidJvm]<br>@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()<br>fun [dateTimeToGMT](date-time-to-g-m-t.md)(gmtFormat: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = currentTimeZone, dateFormat: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = TIME_FORMAT): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>According to the [dateFormat](date-time-to-g-m-t.md) format, returns a time string in the time zone given by the [gmtFormat](date-time-to-g-m-t.md). |
| [datetimeToString](datetime-to-string.md) | [androidJvm]<br>@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()<br>fun [datetimeToString](datetime-to-string.md)(date: [Date](https://developer.android.com/reference/kotlin/java/util/Date.html) = Date(), dateFormat: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = TIME_FORMAT): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Parse [date](datetime-to-string.md) according to the format of [dateFormat](datetime-to-string.md) into a date/time string. |
| [getWeekEndTime](get-week-end-time.md) | [androidJvm]<br>@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()<br>fun [getWeekEndTime](get-week-end-time.md)(calendar: [Calendar](https://developer.android.com/reference/kotlin/java/util/Calendar.html) = Calendar.getInstance(), yearFormat: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = DATE_FORMAT): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Get the **end** timestamp of the week. |
| [getWeekStartTime](get-week-start-time.md) | [androidJvm]<br>@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()<br>fun [getWeekStartTime](get-week-start-time.md)(calendar: [Calendar](https://developer.android.com/reference/kotlin/java/util/Calendar.html) = Calendar.getInstance(), yearFormat: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = DATE_FORMAT): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Get the **start** timestamp of the week. |
| [minDate](min-date.md) | [androidJvm]<br>fun [minDate](min-date.md)(): [Date](https://developer.android.com/reference/kotlin/java/util/Date.html)<br>Get min time |
| [setLocale](set-locale.md) | [androidJvm]<br>fun [setLocale](set-locale.md)(locale: [Locale](https://developer.android.com/reference/kotlin/java/util/Locale.html)) |
| [weekEndTime](week-end-time.md) | [androidJvm]<br>@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()<br>fun [weekEndTime](week-end-time.md)(yearFormat: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = DATE_FORMAT): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Get the **end** timestamp of the week. |
| [weekStartTime](week-start-time.md) | [androidJvm]<br>@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()<br>fun [weekStartTime](week-start-time.md)(yearFormat: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = DATE_FORMAT): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Get the **start** timestamp of the week. |

## Properties

| Name | Summary |
|---|---|
| [currentTime](current-time.md) | [androidJvm]<br>val [currentTime](current-time.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Get current time |
| [currentTimeZone](current-time-zone.md) | [androidJvm]<br>val [currentTimeZone](current-time-zone.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Get current time zone |
| [locale](locale.md) | [androidJvm]<br>var [locale](locale.md): [Locale](https://developer.android.com/reference/kotlin/java/util/Locale.html) |
