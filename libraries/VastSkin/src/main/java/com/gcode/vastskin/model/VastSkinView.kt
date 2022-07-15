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

package com.gcode.vastskin.model

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.gcode.vastskin.*
import com.gcode.vastskin.CHANGEABLY_BACKGROUND
import com.gcode.vastskin.CHANGEABLY_DRAWABLE_BOTTOM
import com.gcode.vastskin.CHANGEABLY_DRAWABLE_LEFT
import com.gcode.vastskin.CHANGEABLY_DRAWABLE_RIGHT
import com.gcode.vastskin.CHANGEABLY_DRAWABLE_TOP
import com.gcode.vastskin.CHANGEABLY_SRC
import com.gcode.vastskin.CHANGEABLY_TEXT
import com.gcode.vastskin.CHANGEABLY_TEXT_COLOR
import com.gcode.vastskin.utils.VastSkinResources

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/30 20:05
// Description: VastSkinView is used to change the attr of the view specified in skinPairs.

/**
 * VastSkinView.
 *
 * @property view view which need to change the attributes.
 * @property skinPairs list of [VastSkinView].
 *
 * @since 0.0.6
 */
internal class VastSkinView(private var view: View, private var skinPairs: List<VastSkinPair>) {

    fun applySkin() {
        applySkinSupport()
        for (skinPair in skinPairs) {
            // Fix https://github.com/SakurajimaMaii/VastUtils/issues/38
            if(0 != skinPair.resId){
                var left: Drawable? = null
                var top: Drawable? = null
                var right: Drawable? = null
                var bottom: Drawable? = null
                when (skinPair.attributeName) {
                    CHANGEABLY_BACKGROUND -> {
                        val background: Any? =
                            VastSkinResources.getBackground(skinPair.resId)
                        if (background is Int) {
                            view.setBackgroundColor(background)
                        } else if (null != background) {
                            ViewCompat.setBackground(view, background as Drawable)
                        }
                    }
                    CHANGEABLY_SRC -> {
                        val background = VastSkinResources.getBackground(skinPair.resId)
                        if (background is Int) {
                            (view as ImageView).setImageDrawable(ColorDrawable(background))
                        } else {
                            (view as ImageView).setImageDrawable(background as Drawable?)
                        }
                    }
                    CHANGEABLY_TEXT -> (view as TextView).text = VastSkinResources.getText(skinPair.resId)
                    CHANGEABLY_TEXT_COLOR -> (view as TextView).setTextColor(
                        VastSkinResources.getColorStateList(skinPair.resId)
                    )
                    CHANGEABLY_DRAWABLE_LEFT -> left = VastSkinResources.getDrawable(skinPair.resId)
                    CHANGEABLY_DRAWABLE_TOP -> top = VastSkinResources.getDrawable(skinPair.resId)
                    CHANGEABLY_DRAWABLE_RIGHT -> right =
                        VastSkinResources.getDrawable(skinPair.resId)
                    CHANGEABLY_DRAWABLE_BOTTOM -> bottom =
                        VastSkinResources.getDrawable(skinPair.resId)
                    else -> {}
                }
                if (null != left || null != right || null != top || null != bottom) {
                    (view as TextView).setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
                }
            }
        }
    }

    private fun applySkinSupport() {
        if (view is VastSkinSupport) {
            (view as VastSkinSupport).applySkin()
        }
    }
}