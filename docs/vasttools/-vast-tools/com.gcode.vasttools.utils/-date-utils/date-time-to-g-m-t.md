//[VastTools](../../../index.md)/[com.gcode.vasttools.utils](../index.md)/[DateUtils](index.md)/[dateTimeToGMT](date-time-to-g-m-t.md)

# dateTimeToGMT

[androidJvm]\

@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()

fun [dateTimeToGMT](date-time-to-g-m-t.md)(gmtFormat: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = currentTimeZone, dateFormat: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = TIME_FORMAT): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

According to the [dateFormat](date-time-to-g-m-t.md) format, returns a time string in the time zone given by the [gmtFormat](date-time-to-g-m-t.md).

If you don't set the [gmtFormat](date-time-to-g-m-t.md) or [dateFormat](date-time-to-g-m-t.md),it will parse current local time in [TIME_FORMAT](../../com.gcode.vasttools.annotation/-date-format/-t-i-m-e_-f-o-r-m-a-t.md) format by default.
