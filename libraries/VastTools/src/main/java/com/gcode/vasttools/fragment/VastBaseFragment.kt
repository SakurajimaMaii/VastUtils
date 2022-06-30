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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 16:04
// Description: VastBaseFragment.

/**
 * @since 0.0.6
 */
abstract class VastBaseFragment : Fragment() {

    /**
     * When you are not using view binding, you should
     * set [layoutId] to the corresponding view resource
     * id of this Fragment.
     *
     * @since 0.0.6
     */
    protected abstract val layoutId: Int

    /**
     * The binding view of the Fragment
     *
     * @since 0.0.6
     */
    protected var dataBindView: View? = null

    /**
     *
     *
     * @param savedInstanceState
     *
     * @since 0.0.6
     */
    protected abstract fun initView(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if(null == dataBindView){
            inflater.inflate(layoutId, container, false)
        }else{
            dataBindView
        }
    }

}