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

package com.gcode.vasttools.utils

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import com.gcode.vasttools.helper.ContextHelper


// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/6/19
// Description: 
// Documentation:

object ResUtils {

    /**
     * Resources not found tag.
     *
     * @since 0.0.9
     */
    const val RESOURCES_NOT_FOUND = 0

    /**
     * Error color hex string.
     *
     * @since 0.0.9
     */
    private const val ERROR_COLOR = "#c0392b"

    /** Get string by [id]. */
    @JvmStatic
    fun getString(@StringRes id: Int): String =
        ContextHelper.getAppContext().resources.getString(id)

    /**
     * Get drawable by name.
     *
     * @param name the name of the drawable.
     * @return drawable an object that can be used to draw this
     *     resource. null if the drawable an object is not exist.
     * @since 0.0.9
     */
    @JvmStatic
    fun getDrawableByName(name: String): Drawable? {
        val context = ContextHelper.getAppContext()
        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        return if (RESOURCES_NOT_FOUND == resId) {
            null
        } else {
            ResourcesCompat.getDrawable(context.resources, resId, context.theme)
        }
    }

    /**
     * A single color value in the form 0xAARRGGBB. Or color int of
     * [ERROR_COLOR] will be return if resource is not exist.
     *
     * @param id the resource id of color.
     * @since 0.0.9
     */
    @JvmStatic
    fun getColor(@ColorRes id: Int): Int {
        return try {
            ContextHelper.getAppContext().getColor(id)
        } catch (e: Resources.NotFoundException) {
            ColorUtils.colorHex2Int(ERROR_COLOR)
        }
    }

}