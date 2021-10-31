package com.gcode.tools.utils

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import com.gcode.tools.internal.validator.UrlValidator
import kotlin.jvm.Throws

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
        return if(UrlValidator.isCompleteUrl(url)){
            val webpage: Uri = Uri.parse(url)
            Intent(Intent.ACTION_VIEW, webpage)
        }else{
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
}