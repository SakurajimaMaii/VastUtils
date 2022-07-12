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

package com.gcode.vasttools.activity

import android.content.Context
import android.os.Bundle
import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import java.lang.reflect.ParameterizedType

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 16:20
// Description: BaseVastActivity.

/**
 * The parent class for [VastVmActivity] , [VastVbActivity] ,
 * [VastVbVmActivity].
 *
 * @since 0.0.9
 */
@Suppress("UNCHECKED_CAST")
sealed class VastActivity : AppCompatActivity(),
    VastBaseActivity, VastBaseActivityWindow {

    override var enableActionBar = true

    override var enableFullScreen = false

    final override lateinit var mContext: Context

    final override val defaultTag: String
        get() = this.javaClass.simpleName

    /**
     * Default [Snackbar] for activity.
     *
     * @since 0.0.9
     */
    protected lateinit var mSnackbar:Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        initBeforeOnCreate()
        super.onCreate(savedInstanceState)
        mContext = this
    }

    /**
     * Initialize some settings before calling super.onCreate().
     *
     * @since 0.0.9
     */
    protected open fun initBeforeOnCreate() {}

    /**
     * Used to replace the [Activity.onCreate] method.
     *
     * @since 0.0.6
     */
    protected abstract fun initView(savedInstanceState: Bundle?)

    /**
     * initialize activity window.
     *
     * @since 0.0.9
     */
    internal fun initWindow() {
        if (!enableActionBar) {
            supportActionBar?.hide()
        }
        if (enableFullScreen) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                val flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
                window.decorView.systemUiVisibility = flags
            } else {
                window.setDecorFitsSystemWindows(false)
                window.insetsController?.apply {
                    hide(WindowInsetsCompat.Type.systemBars())
                    systemBarsBehavior =
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
            }
            supportActionBar?.hide()
            // In order to solve the black bar when state bar is gone.
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                val lp: WindowManager.LayoutParams = window.attributes
                lp.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                window.attributes = lp
            }
        }
    }

    /**
     * Get the [ViewBinding] class.
     *
     * @since 0.0.6
     */
    internal fun <VB> getVbClass(obj: Any, index: Int,layoutInflater: LayoutInflater): VB {
        val superClass = obj.javaClass.genericSuperclass
        val clazz = (superClass as ParameterizedType).actualTypeArguments[index] as Class<*>
        val method = clazz.getMethod("inflate", LayoutInflater::class.java)
        return method.invoke(null, layoutInflater) as VB
    }

    /**
     * Get the [ViewModel] class.
     *
     * @since 0.0.6
     */
    internal fun <VM> getVmClass(obj: Any, index: Int): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[index] as VM
    }
}