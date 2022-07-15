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

package com.gcode.vastskin

import android.util.AttributeSet
import android.view.View
import com.gcode.vastskin.model.VastSkinPair
import com.gcode.vastskin.model.VastSkinView
import com.gcode.vastskin.utils.VastSkinUtils

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/27 18:37
// Description: VastSkinAttribute used to change the attr of view.

internal class VastSkinAttribute {

    private val mSkinViews: MutableList<VastSkinView> = ArrayList()

    /**
     * Firstly,select the attributes that the [view] changeably.
     *
     * Secondly,change the attributes of the [view].
     */
    fun look(view: View, attrs: AttributeSet) {
        val mSkinPars: MutableList<VastSkinPair> = ArrayList()
        for (i in 0 until attrs.attributeCount) {
            // Get the view attributes name.
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

    /** Change the attr of view in [mSkinViews] */
    fun applySkin() {
        for (mSkinView in mSkinViews) {
            mSkinView.applySkin()
        }
    }

}