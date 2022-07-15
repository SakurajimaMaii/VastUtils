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

package com.gcode.vasttools.fragment

import androidx.lifecycle.ViewModel
import com.gcode.vasttools.extension.NotNUllSingleVar


// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/7/15
// Description: 
// Documentation:

sealed class VastBaseVmFragment:VastBaseFragment() {

    /**
     * When [vmBySelf] is true, the ViewModel representing the Fragment
     * is retained by itself.When you want the ViewModel to be retained
     * by its associated Activity, please set [vmBySelf] to false.
     *
     * You can only set [vmBySelf] through [initVmBySelf], the default value
     * of [vmBySelf] is false.
     *
     * @since 0.0.9
     */
    protected var vmBySelf:Boolean by NotNUllSingleVar()

    /**
     * Initialize the [vmBySelf].
     *
     * @since 0.0.9
     */
    protected abstract fun initVmBySelf():Boolean

    /**
     * Return a [ViewModel].
     *
     * If you want to initialization a [ViewModel] with parameters,just do like this:
     * ```kotlin
     * override fun createViewModel(modelClass: Class<out ViewModel>): ViewModel {
     *      return MainSharedVM("MyVM")
     * }
     * ```
     *
     * @param modelClass by default, Fragment will get the [ViewModel] by `modelClass.newInstance()`.
     * @return the [ViewModel] of the Fragment.
     * @since 0.0.9
     */
    protected abstract fun createViewModel(modelClass: Class<out ViewModel>): ViewModel

}