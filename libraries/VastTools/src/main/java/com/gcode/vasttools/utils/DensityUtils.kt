/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("DensityUtils")

package com.gcode.vasttools.utils

import android.content.res.Resources
import android.util.TypedValue

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 15:27
// Description: DensityUtils provide you with some methods to convert different dimensions.
// Documentation: [DensityUtils](https://sakurajimamaii.github.io/VastDocs/document/en/DensityUtils.html)

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