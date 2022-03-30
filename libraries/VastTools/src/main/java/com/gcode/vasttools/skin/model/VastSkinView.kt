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

package com.gcode.vasttools.skin.model

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.gcode.vasttools.skin.VastSkinSupport
import com.gcode.vasttools.skin.utils.VastSkinResources

/**
 * @Author: Vast Gui
 * @Email: guihy2019@gmail.com
 * @Date: 2022/3/30 20:05
 * @Description:
 * @Documentation:
 */

/**
 * VastSkinView is used to change the attr of the [view] specified in [skinPairs]
 */
internal class VastSkinView(private var view: View, private var skinPairs: List<VastSkinPair>) {

    fun applySkin() {
        applySkinSupport()
        for (skinPair in skinPairs) {
            var left: Drawable? = null
            var top: Drawable? = null
            var right: Drawable? = null
            var bottom: Drawable? = null
            when (skinPair.attributeName) {
                "background" -> {
                    val background: Any? =
                        VastSkinResources.getBackground(skinPair.resId)
                    //背景可能是 @color 也可能是 @drawable
                    if (background is Int) {
                        view.setBackgroundColor(background)
                    } else if (null != background) {
                        ViewCompat.setBackground(view, background as Drawable)
                    }
                }
                "src" -> {
                    val background = VastSkinResources.getBackground(skinPair.resId)
                    if (background is Int) {
                        (view as ImageView).setImageDrawable(ColorDrawable(background))
                    } else {
                        (view as ImageView).setImageDrawable(background as Drawable?)
                    }
                }
                "textColor" -> (view as TextView).setTextColor(
                    VastSkinResources.getColorStateList(skinPair.resId)
                )
                "drawableLeft" -> left = VastSkinResources.getDrawable(skinPair.resId)
                "drawableTop" -> top = VastSkinResources.getDrawable(skinPair.resId)
                "drawableRight" -> right =
                    VastSkinResources.getDrawable(skinPair.resId)
                "drawableBottom" -> bottom =
                    VastSkinResources.getDrawable(skinPair.resId)
                else -> {}
            }
            if (null != left || null != right || null != top || null != bottom) {
                (view as TextView).setCompoundDrawablesWithIntrinsicBounds(
                    left, top, right,
                    bottom
                )
            }
        }
    }

    private fun applySkinSupport() {
        if (view is VastSkinSupport) {
            (view as VastSkinSupport).applySkin()
        }
    }
}