package com.gcode.vasttools.interfaces

import androidx.annotation.Nullable

interface LogContent{
    fun logContentFormat(methodName:String, @Nullable key: String?, @Nullable content: String?):String{
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
}