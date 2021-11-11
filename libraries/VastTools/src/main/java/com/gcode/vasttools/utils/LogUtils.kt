package com.gcode.vasttools.utils

import android.util.Log
import androidx.annotation.Nullable
import com.gcode.vasttools.interfaces.LogContent
import java.util.*

/**
 * Log utils
 */
object LogUtils : LogContent {

    private var logContent: LogContent? = null

    /**
     * Maximum number of bytes printed at once.
     */
    private const val defaultByteLength = 4000

    /**
     * Default max print repeat times
     */
    private const val defaultMaxPrintTimes = 5

    /**
     * Max print repeat times
     */
    var maxPrintTimes: Int = defaultMaxPrintTimes

    /**
     * `true` if you want to print log,`false` if you don't want to print the log.
     */
    var logEnabled = true

    /**
     * send info message
     *
     * @param clz Current class
     * @param key message keyboard
     * @param content message content
     */
    fun i(@Nullable clz: Class<*>?, @Nullable key: String?, @Nullable content: String) {
        if (logEnabled) {
            logPrint(Log.INFO, key, content, clz)
        }
    }

    /**
     * send verbose message
     * @param clz Current class
     * @param key message keyboard
     * @param content message content
     */
    fun v(@Nullable clz: Class<*>?, @Nullable key: String?, @Nullable content: String?) {
        if (logEnabled) {
            logPrint(Log.VERBOSE, key, content, clz)
        }
    }

    /**
     * send warning message
     * @param clz Current class
     * @param key message keyboard
     * @param content message content
     */
    fun w(@Nullable clz: Class<*>?, @Nullable key: String?, @Nullable content: String?) {
        if (logEnabled) {
            logPrint(Log.WARN, key, content, clz)
        }
    }

    /**
     * send debug message
     * @param clz Current class
     * @param key message keyboard
     * @param content message content
     */
    fun d(@Nullable clz: Class<*>?, @Nullable key: String?, @Nullable content: String?) {
        if (logEnabled) {
            logPrint(Log.DEBUG, key, content, clz)
        }
    }

    /**
     * send error message
     * @param clz Current class
     * @param key message keyboard
     * @param content message content
     */
    fun e(@Nullable clz: Class<*>?, @Nullable key: String?, @Nullable content: String?) {
        if (logEnabled) {
            logPrint(Log.ERROR, key, content, clz)
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
     * @param clz If you want to get the log information of the specified class, please assign a value to the [clz]
     * @param priority log level
     * @param key log keyboard
     * @param content log message
     */
    private fun logPrint(
        priority: Int,
        @Nullable key: String?,
        @Nullable content: String?,
        @Nullable clz: Class<*>?
    ) {
        // Get the function call stack structure of the current thread.
        val stackTrace = Thread.currentThread().stackTrace

        /**
         * Get the stackTrace of the of the [LogUtils]
         */
        val logStackTraceElement = stackTrace.find { it.className == this.javaClass.name }
        // Get the stackTrace of the of the current class
        clz?.let {
            stackTrace.find { it.className == clz.name }?.let {
                val methodName = it.methodName
                val tag = "class (${it.fileName}:${it.lineNumber}) "
                val parameter: String =
                    logContent?.logContentFormat(methodName, key, content) ?: logContentFormat(
                        methodName,
                        key,
                        content
                    )

                // Print log by log level.
                print(priority, tag, parameter)
            } ?: Log.e(
                "class (${logStackTraceElement?.fileName}:${logStackTraceElement?.lineNumber}) ",
                "We can't find the stackTrace about the class ${clz.javaClass.simpleName}"
            ) //print the error message
        } ?: run {
            // If clz is null,we will print all information of the current stackTrace
            for (stackTraceElement in stackTrace) {
                val tag = "class (${stackTraceElement.fileName}:${stackTraceElement.lineNumber}) "
                val parameter = "key:" + (key ?: this.javaClass.simpleName)
                // Print log by log level.
                print(priority, tag, parameter)
            }
        }
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
         * Here the character length is less than 1000, that is, the byte length is less than 4000,
         * then it will be printed directly, avoiding the subsequent process.
         */
        if (content.length < 1000) {
            Log.println(priority, tag, content)
            return
        }

        // Convert the content to ByteArray.
        var bytes = content.toByteArray()

        // Print when defaultByteLength is greater than bytes.size
        if (defaultByteLength >= bytes.size) {
            Log.println(priority, tag, content)
            return
        }

        // Segment printing count
        var count = 1

        // In the range of the array, print in cycles
        while (defaultByteLength < bytes.size) {
            val subStr = cutStr(bytes)

            count++
            Log.println(priority, tag, String.format("%s", subStr))

            // Truncate the unprinted bytes
            bytes = bytes.copyOfRange(subStr!!.toByteArray().size, bytes.size)

            if (count == maxPrintTimes) {
                break
            }
        }

        // Print the unprinted bytes
        Log.println(priority, tag, String.format("%s", String(bytes)))
    }


    /**
     * Truncate the byte array as a string according to [defaultByteLength].
     *
     * @param bytes byte array
     * @return The string obtained by [defaultByteLength]
     */
    private fun cutStr(bytes: ByteArray?): String? {
        // Return when the bytes is null.
        if (bytes == null) {
            return null
        }

        // Return when the bytes length is less than the subLength.
        if (defaultByteLength >= bytes.size) {
            return String(bytes)
        }

        // Copy the fixed-length byte array and convert it to a string
        val subStr = String(Arrays.copyOf(bytes, defaultByteLength))

        // Avoid the end character is split, here minus 1 to keep the string intact
        return subStr.substring(0, subStr.length - 1)
    }
}