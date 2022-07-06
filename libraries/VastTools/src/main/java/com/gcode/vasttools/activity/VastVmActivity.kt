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

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gcode.vasttools.extension.getVmClass
import com.gcode.vasttools.extension.initSettings

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 16:14
// Description: Please make sure that the activity extends VastVmActivity when the activity using viewModel.
// Documentation: [VastBaseActivity](https://sakurajimamaii.github.io/VastDocs/document/en/VastBaseActivity.html)

/**
 * VastVmActivity.
 *
 * Here is an example in kotlin:
 * ```kotlin
 * class MainActivity : VastVmActivity<MainViewModel>() {
 *     override fun initView(savedInstanceState: Bundle?) {
 *          // Something to do
 *     }
 * }
 * ```
 *
 * @param VM [ViewModel] of the activity.
 *
 * @since 0.0.6
 */
abstract class VastVmActivity<VM : ViewModel> : VastActivity() {

    /**
     * The layout resource id for this activity.
     *
     * If you want to use [VastVmActivity] for [Jetpack Compose](https://developer.android.com/jetpack/compose),
     * please set 0 to [layoutId].
     *
     * @since 0.0.6
     */
    protected abstract val layoutId: Int

    protected lateinit var mViewModel: VM

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(0 != layoutId){
            setContentView(layoutId)
        }
        mViewModel = createViewModel()
        initView(savedInstanceState)
        initSettings()
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClass(this, 0))
    }

}