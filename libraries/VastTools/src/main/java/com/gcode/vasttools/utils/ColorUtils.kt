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

@file:JvmName("ColorUtils")

package com.gcode.vasttools.utils

import android.content.res.Resources
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 15:27
// Description: ColorUtils Provides methods for converting between different formats of Color.
// Documentation: [ColorUtils](https://sakurajimamaii.github.io/VastDocs/document/en/ColorUtils.html)

/**
 * Converting Color hexadecimal string to int.
 *
 * @param colorHex Color hexadecimal string,for example:#12c2e9.
 */
fun colorHex2Int(colorHex: String): Int {
    return Color.parseColor(colorHex)
}

/**
 * Converting color hexadecimal string to an array of RGB.
 *
 * @param colorHex Color hexadecimal string,for example:#12c2e9.
 * @return For example: {18,194,233}.
 */
fun colorHex2RGB(colorHex: String): IntArray {
    val colorInt = colorHex2Int(colorHex)
    return colorInt2RGB(colorInt)
}

/**
 * Converting color int to hexadecimal string.
 *
 * @return Color hexadecimal string.For example:#3FE2C5.
 */
fun colorInt2Hex(@ColorInt colorInt: Int): String {
    val rgb = colorInt2RGB(colorInt)
    return colorRGB2Hex(rgb)
}

/**
 * Converting color int to an array of RGB.
 *
 * @param colorInt Color int.
 * @return For example: **{63,226,197}**.
 */
fun colorInt2RGB(@ColorInt colorInt: Int): IntArray {
    val result = intArrayOf(0, 0, 0)
    result[0] = Color.red(colorInt)
    result[1] = Color.green(colorInt)
    result[2] = Color.blue(colorInt)
    return result
}

/**
 * Converting an array of RGB to color hexadecimal string.
 *
 * @param rgb For example: **{63,226,197}**.
 * @return Color hexadecimal string.For example:**#3FE2C5**.
 */
fun colorRGB2Hex(rgb: IntArray): String {
    var hexCode = "#"
    for (i in rgb.indices) {
        var rgbItem = rgb[i]
        if (rgbItem < 0) {
            rgbItem = 0
        } else if (rgbItem > 255) {
            rgbItem = 255
        }
        val code =
            arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        val lCode = rgbItem / 16
        val rCode = rgbItem % 16
        hexCode += code[lCode] + code[rCode]
    }
    return hexCode
}

/**
 * Converting an array of RGB to color int.
 *
 * @param rgb For example: **{63,226,197}**.
 * @return Color int
 */
fun colorRGB2Int(rgb: IntArray): Int {
    return Color.rgb(rgb[0], rgb[1], rgb[2])
}