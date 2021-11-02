package com.gcode.tools.utils

import android.util.Log
import androidx.annotation.Nullable
import com.gcode.tools.interfaces.LogContent

/**
 * Log utils
 * @constructor Create empty Log utils
 */
object LogUtils : LogContent {

    /**
     * `true` if you want to print log,`false` if you don't want to print the log
     * @See [setLogEnabled] to set the [logEnabled] value
     */
    private var logEnabled = true

    private var logContent: LogContent? = null

    /**
     * send info message
     *
     * @param clz Current class
     * @param key message keyboard
     * @param content message content
     */
    fun i(@Nullable clz: Class<*>?, @Nullable key: String?, @Nullable content: String?) {
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
     * @param logEnabled [true] if you want to print log
     */
    fun setLogEnabled(logEnabled: Boolean) {
        this.logEnabled = logEnabled
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
            val stackTraceElement = stackTrace.find { it.className == clz.name }?.let {
                val methodName = it.methodName
                val tag = "class (${it.fileName}:${it.lineNumber}) "
                val parameter: String =
                    logContent?.logContentFormat(methodName, key, content) ?: logContentFormat(
                        methodName,
                        key,
                        content
                    )

                // Print log by log level.
                when (priority) {
                    Log.DEBUG -> Log.d(tag, parameter)
                    Log.ERROR -> Log.e(tag, parameter)
                    Log.WARN -> Log.w(tag, parameter)
                    Log.VERBOSE -> Log.v(tag, parameter)
                    Log.INFO -> Log.i(tag, parameter)
                    else -> {
                    }
                }
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
                when (priority) {
                    Log.DEBUG -> Log.d(tag, parameter)
                    Log.ERROR -> Log.e(tag, parameter)
                    Log.WARN -> Log.w(tag, parameter)
                    Log.VERBOSE -> Log.v(tag, parameter)
                    Log.INFO -> Log.i(tag, parameter)
                    else -> {
                    }
                }
            }
        }
    }
}