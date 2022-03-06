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

package com.gcode.vastswiperecyclerview.model

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.gcode.vastswiperecyclerview.interfaces.VastSwipeMenuClickListener
import com.gcode.vastswipeview.R

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/29
 */

/**
 * VastSwipeMenuItem
 *
 * @param title menu title
 * @param icon menu icon
 * @param background default background is grey
 * @param titleColor default color is black
 */
class VastSwipeMenu @JvmOverloads constructor(
    private val context: Context,
    title: String = context.resources.getString(R.string.default_slide_item_title),
    icon: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_null),
    background: Drawable? = ContextCompat.getDrawable(context, R.drawable.default_bk_menu_item),
    titleColor: Int = ContextCompat.getColor(context, R.color.default_menu_item_title_color),
    clickListener: VastSwipeMenuClickListener? = null
) {
    /**
     * Title
     */
    var title: String = title
        private set

    /**
     * Icon
     */
    var icon: Drawable? = icon
        private set

    /**
     * Background,default background is **grey**.
     */
    var background: Drawable? = background
        private set

    /**
     * Title color,default color is **black**.
     */
    var titleColor = titleColor
        private set

    var clickListener:VastSwipeMenuClickListener? = null
        private set

    /**
     * Set menu title by [title].
     */
    fun setTitleByString(title: String){
        this.title = title
    }

    /**
     * Set menu title by [titleRes].
     */
    fun setTitleByResId(@StringRes titleRes: Int){
        title = context.resources.getString(titleRes)
    }

    /**
     * Set menu icon by [icon].
     */
    fun setIconByDrawable(icon: Drawable){
        this.icon = icon
    }

    /**
     * Set menu icon by [iconResId].
     */
    fun setIconByResId(@DrawableRes iconResId:Int){
        icon = ContextCompat.getDrawable(context, iconResId)
    }

    /**
     * Set menu background by [background].
     */
    fun setBackgroundByDrawable(background: Drawable){
        this.background = background
    }

    /**
     * Set background by [backgroundResId].
     */
    fun setBackgroundByResId(@DrawableRes backgroundResId:Int){
        background = ContextCompat.getDrawable(context, backgroundResId)
    }

    /**
     * Set solid color by [colorInt]
     */
    fun setBackgroundByColorInt(@ColorInt colorInt: Long){
        background = ColorDrawable(colorInt.toInt())
    }

    /**
     * Set titleColor by [colorResId].
     */
    fun setTitleColorByResId(@ColorRes colorResId:Int){
        titleColor = ContextCompat.getColor(context, colorResId)
    }

    /**
     * Set titleColor by [colorInt].
     */
    fun setTitleColorByColorInt(@ColorInt colorInt:Int){
        titleColor = colorInt
    }

    fun setClickListener(clickListener:VastSwipeMenuClickListener){
        this.clickListener = clickListener
    }

}