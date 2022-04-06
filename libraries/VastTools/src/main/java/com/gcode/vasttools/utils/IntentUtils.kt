/*
 * MIT License
 *
 * Copyright (c) 2021 码上夏雨
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@file:JvmName("IntentUtils")

package com.gcode.vasttools.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.AlarmClock
import android.provider.Settings
import android.util.Log
import androidx.annotation.IntRange
import androidx.annotation.RequiresPermission

/**
 * @Author: Vast Gui
 * @Email: guihy2019@gmail.com
 * @Date: 2022/3/10 15:27
 * @Description: Provides you for some common Intent.
 * @Documentation:
 */

/**
 * Dial phone number
 *
 * **This method does not verify the phone number.** If you want to know
 * if your mobile phone number meets the rules,you can click this link
 * [Rules of International Mobile Phone Numbers](https://support.huaweicloud.com/intl/en-us/productdesc-msgsms/phone_numbers.html)
 *
 * @param phoneNumber Phone number you want to call.
 */
@RequiresPermission(Manifest.permission.CALL_PHONE)
fun Context.dialPhoneNumber(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    } else {
        resolveActivityNullHint()
    }
}

/**
 * Search [query] by webView
 *
 * @param query Content you want to search.
 */
@RequiresPermission(Manifest.permission.INTERNET)
fun Context.searchWeb(query: String) {
    val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
        putExtra(SearchManager.QUERY, query)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    } else {
        resolveActivityNullHint()
    }
}


/**
 * Open [url] by WebView
 *
 * @param url Url you want to open.
 */
fun Context.openWebPage(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    } else {
        resolveActivityNullHint()
    }
}

/**
 * Send message only by SMS app (not other email or social apps)
 *
 * @param message What you want to send.
 * @param phoneNumber Who you want to send.Default value is `null`
 * @param attachment Point to the Uri of the image or video to be
 *     attached. If you are using the ACTION_SEND_MULTIPLE
 *     operation, this extra should be an ArrayList pointing to the
 *     image/video Uri to be attached.And default value is `null`
 */
@SuppressLint("QueryPermissionsNeeded")
@JvmOverloads
@Throws(SecurityException::class)
fun Context.sendMmsMessage(
    message: String = "",
    phoneNumber: String? = null,
    attachment: Uri? = null
) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("smsto:${phoneNumber}")  // This ensures only SMS apps respond
        putExtra("sms_body", message)
        if (attachment != null) {
            putExtra(Intent.EXTRA_STREAM, attachment)
        }
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    } else {
        resolveActivityNullHint()
    }
}


/**
 * Open email only by email apps (not other SMS or social apps)
 *
 * @param addresses A string array containing all the email addresses of
 *     the recipients of the "primary sender".
 * @param subject Subject of the email.Default value is ""
 * @param text Text of the email.Default value is ""
 */
@JvmOverloads
fun Context.openEmail(addresses: Array<String>, subject: String = "", text: String = "") {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:") // only email apps should handle this
        putExtra(Intent.EXTRA_EMAIL, addresses)
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, text)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    } else {
        resolveActivityNullHint()
    }
}


/**
 * Create once alarm If any param perplexes you,please see
 * [createAlarm].
 */
@RequiresPermission(Manifest.permission.SET_ALARM)
@JvmOverloads
fun Context.createOnceAlarm(
    message: String,
    @IntRange(from = 0, to = 23) hour: Int,
    @IntRange(from = 0, to = 59) minutes: Int,
    vibrate: Boolean = false,
    skipUI: Boolean = false,
    music: Uri? = null
) {
    createAlarm(message, hour, minutes, vibrate, skipUI, music)
}

/**
 * Create alarm
 *
 * @param message A custom message for the alarm or timer.
 * @param hour The hour of the alarm.
 * @param minutes The minutes of the alarm.
 * @param vibrate Whether or not to activate the device vibrator.
 *     Default value is `false`.
 * @param skipUI Whether or not to display an activity after performing
 *     the action. Default value is `false`.
 * @param days Weekdays for repeating alarm. The value is an
 *     `Array<Int>`. Each item can be: [Calendar.SUNDAY]
 *     [Calendar.MONDAY] [Calendar.TUESDAY] [Calendar.WEDNESDAY]
 *     [Calendar.THURSDAY] [Calendar.FRIDAY]
 *     [Calendar.SATURDAY] The [days] default is `null`.
 * @param music A ringtone to be played with this alarm. Default value
 *     is `null`.
 */
@RequiresPermission(Manifest.permission.SET_ALARM)
@JvmOverloads
fun Context.createAlarm(
    message: String,
    @IntRange(from = 0, to = 23) hour: Int,
    @IntRange(from = 0, to = 59) minutes: Int,
    vibrate: Boolean = false,
    skipUI: Boolean = false,
    music: Uri? = null,
    days: Array<Int>? = null,
) {
    val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
        putExtra(AlarmClock.EXTRA_MESSAGE, message)
        putExtra(AlarmClock.EXTRA_HOUR, hour)
        putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        putExtra(AlarmClock.EXTRA_VIBRATE, vibrate)
        putExtra(AlarmClock.EXTRA_SKIP_UI, skipUI)
        putExtra(AlarmClock.EXTRA_DAYS, days)
        putExtra(AlarmClock.EXTRA_RINGTONE, music)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    } else {
        resolveActivityNullHint()
    }
}

/**
 * Open wifi settings.
 */
fun Context.openWirelessSettings(){
    val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
    if(intent.resolveActivity(packageManager) != null){
        startActivity(intent)
    }else{
        resolveActivityNullHint()
    }
}

internal fun resolveActivityNullHint() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        Log.w("IntentUtils", "Maybe you don't adding a <queries> declaration to your manifest")
    }
}