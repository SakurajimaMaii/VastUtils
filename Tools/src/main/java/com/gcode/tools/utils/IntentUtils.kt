package com.gcode.tools.utils

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.provider.AlarmClock
import androidx.annotation.IntRange
import com.gcode.tools.internal.annotation.CheckPermission
import com.gcode.tools.internal.validator.UrlValidator
import java.util.*

object IntentUtils {
    /**
     * Dial phone number
     * @param phoneNumber Phone number you want to call.
     */
    fun dialPhoneNumber(phoneNumber: String) =
        Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }


    /**
     * Search [query] by webView
     * @param query Content you want to search.
     */
    fun searchWeb(query: String) =
        Intent(Intent.ACTION_WEB_SEARCH).apply {
            putExtra(SearchManager.QUERY, query)
        }

    /**
     * Open [url] by WebView
     * @param url Url you want to open.
     */
    fun openWebPage(url: String): Intent {
        return if (UrlValidator.isCompleteUrl(url)) {
            val webpage: Uri = Uri.parse(url)
            Intent(Intent.ACTION_VIEW, webpage)
        } else {
            //Just open webView
            Intent(Intent.ACTION_WEB_SEARCH)
        }
    }

    /**
     * Send message only by SMS app (not other email or social apps)
     * @param message What you want to send.
     * @param phoneNumber Who you want to send.Default value is `null`
     * @param attachment Point to the Uri of the image or video to be attached.
     *                   If you are using the ACTION_SEND_MULTIPLE operation,
     *                   this extra should be an ArrayList pointing to the
     *                   image/video Uri to be attached.And default value is `null`
     */
    @JvmOverloads
    @Throws(SecurityException::class)
    fun sendMmsMessage(message: String = "", phoneNumber: String? = null, attachment: Uri? = null) =
        Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto:${phoneNumber}")  // This ensures only SMS apps respond
            putExtra("sms_body", message)
            if (attachment != null) {
                putExtra(Intent.EXTRA_STREAM, attachment)
            }
        }

    /**
     * Open email only by email apps (not other SMS or social apps)
     * @param addresses A string array containing all the email addresses
     *                  of the recipients of the "primary sender".
     * @param subject Subject of the email.Default value is ""
     * @param text Text of the email.Default value is ""
     */
    @JvmOverloads
    fun openEmail(addresses: Array<String>, subject: String = "", text: String = "") =
        Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, text)
        }

    /**
     * Create once alarm
     * If any param perplexes you,please see [createAlarm]
     */
    @JvmOverloads
    @CheckPermission("com.android.alarm.permission.SET_ALARM")
    fun createOnceAlarm(
        message: String,
        @IntRange(from = 0, to = 23) hour: Int,
        @IntRange(from = 0, to = 59) minutes: Int,
        vibrate: Boolean = false,
        skipUI: Boolean = false,
        music: Uri? = null
    ) = createAlarm(message, hour, minutes, vibrate, skipUI, music)

    /**
     * Create alarm
     * @param message A custom message for the alarm or timer.
     * @param hour The hour of the alarm.
     * @param minutes The minutes of the alarm.
     * @param vibrate Whether or not to activate the device vibrator.
     *                Default value is `false`.
     * @param skipUI Whether or not to display an activity after performing the action.
     *               Default value is `false`.
     * @param days Weekdays for repeating alarm.
     *             The value is an `Array<Int>`. Each item can be:
     * [Calendar.SUNDAY]
     * [Calendar.MONDAY]
     * [Calendar.TUESDAY]
     * [Calendar.WEDNESDAY]
     * [Calendar.THURSDAY]
     * [Calendar.FRIDAY]
     * [Calendar.SATURDAY]
     * The [days] default is `null`.
     * @param music A ringtone to be played with this alarm.
     *              Default value is `null`.
     */
    @JvmOverloads
    @CheckPermission("com.android.alarm.permission.SET_ALARM")
    fun createAlarm(
        message: String,
        @IntRange(from = 0, to = 23) hour: Int,
        @IntRange(from = 0, to = 59) minutes: Int,
        vibrate: Boolean = false,
        skipUI: Boolean = false,
        music: Uri? = null,
        days: Array<Int>? = null,
    ) =
        Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
            putExtra(AlarmClock.EXTRA_VIBRATE, vibrate)
            putExtra(AlarmClock.EXTRA_SKIP_UI, skipUI)
            putExtra(AlarmClock.EXTRA_DAYS, days)
            putExtra(AlarmClock.EXTRA_RINGTONE, music)
        }
}