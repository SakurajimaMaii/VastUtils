package com.gcode.vasttools.utils

import android.util.Log
import androidx.annotation.IntRange
import androidx.annotation.Nullable
import com.gcode.vasttools.BuildConfig
import java.util.*

/**
 * Log utils
 */
object LogUtils {

    private var logContent: LogContent? = null

    /**
     * Default maximum length of chars printed of a single log.
     *
     * Notes:Considering fault tolerance, 1000 is set here instead of 1024.
     */
    private const val defaultCharLength = 1000

    /**
     * Default max print repeat times
     */
    private const val defaultMaxPrintTimes = 5

    /**
     * Maximum length of chars printed of a single log.
     */
    var singleLogCharLength = defaultCharLength
        private set

    /**
     * Max print repeat times
     */
    var maxPrintTimes: Int = defaultMaxPrintTimes
        private set

    /**
     * `true` if you want to print log,`false` if you don't want to print the log.
     */
    var logEnabled = true

    /**
     * Set [singleLogCharLength].
     */
    fun setSingleLogCharLength(@IntRange(from = 0, to = 1000) charLength:Int){
        singleLogCharLength = charLength
    }

    /**
     * Set [maxPrintTimes]
     */
    fun setMaxPrintTimes(@IntRange(from = 0) maxPrint: Int){
        maxPrintTimes = maxPrint
    }

    /**
     * Send info message.
     *
     * @param key Message keyboard.
     * @param content Message content.
     */
    fun i(@Nullable key: String?, @Nullable content: String?) {
        if (logEnabled && BuildConfig.DEBUG) {
            logPrint(Log.INFO, key, content)
        }
    }

    /**
     * Send verbose message.
     *
     * @param key Message keyboard.
     * @param content Message content.
     */
    fun v(@Nullable key: String?, @Nullable content: String?) {
        if (logEnabled && BuildConfig.DEBUG) {
            logPrint(Log.VERBOSE, key, content)
        }
    }

    /**
     * Send warning message.
     *
     * @param key Message keyboard.
     * @param content Message content.
     */
    fun w(@Nullable key: String?, @Nullable content: String?) {
        if (logEnabled && BuildConfig.DEBUG) {
            logPrint(Log.WARN, key, content)
        }
    }

    /**
     * Send debug message.
     *
     * @param key Message keyboard.
     * @param content Message content.
     */
    fun d(@Nullable key: String?, @Nullable content: String?) {
        if (logEnabled && BuildConfig.DEBUG) {
            logPrint(Log.DEBUG, key, content)
        }
    }

    /**
     * Send error message.
     *
     * @param key Message keyboard.
     * @param content Message content.
     */
    fun e(@Nullable key: String?, @Nullable content: String?) {
        if (logEnabled && BuildConfig.DEBUG) {
            logPrint(Log.ERROR, key, content)
        }
    }

    /**
     * Define your log format by implementing [LogContent]
     * @param logContent
     */
    fun setLogContentFormat(logContent: LogContent) {
        LogUtils.logContent = logContent
    }

    /**
     * Log print
     *
     * @param priority log level
     * @param key log keyboard
     * @param content log message
     */
    private fun logPrint(
        priority: Int,
        @Nullable key: String?,
        @Nullable content: String?,
    ) {
        // Get the function call stack structure of the current thread.
        val stackTrace = Thread.currentThread().stackTrace

        val methodStackTraceIndex = stackTrace.indexOfLast {
            it.className == this.javaClass.name
        } + 1
        val methodStackTraceElement = stackTrace[methodStackTraceIndex]

        val methodName = methodStackTraceElement.methodName
        val tag = "class (${methodStackTraceElement.fileName}:${methodStackTraceElement.lineNumber}) "
        val parameter: String =
            logContent?.logContentFormat(methodName, key, content) ?: logContentFormat(methodName, key, content)

        print(priority, tag, parameter)
    }

    /**
     * Print the log(In order to solve the length limit)
     *
     * @param priority The priority/type of this log message
     * @param tag Used to identify the source of a log message.It usually identifies
     *            the class or activity where the log call occurs.
     * @param content The message you would like logged.
     */
    private fun print(priority: Int, tag: String, content: String) {
        /**
         * 1. The console can print 4062 bytes at most, and there are slight discrepancies in different situations
         * (note: here are bytes, not characters!!!)
         * 2. The default character set encoding of strings is utf-8, which is a variable-length encoding.
         * One character is represented by 1 to 4 bytes.
         */

        /**
         * Here the character length is less than [singleLogCharLength],
         * then it will be printed directly, avoiding the subsequent process.
         */
        if (content.length < singleLogCharLength) {
            Log.println(priority, tag, content)
            return
        }

        // Convert the content to ByteArray.
        var bytes = content.toByteArray()

        if (singleLogCharLength * 4 >= bytes.size) {
            Log.println(priority, tag, content)
            return
        }

        // Segment printing count
        var count = 1

        var printTheRest = true

        // In the range of the array, print in cycles
        while (singleLogCharLength * 4 < bytes.size) {
            val subStr = cutStr(bytes)

            Log.println(priority, tag, String.format("%s", subStr))

            // Truncate the unprinted bytes
            bytes = bytes.copyOfRange(subStr!!.toByteArray().size, bytes.size)

            if (count == maxPrintTimes) {
                printTheRest = false
                break
            }

            count++
        }

        // Print the unprinted bytes
        if(printTheRest){
            Log.println(priority, tag, String.format("%s", String(bytes)))
        }
    }


    /**
     * Truncate the byte array as a string according to [singleLogCharLength].
     *
     * @param bytes byte array
     * @return The string obtained by [singleLogCharLength]
     */
    private fun cutStr(bytes: ByteArray?): String? {
        // Return when the bytes is null.
        if (bytes == null) {
            return null
        }

        // Return when the bytes length is less than the subLength.
        if (singleLogCharLength * 4 >= bytes.size) {
            return String(bytes)
        }

        // Copy the fixed-length byte array and convert it to a string
        val subStr = String(Arrays.copyOf(bytes, singleLogCharLength * 4))

        // Avoid the end character is split, here minus 1 to keep the string intact
        return subStr.substring(0, subStr.length - 1)
    }

    private fun logContentFormat(methodName: String?, key: String?, content: String?): String {
        return if (key == null || key.trim { it <= ' ' }.isEmpty()) {
            "method: $methodName()  content: $content"
        } else {
            if (content?.isEmpty() == true) {
                "method: $methodName() key: $key"
            } else {
                "method: $methodName() key: $key content: $content"
            }
        }
    }

    interface LogContent {
        fun logContentFormat(methodName: String?, key: String?, content: String?): String
    }
}