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

package com.gcode.vasttools.skin.utils

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.text.TextUtils
import androidx.core.content.res.ResourcesCompat

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/30 18:52
// Description: Used to loading skin resource from original app or skin apk.

object VastSkinResources {
    private var themePackageName: String? = null
    private var isDefaultTheme = true

    // The original resource of app.
    private lateinit var mAppResources: Resources

    // Theme package resource
    private var mThemeResources: Resources? = null

    fun initSkinResources(context: Context) {
        mAppResources = context.resources
    }

    fun reset() {
        mThemeResources = null
        themePackageName = ""
        isDefaultTheme = true
    }

    fun applySkin(resources: Resources?, pkgName: String?) {
        mThemeResources = resources
        themePackageName = pkgName
        //Whether to use the default theme
        isDefaultTheme = TextUtils.isEmpty(pkgName) || resources == null
    }

    /**
     * 1.Return the entry name for a given resource identifier. 2.Return
     * the type name for a given resource identifier.
     */
    private fun getIdentifier(resId: Int): Int {
        if (isDefaultTheme) {
            return resId
        }
        val resName = mAppResources.getResourceEntryName(resId)
        val resType = mAppResources.getResourceTypeName(resId)
        return mThemeResources!!.getIdentifier(resName, resType, themePackageName)
    }

    /**
     * Enter the [resId] of the main APP and go to the skin APK file to
     * find the color value of the corresponding [resId].
     */
    fun getColor(resId: Int): Int {
        if (isDefaultTheme) {
            return mAppResources.getColor(resId, null)
        }
        val skinId = getIdentifier(resId)
        return if (skinId == 0) {
            mAppResources.getColor(resId, null)
        } else mThemeResources!!.getColor(skinId, null)
    }

    fun getColorStateList(resId: Int): ColorStateList {
        if (isDefaultTheme) {
            return mAppResources.getColorStateList(resId, null)
        }
        val skinId = getIdentifier(resId)
        return if (skinId == 0) {
            mAppResources.getColorStateList(resId, null)
        } else mThemeResources!!.getColorStateList(skinId, null)
    }

    fun getDrawable(resId: Int): Drawable? {
        if (isDefaultTheme) {
            return ResourcesCompat.getDrawable(mAppResources, resId, null)
        }
        //Obtain the resource name and resource type corresponding to the id through
        //the resource of the app, and find the resource id matching the skin package.
        val skinId = getIdentifier(resId)
        return if (skinId == 0) {
            ResourcesCompat.getDrawable(mAppResources, resId, null)
        } else ResourcesCompat.getDrawable(mThemeResources!!, skinId, null)
    }

    /** @return Maybe color or drawable. */
    fun getBackground(resId: Int): Any? {
        val resourceTypeName = mAppResources.getResourceTypeName(resId)
        return if ("color" == resourceTypeName) {
            getColor(resId)
        } else {
            // drawable
            getDrawable(resId)
        }
    }
}