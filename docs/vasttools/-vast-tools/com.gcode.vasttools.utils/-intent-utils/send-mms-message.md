//[VastTools](../../../index.md)/[com.gcode.vasttools.utils](../index.md)/[IntentUtils](index.md)/[sendMmsMessage](send-mms-message.md)

# sendMmsMessage

[androidJvm]\

@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()

fun [sendMmsMessage](send-mms-message.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = "", phoneNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, attachment: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)? = null): [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)

Send message only by SMS app (not other email or social apps)

## Parameters

androidJvm

| | |
|---|---|
| message | What you want to send. |
| phoneNumber | Who you want to send.Default value is null |
| attachment | Point to the Uri of the image or video to be attached.     If you are using the ACTION_SEND_MULTIPLE operation,     this extra should be an ArrayList pointing to the     image/video Uri to be attached.And default value is null |
