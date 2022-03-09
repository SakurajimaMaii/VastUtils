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

@file:JvmName("ColorUtils")

package com.gcode.vasttools.utils

import android.content.res.Resources
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

/**
 * @OriginalAuthor: Vast Gui @OriginalDate: @EditAuthor: Vast Gui
 * @EditDate: 2022/2/18
 */

/**
 * Get color int value by [resId]
 *
 * @param resId The resource id of color
 * @return The int value of the color
 */
fun getColor(@ColorRes resId: Int): Int {
    return Resources.getSystem().getColor(resId, null)
}

/**
 * Color hexadecimal string convert to int.
 *
 * @param colorHex Color hexadecimal string,for example:#12c2e9.
 */
fun colorHex2Int(colorHex: String): Int {
    return Color.parseColor(colorHex)
}

/**
 * Color hexadecimal string convert to an array of RGB.
 *
 * @param colorHex Color hexadecimal string,for example:#12c2e9.
 * @return For example: **{63,226,197}**.
 */
fun colorHex2RGB(colorHex: String): IntArray {
    val colorInt = colorHex2Int(colorHex)
    return colorInt2RGB(colorInt)
}

/**
 * Color int convert to hexadecimal string.
 *
 * @return Color hexadecimal string.For example:**#3FE2C5**.
 */
fun colorInt2Hex(@ColorInt colorInt: Int): String {
    val rgb = colorInt2RGB(colorInt)
    return colorRGB2Hex(rgb)
}

/**
 * Color int convert to an array of RGB.
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
 * An array of RGB convert to color hexadecimal string.
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
 * An array of RGB convert to color int.
 *
 * @param rgb For example: **{63,226,197}**.
 * @return Color int
 */
fun colorRGB2Int(rgb: IntArray): Int {
    return Color.rgb(rgb[0], rgb[1], rgb[2])
}