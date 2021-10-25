package com.gcode.tools.utils

import android.util.Log
import androidx.annotation.Nullable
import com.gcode.tools.interfaces.LogContent

/**
 * Log utils
 * @constructor Create empty Log utils
 */
object LogUtils: LogContent {
    private val tag = this.javaClass.simpleName
    /**
     * [true] if you want to print log,[false] if you don't want to print the log
     * @See [setLogEnabled] to set the [logEnabled] value
     */
    private var logEnabled = true

    private var logContent: LogContent? = null

    /**
     * send info message
     * @param key message keyboard
     * @param content message content
     */
    fun i(@Nullable key: String?, @Nullable content: String?) {
        if (logEnabled) {
            logPrint(Log.INFO, key, content)
        }
    }

    /**
     * send verbose message
     * @param key message keyboard
     * @param content message content
     */
    fun v(@Nullable key: String?, @Nullable content: String?) {
        if (logEnabled) {
            logPrint(Log.VERBOSE, key, content)
        }
    }

    /**
     * send warning message
     * @param key message keyboard
     * @param content message content
     */
    fun w(@Nullable key: String?, @Nullable content: String?) {
        if (logEnabled) {
            logPrint(Log.WARN, key, content)
        }
    }

    /**
     * send debug message
     * @param key message keyboard
     * @param content message content
     */
    fun d(@Nullable key: String?, @Nullable content: String?) {
        if (logEnabled) {
            logPrint(Log.DEBUG, key, content)
        }
    }

    /**
     * send error message
     * @param key message keyboard
     * @param content message content
     */
    fun e(@Nullable key: String?, @Nullable content: String?) {
        if (logEnabled) {
            logPrint(Log.ERROR, key, content)
        }
    }

    /**
     * @param logEnabled [true] if you want to print log
     */
    fun setLogEnabled(logEnabled: Boolean){
        this.logEnabled = logEnabled
    }

    /**
     * Define your log format by implementing [LogContent]
     * @param logContent
     */
    fun setLogContentFormat(logContent: LogContent){
        LogUtils.logContent = logContent
    }

    /**
     * @param priority log level
     * @param key log keyboard
     * @param content log message
     */
    private fun logPrint(priority: Int, @Nullable key: String?, @Nullable content: String?) {
        // Get the function call stack structure of the current thread.
        val stackTrace = Thread.currentThread().stackTrace

        val methodName = stackTrace[4].methodName
        val tag = "class (${stackTrace[4].fileName}:${stackTrace[4].lineNumber}) "
        val parameter: String = logContent?.logContentFormat(methodName, key, content) ?: logContentFormat(methodName, key, content)

        // Print log by log level.
        when (priority) {
            Log.DEBUG -> Log.d(tag, parameter)
            Log.ERROR -> Log.e(tag, parameter)
            Log.WARN -> Log.w(tag, parameter)
            Log.VERBOSE -> Log.v(tag, parameter)
            Log.INFO -> Log.i(tag, parameter)
        }
    }
}