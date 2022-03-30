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
package com.gcode.vasttools.skin.utils

import android.app.Activity
import android.content.Context
import com.gcode.vasttools.R
import com.gcode.vasttools.skin.utils.VastSkinResources.getColor

object VastSkinUtils {

    private val APPCOMPAT_COLOR_PRIMARY_DARK_ATTRS = intArrayOf(
        R.attr.colorPrimaryDark
    )
    private val STATUS_BAR_COLOR_ATTRS = intArrayOf(
        android.R.attr.statusBarColor, android.R.attr.navigationBarColor
    )

    fun getResId(context: Context, attrs: IntArray): IntArray {
        val resIds = IntArray(attrs.size)
        val a = context.obtainStyledAttributes(attrs)
        for (i in attrs.indices) {
            resIds[i] = a.getResourceId(i, 0)
        }
        a.recycle()
        return resIds
    }

    fun updateStatusBarColor(activity: Activity) {

        val resIds = getResId(activity, STATUS_BAR_COLOR_ATTRS)
        val statusBarColorResId = resIds[0]
        val navigationBarColor = resIds[1]

        if (statusBarColorResId != 0) {
            val color = getColor(statusBarColorResId)
            activity.window.statusBarColor = color
        } else {
            val colorPrimaryDarkResId = getResId(activity, APPCOMPAT_COLOR_PRIMARY_DARK_ATTRS)[0]
            if (colorPrimaryDarkResId != 0) {
                val color = getColor(colorPrimaryDarkResId)
                activity.window.statusBarColor = color
            }
        }
        if (navigationBarColor != 0) {
            val color = getColor(navigationBarColor)
            activity.window.navigationBarColor = color
        }
    }
}