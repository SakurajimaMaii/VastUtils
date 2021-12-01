//[VastTools](../../../index.md)/[com.gcode.vasttools.utils](../index.md)/[IntentUtils](index.md)/[createAlarm](create-alarm.md)

# createAlarm

[androidJvm]\

@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()

fun [createAlarm](create-alarm.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @[IntRange](https://developer.android.com/reference/kotlin/androidx/annotation/IntRange.html)(from = 0.toLong(), to = 23.toLong())hour: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @[IntRange](https://developer.android.com/reference/kotlin/androidx/annotation/IntRange.html)(from = 0.toLong(), to = 59.toLong())minutes: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), vibrate: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, skipUI: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, music: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)? = null, days: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)>? = null): [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)

Create alarm

## Parameters

androidJvm

| | |
|---|---|
| message | A custom message for the alarm or timer. |
| hour | The hour of the alarm. |
| minutes | The minutes of the alarm. |
| vibrate | Whether or not to activate the device vibrator.     Default value is false. |
| skipUI | Whether or not to display an activity after performing the action.     Default value is false. |
| days | Weekdays for repeating alarm.     The value is an Array<Int>. Each item can be: [Calendar.SUNDAY](https://developer.android.com/reference/kotlin/java/util/Calendar.html#monday)[Calendar.TUESDAY](https://developer.android.com/reference/kotlin/java/util/Calendar.html#wednesday)[Calendar.THURSDAY](https://developer.android.com/reference/kotlin/java/util/Calendar.html#friday)[Calendar.SATURDAY](https://developer.android.com/reference/kotlin/java/util/Calendar.html#saturday) The [days](create-alarm.md) default is null. |
| music | A ringtone to be played with this alarm.     Default value is null. |
