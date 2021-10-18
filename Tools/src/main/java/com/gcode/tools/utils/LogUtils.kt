package com.gcode.tools.utils

import android.util.Log
import androidx.annotation.Nullable

/**
 * Log utils
 * 日志工具类
 * @constructor Create empty Log utils
 */
object LogUtils {
    //设为false关闭日志
    private var LOG_ENABLE = true

    fun i(`object`: Class<*>, @Nullable key: String?, @Nullable content: String?) {
        if (LOG_ENABLE) {
            BaseLogUtils.log(Log.INFO,`object`, key, content)
        }
    }

    fun v(`object`: Class<*>, @Nullable key: String?, @Nullable content: String?) {
        if (LOG_ENABLE) {
            BaseLogUtils.log(Log.VERBOSE,`object`, key, content)
        }
    }

    fun w(`object`: Class<*>, @Nullable key: String?, @Nullable content: String?) {
        if (LOG_ENABLE) {
            BaseLogUtils.log(Log.WARN,`object`, key, content)
        }
    }

    fun d(`object`: Class<*>, @Nullable key: String?, @Nullable content: String?) {
        if (LOG_ENABLE) {
            BaseLogUtils.log(Log.DEBUG,`object`, key, content)
        }
    }

    fun e(`object`: Class<*>, @Nullable key: String?, @Nullable content: String?) {
        if (LOG_ENABLE) {
            BaseLogUtils.log(Log.ERROR,`object`, key, content)
        }
    }

    fun setLogEnabled(logEnabled: Boolean){
        LOG_ENABLE = logEnabled
    }
}
