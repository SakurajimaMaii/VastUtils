//[VastTools](../../../index.md)/[com.gcode.vasttools.utils](../index.md)/[DateUtils](index.md)/[datetimeToString](datetime-to-string.md)

# datetimeToString

[androidJvm]\

@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()

fun [datetimeToString](datetime-to-string.md)(date: [Date](https://developer.android.com/reference/kotlin/java/util/Date.html) = Date(), dateFormat: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = TIME_FORMAT): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Parse [date](datetime-to-string.md) according to the format of [dateFormat](datetime-to-string.md) into a date/time string.

If you don't set the [date](datetime-to-string.md) or [dateFormat](datetime-to-string.md),it will parse current time in [dateFormat](datetime-to-string.md) format by default.
