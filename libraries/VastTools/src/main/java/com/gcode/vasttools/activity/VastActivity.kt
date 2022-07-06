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
import androidx.appcompat.app.AppCompatActivity

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 16:20
// Description: BaseVastActivity.

/**
 * The parent class for [VastVmActivity] ,
 * [VastVbActivity] , [VastVbVmActivity].
 */
sealed class VastActivity : AppCompatActivity(),
    VastBaseActivity, VastBaseActivityWindow {

    override var enableActionBar = true

    override var enableFullScreen = false

    override lateinit var mContext: Context

    final override val defaultTag: String
        get() = this.javaClass.simpleName

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
}