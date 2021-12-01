//[VastTools](../../../index.md)/[com.gcode.vasttools.utils](../index.md)/[IntentUtils](index.md)/[openEmail](open-email.md)

# openEmail

[androidJvm]\

@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()

fun [openEmail](open-email.md)(addresses: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>, subject: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = "", text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = ""): [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)

Open email only by email apps (not other SMS or social apps)

## Parameters

androidJvm

| | |
|---|---|
| addresses | A string array containing all the email addresses     of the recipients of the "primary sender". |
| subject | Subject of the email.Default value is "" |
| text | Text of the email.Default value is "" |
