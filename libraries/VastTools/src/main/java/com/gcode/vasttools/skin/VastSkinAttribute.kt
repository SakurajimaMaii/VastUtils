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

package com.gcode.vasttools.skin

import android.util.AttributeSet
import android.view.View
import com.gcode.vasttools.skin.model.VastSkinPair
import com.gcode.vasttools.skin.model.VastSkinView
import com.gcode.vasttools.skin.utils.VastSkinUtils

/**
 * @Author: Vast Gui
 * @Email: guihy2019@gmail.com
 * @Date: 2022/3/27 18:37
 * @Description:
 * @Documentation:
 */

/**
 * VastSkinAttribute used to change the attr of view.
 */
class VastSkinAttribute {

    private val mSkinViews: MutableList<VastSkinView> = ArrayList()

    /**
     * Firstly,select the attributes that the [view] can modify.
     * Secondly,change the attributes of the [view].
     */
    fun look(view: View, attrs: AttributeSet) {
        val mSkinPars: MutableList<VastSkinPair> = ArrayList()
        for (i in 0 until attrs.attributeCount) {
            // Get the view attr name.
            val attributeName = attrs.getAttributeName(i)
            if (ChangeablyAttrs.contains(attributeName)) {
                val attributeValue = attrs.getAttributeValue(i)
                // If the color start with "#",for example "#000000",
                // It can not be change.
                if (attributeValue.startsWith("#")) {
                    continue
                }
                // If the color start with "?",for example "?primaryColor",
                val resId: Int = if (attributeValue.startsWith("?")) {
                    val attrId = attributeValue.substring(1).toInt()
                    VastSkinUtils.getResId(view.context, intArrayOf(attrId))[0]
                } else {
                    // If the color start with "@",for example "?primaryColor",
                    attributeValue.substring(1).toInt()
                }
                mSkinPars.add(VastSkinPair(attributeName, resId))
            }
        }
        if (mSkinPars.isNotEmpty() || view is VastSkinSupport) {
            val skinView = VastSkinView(view, mSkinPars)
            skinView.applySkin()
            mSkinViews.add(skinView)
        }
    }

    /**
     * Change the attr of view in [mSkinViews]
     */
    fun applySkin() {
        for (mSkinView in mSkinViews) {
            mSkinView.applySkin()
        }
    }

}