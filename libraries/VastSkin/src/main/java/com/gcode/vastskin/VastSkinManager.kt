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
@file:Suppress("DEPRECATION")

package com.gcode.vastskin

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.text.TextUtils
import com.gcode.vastskin.utils.VastSkinResources
import java.util.*

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/27 18:35
// Description: VastSkin is a non-intrusive skinning framework based on the replacement LayoutInflater.Factory2
// Documentation: [VastSkin](https://sakurajimamaii.github.io/VastDocs/document/en/VastSkin.html)


/**
 * VastSkinManager
 *
 * Using [loadSkin] to load the skin
 * ```kotlin
 * VastSkinManager.loadSkin("data/data/com.gcode.vastutils/files/darkskin-debug.apk")
 * ```
 * If you want to load the default skin ,just do this
 * ```kotlin
 * VastSkinManager.loadSkin(null)
 * ```
 *
 * @since 0.0.6
 */
object VastSkinManager : Observable() {

    private lateinit var originalApplication:Application
    private lateinit var skinActivityLifecycle: VastSkinActivityLifecycle
    // Fix https://github.com/SakurajimaMaii/VastUtils/issues/39
    internal lateinit var sharedPreferences:SharedPreferences

    /**
     * Initialization the [VastSkinManager].
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
    @JvmStatic
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