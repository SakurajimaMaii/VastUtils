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