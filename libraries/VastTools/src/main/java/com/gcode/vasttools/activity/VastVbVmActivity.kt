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
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 16:13
// Description: Please make sure that the activity extends VastVbVmActivity when the activity using viewBinding and viewModel.
// Documentation: [VastBaseActivity](https://sakurajimamaii.github.io/VastDocs/document/en/VastBaseActivity.html)

/**
 * VastVbVmActivity.
 *
 * Here is an example in kotlin:
 *
 * ```kotlin
 * class MainActivity : VastVbVmActivity<ActivityMainBinding,MainViewModel>() {
 *     override fun initView(savedInstanceState: Bundle?) {
 *          // Something to do
 *     }
 * }
 * ```
 *
 * @param VB [ViewBinding] of the activity layout.
 * @param VM [ViewModel] of the activity.
 * @since 0.0.6
 */
abstract class VastVbVmActivity<VB : ViewBinding, VM : ViewModel> : VastActivity() {

    protected lateinit var mBinding: VB
    protected lateinit var mViewModel: VM

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBind()
        mViewModel = createViewModel()
        initView(savedInstanceState)
        initWindow()
        mSnackbar = Snackbar.make(mBinding.root, defaultTag, Snackbar.LENGTH_SHORT)
    }

    @Suppress("UNCHECKED_CAST")
    private fun initDataBind() {
        mBinding = getVbClass(this, 0, layoutInflater)
        setContentView(mBinding.root)
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClass(this, 1))
    }
}