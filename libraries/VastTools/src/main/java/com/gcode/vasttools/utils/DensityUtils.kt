/*
 * MIT License
 *
 * Copyright (c) 2021 码上夏雨
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@file:JvmName("DensityUtils")

package com.gcode.vasttools.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 * @Author: Vast Gui
 * @Date: 2022/3/10 15:27
 * @Description: Provides some basic conversion methods between different dimension.
 * @Documentation:
 */

fun px2dp(pxValue: Float): Float {
    val scale: Float = Resources.getSystem().displayMetrics.density
    return pxValue / scale
}

fun dp2px(dpValue: Float): Float {
    val scale = Resources.getSystem().displayMetrics.density
    return dpValue * scale
}

fun px2sp(pxValue: Float): Float {
    val fontScale = Resources.getSystem().displayMetrics.scaledDensity
    return pxValue / fontScale
}

fun sp2px(spValue: Float): Float {
    val fontScale = Resources.getSystem().displayMetrics.scaledDensity
    return spValue * fontScale
}

fun dp2sp(dpValue: Float): Float {
    val scale = Resources.getSystem().displayMetrics.density
    return px2sp(dpValue * scale)
}

fun sp2dp(spValue: Float): Float {
    val fontScale = Resources.getSystem().displayMetrics.scaledDensity
    return px2dp(spValue * fontScale)
}

/**
 * @return The complex floating point value multiplied by the
 *     appropriate metrics(in density-independent pixels).
 */
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * @return The complex floating point value multiplied by the
 *     appropriate metrics(in scale-independent pixels).
 */
val Float.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * @return The complex floating point value multiplied by the
 *     appropriate metrics(in pixels).
 */
val Float.px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PX,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * @return The complex floating point value multiplied by the
 *     appropriate metrics(in points).
 */
val Float.pt
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PT,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * @return The complex floating point value multiplied by the
 *     appropriate metrics(in inches).
 */
val Float.inches
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_IN,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * @return The complex floating point value multiplied by the
 *     appropriate metrics(in millimeters).
 */
val Float.mm
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_MM,
        this,
        Resources.getSystem().displayMetrics
    )