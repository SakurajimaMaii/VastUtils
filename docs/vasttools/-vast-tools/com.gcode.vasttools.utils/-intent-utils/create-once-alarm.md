//[VastTools](../../../index.md)/[com.gcode.vasttools.utils](../index.md)/[IntentUtils](index.md)/[createOnceAlarm](create-once-alarm.md)

# createOnceAlarm

[androidJvm]\

@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()

fun [createOnceAlarm](create-once-alarm.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @[IntRange](https://developer.android.com/reference/kotlin/androidx/annotation/IntRange.html)(from = 0.toLong(), to = 23.toLong())hour: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @[IntRange](https://developer.android.com/reference/kotlin/androidx/annotation/IntRange.html)(from = 0.toLong(), to = 59.toLong())minutes: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), vibrate: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, skipUI: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, music: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)? = null): [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)

Create once alarm If any param perplexes you,please see [createAlarm](create-alarm.md)
