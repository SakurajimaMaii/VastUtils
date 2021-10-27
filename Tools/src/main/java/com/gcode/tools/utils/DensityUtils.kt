package com.gcode.tools.utils

import android.content.Context

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

    fun px2dip(context: Context, pxValue: Float): Float {
        val scale: Float = context.resources.displayMetrics.density
        return pxValue / scale + 0.5f
    }

    fun dip2px(context: Context, dipValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dipValue * scale + 0.5f
    }

    fun px2sp(context: Context, pxValue: Float): Float {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return pxValue / fontScale + 0.5f
    }

    fun sp2px(context: Context, spValue: Float): Float {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return spValue * fontScale + 0.5f
    }
}