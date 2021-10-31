package com.gcode.tools.internal.validator

import java.util.regex.Pattern

/**
 * Url validator
 * In order to validate whether it is a Url
 * @constructor Create empty Url validator
 */
internal object UrlValidator {
    private val Top_level_domain = arrayOf(  "top", "com.cn", "com", "net", "cn", "cc", "gov", "cn", "hk")

    /**
     * Verify that the Url is correct
     */
    fun isCompleteUrl(urlToBeVerified: String): Boolean {
        val stringBuffer = StringBuilder().apply {
            append("(")
            for (f in Top_level_domain) {
                append(f)
                append("|")
            }
            deleteCharAt(this.length - 1)
            append(")")
        }
        val p = Pattern.compile("((https?|s?ftp|irc[6s]?|git|afp|telnet|smb)://)((\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})|((www\\.|[a-zA-Z\\.\\-]+\\.)?[a-zA-Z0-9\\-]+\\.$stringBuffer(:[0-9]{1,5})?))((/[a-zA-Z0-9\\./,;\\?'\\+&%\\$#=~_\\-]*)|([^\\u4e00-\\u9fa5\\s0-9a-zA-Z\\./,;\\?'\\+&%\\$#=~_\\-]*))", Pattern.CASE_INSENSITIVE);
        val matcher = p.matcher(urlToBeVerified)
        return matcher.matches()
    }
}