/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gcode.vastskin.utils

import android.app.Activity
import android.content.Context
import com.gcode.vastskin.utils.VastSkinResources.getColor

object VastSkinUtils {

    private val APPCOMPAT_COLOR_PRIMARY_DARK_ATTRS = intArrayOf(
        android.R.attr.colorPrimaryDark
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