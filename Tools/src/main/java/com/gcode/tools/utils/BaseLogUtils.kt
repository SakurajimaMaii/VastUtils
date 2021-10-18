package com.gcode.tools.utils

import android.util.Log
import androidx.annotation.Nullable

/**
 * Base log utils
 * See [https://blog.csdn.net/a1064072510/article/details/83185684]
 * @constructor Create empty Base log utils
 */
internal object BaseLogUtils {

    /**
     * @param priority
     * @param object `object` 传入Class类型 可以用getClass()得到
     * @param key 要输出数据的标识
     * @param content 动态参数，这里是要输出数据的内容
     */
    fun log(priority: Int, `object`: Class<*>, @Nullable key: String?, @Nullable content: String?) {
        val methodName: String
        var className = `object`.name
        className = when {
            className.isEmpty() -> {
                return
            }
            className.contains("$") -> { //用于内部类的名字解析
                className.substring(className.lastIndexOf(".") + 1, className.indexOf("$"))
            }
            else -> {
                className.substring(className.lastIndexOf(".") + 1, className.length)
            }
        }
        val stackTrace = Thread.currentThread().stackTrace
        methodName = stackTrace[4].methodName

        //生成指向java的字符串 加入到TAG标签里面
        val tag = "class ($className:${stackTrace[4].lineNumber}) "

        // 获取输出内容
        val parameter: String = if (key == null || key.trim { it <= ' ' }.isEmpty()) {
            "method: $methodName()  content: $content"
        } else {
            if (content?.isEmpty() == true) {
                "method: $methodName() key: $key"
            } else {
                "method: $methodName() key: $key content: $content"
            }
        }
        when (priority) {
            Log.DEBUG -> Log.d(tag, parameter)
            Log.ERROR -> Log.e(tag, parameter)
            Log.WARN -> Log.w(tag, parameter)
            Log.VERBOSE -> Log.v(tag, parameter)
            Log.INFO -> Log.i(tag, parameter)
        }
    }
}