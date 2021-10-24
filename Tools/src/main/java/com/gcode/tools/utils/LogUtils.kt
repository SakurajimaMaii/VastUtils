package com.gcode.tools.utils

import android.util.Log
import androidx.annotation.Nullable

/**
 * Log utils
 * @constructor Create empty Log utils
 */
object LogUtils {
    /**
     * [true] if you want to print log,[false] if you don't want to print the log
     * @See [setLogEnabled] to set the [LOG_ENABLE] value
     */
    private var LOG_ENABLE = false

    /**
     * send info message
     * @param object Incoming Class type can be obtained with [getClass]
     * @param key message keyboard
     * @param content message content
     */
    fun i(`object`: Class<*>, @Nullable key: String?, @Nullable content: String?) {
        if (LOG_ENABLE) {
            BaseLogUtils.log(Log.INFO,`object`, key, content)
        }
    }

    /**
     * send verbose message
     * @param object Incoming Class type can be obtained with [getClass]
     * @param key message keyboard
     * @param content message content
     */
    fun v(`object`: Class<*>, @Nullable key: String?, @Nullable content: String?) {
        if (LOG_ENABLE) {
            BaseLogUtils.log(Log.VERBOSE,`object`, key, content)
        }
    }

    /**
     * send warning message
     * @param object Incoming Class type can be obtained with [getClass]
     * @param key message keyboard
     * @param content message content
     */
    fun w(`object`: Class<*>, @Nullable key: String?, @Nullable content: String?) {
        if (LOG_ENABLE) {
            BaseLogUtils.log(Log.WARN,`object`, key, content)
        }
    }

    /**
     * send debug message
     * @param object Incoming Class type can be obtained with [getClass]
     * @param key message keyboard
     * @param content message content
     */
    fun d(`object`: Class<*>, @Nullable key: String?, @Nullable content: String?) {
        if (LOG_ENABLE) {
            BaseLogUtils.log(Log.DEBUG,`object`, key, content)
        }
    }

    /**
     * send error message
     * @param object Incoming Class type can be obtained with [getClass]
     * @param key message keyboard
     * @param content message content
     */
    fun e(`object`: Class<*>, @Nullable key: String?, @Nullable content: String?) {
        if (LOG_ENABLE) {
            BaseLogUtils.log(Log.ERROR,`object`, key, content)
        }
    }

    /**
     * @param logEnabled [true] if you want to print log
     */
    fun setLogEnabled(logEnabled: Boolean){
        LOG_ENABLE = logEnabled
    }
}
