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
import android.util.Log
import android.view.View
import com.gcode.vastskin.model.VastSkinPair
import com.gcode.vastskin.model.VastSkinViewWrapper
import com.gcode.vastskin.utils.VastSkinUtils

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/27 18:37
// Description: VastSkinAttribute used to change the attr of view.

internal class VastSkinAttribute {

    private val tag = this::class.java.simpleName

    /**
     * Storing views and their changeably attributes pair.
     *
     * @since 0.0.1
     */
    private val mSkinViews: MutableList<VastSkinViewWrapper> = ArrayList()

    /**
     * The [look] is called in [VastSkinLayoutInflaterFactory.onCreateView],
     * This method will first get the view and it's attrs. After that, the
     * attributes will be checked and filtered to select the attributes that
     * can be modified, stored in `mSkinPairs` according to the format of
     * [VastSkinPair], and finally the view and `mSkinPairs` are stored in
     * `mVastSkinViewWrapper`. `mVastSkinViewWrapper` calling the `applySkin`
     * of to modify the resources corresponding of the view in `mSkinPairs`.
     *
     * @since 0.0.1
     */
    fun look(view: View, attrs: AttributeSet) {
        val mSkinPairs: MutableList<VastSkinPair> = ArrayList()
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
                // If the attribute resource start with "?",
                // for example "?primaryColor".
                val resId: Int = if (attributeValue.startsWith("?")) {
                    val attrId = attributeValue.substring(1).toInt()
                    VastSkinUtils.getResourceId(view.context, intArrayOf(attrId))[0]
                } else {
                    // If the attribute resource start with "@",
                    // for example "@string/zh_change_theme".
                    attributeValue.substring(1).toInt()
                }
                Log.d(tag,"${view::class.java} $attributeName $attributeValue $resId")
                mSkinPairs.add(VastSkinPair(attributeName, resId))
            }
        }
        if (mSkinPairs.isNotEmpty() || view is VastSkinSupport) {
            val mVastSkinViewWrapper = VastSkinViewWrapper(view, mSkinPairs)
            mVastSkinViewWrapper.applySkin()
            mSkinViews.add(mVastSkinViewWrapper)
        }
    }

    /**
     * Change the attr of view in [mSkinViews] when the [VastSkinLayoutInflaterFactory]
     * is changed. The [applySkin] is called in [VastSkinLayoutInflaterFactory.update].
     *
     * @since 0.0.1
     */
    fun applySkin() {
        for (mSkinView in mSkinViews) {
            mSkinView.applySkin()
        }
    }

}