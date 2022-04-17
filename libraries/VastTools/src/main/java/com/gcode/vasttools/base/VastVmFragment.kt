/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gcode.vasttools.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gcode.vasttools.base.extension.getVmClass

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 16:18
// Description:
// Documentation:

/**
 * VastVmActivity.
 *
 * Here is an example in kotlin:
 * ```kotlin
 * // Because don't using the ViewBinding,so just set the layoutId to layout id.
 * class MainFragment(override val layoutId: Int = R.layout.fragment_main) :VastVmFragment<MainViewModel>() {
 *     override fun initView(savedInstanceState: Bundle?) {
 *          // Something to do
 *     }
 * }
 * ```
 *
 * @param VM [ViewModel] of the fragment.
 *
 * @since 0.0.6
 */
abstract class VastVmFragment<VM : ViewModel> : VastBaseFragment() {

    protected lateinit var mViewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewModel = createViewModel()
        return dataBindView ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
    }

    abstract fun initView(savedInstanceState: Bundle?)

    private fun createViewModel(): VM {
        // Fix https://github.com/SakurajimaMaii/VastUtils/issues/42
        // Change this to requireActivity()
        return ViewModelProvider(requireActivity()).get(getVmClass(this, 0))
    }

}