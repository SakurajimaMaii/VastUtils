package com.gcode.tools.utils

import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.FloatRange
import com.gcode.tools.internal.annotation.UnderTest

/**
 * Density util
 *
 * If you have any questions about the concept of various size units,
 * you can visit the link:
 * [Dimension](https://developer.android.google.cn/guide/topics/resources/more-resources?hl=zh-cn#Dimension)
 *
 * @constructor Create empty Density util
 */
object DensityUtils {

    fun px2dp(@FloatRange(from = 0.0) pxValue: Float): Float {
        val scale: Float = Resources.getSystem().displayMetrics.density
        return pxValue / scale
    }

    fun dp2px(@FloatRange(from = 0.0) dpValue: Float): Float {
        val scale = Resources.getSystem().displayMetrics.density
        return dpValue * scale
    }

    fun px2sp(@FloatRange(from = 0.0) pxValue: Float): Float {
        val fontScale = Resources.getSystem().displayMetrics.scaledDensity
        return pxValue / fontScale
    }

    fun sp2px(@FloatRange(from = 0.0) spValue: Float): Float {
        val fontScale = Resources.getSystem().displayMetrics.scaledDensity
        return spValue * fontScale
    }

    fun dp2sp(@FloatRange(from = 0.0) dpValue: Float): Float {
        val scale = Resources.getSystem().displayMetrics.density
        return px2sp(dpValue * scale)
    }

    /**
     * Convert dp value to float (in pixels)
     *
     * **If you use java,[Float.dp] and [dp2px] have the same result.So just choose one you like**
     */
    @UnderTest
    val Float.dp
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
        )

    /**
     * Convert sp value to float (in pixels)
     *
     * **If you use java,[Float.sp] and [sp2px] have the same result.So just choose one you like**
     */
    @UnderTest
    val Float.sp
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            this,
            Resources.getSystem().displayMetrics
        )
}