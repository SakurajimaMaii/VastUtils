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
@file:Suppress("DEPRECATION")

package com.gcode.vasttools.skin

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.text.TextUtils
import com.gcode.vasttools.skin.utils.VastSkinResources
import java.util.*

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/27 18:35
// Description: VastSkin is a non-intrusive skinning framework based on the replacement LayoutInflater.Factory2
// Documentation: [VastSkin](https://sakurajimamaii.github.io/VastDocs/document/en/VastSkin.html)

object VastSkinManager : Observable() {

    private lateinit var originalApplication:Application
    private lateinit var skinActivityLifecycle: VastSkinActivityLifecycle
    // Fix https://github.com/SakurajimaMaii/VastUtils/issues/39
    internal lateinit var sharedPreferences:SharedPreferences

    /**
     * You should init [VastSkinManager] in application.
     *
     * If [originalApplication] and [skinActivityLifecycle] is initialized,
     * when you call [initVastThemeManager],it will do nothing.
     */
    fun initVastThemeManager(application:Application){
        if(!this::originalApplication.isInitialized and !this::skinActivityLifecycle.isInitialized){
            originalApplication = application
            VastSkinResources.initSkinResources(originalApplication)
            sharedPreferences = application.getSharedPreferences(THEME_FILE, Context.MODE_PRIVATE)
            // Register the original application as Observer.
            skinActivityLifecycle = VastSkinActivityLifecycle(this)
            originalApplication.registerActivityLifecycleCallbacks(skinActivityLifecycle)
            // Load the skin setting in the last time.
            loadSkin(VastSkinSharedPreferences.skin)
        }
    }

    /**
     * Get the skin and apply it.
     *
     * @param skinPath Theme path,if empty use default skin.
     */
    fun loadSkin(skinPath: String?) {
        if (TextUtils.isEmpty(skinPath)) {
            VastSkinSharedPreferences.reset()
            VastSkinResources.reset()
        } else {
            try {
                // The resources of application.
                val appResource = originalApplication.resources
                // Create AssetManager and Resource by reflection.
                val assetManager = AssetManager::class.java.newInstance()
                // Resource path setting directory or zip.
                val addAssetPath = assetManager.javaClass.getMethod(
                    "addAssetPath",
                    String::class.java
                )
                addAssetPath.invoke(assetManager, skinPath)
                //Create Resources based on the current device display information
                //and configuration (horizontal and vertical screen, language, etc.)
                val themeResource =
                    Resources(assetManager, appResource.displayMetrics, appResource.configuration)

                //Get theme package
                val mPm = originalApplication.packageManager
                val info = mPm.getPackageArchiveInfo(skinPath!!, PackageManager.GET_ACTIVITIES)
                val packageName = info!!.packageName
                VastSkinResources.applySkin(themeResource, packageName)
                VastSkinSharedPreferences.skin = skinPath
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        setChanged()
        notifyObservers(this)
    }

}