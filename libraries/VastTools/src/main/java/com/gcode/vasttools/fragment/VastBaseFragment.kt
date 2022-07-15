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

@file:Suppress("UNCHECKED_CAST")

package com.gcode.vasttools.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 16:04
// Description: VastBaseFragment.

/**
 * @since 0.0.6
 */
sealed class VastBaseFragment : Fragment() {

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
     * Default tag for log.
     *
     * The value of [defaultTag] will be the class name that extends
     * [VastVbFragment] , [VastVmFragment] or [VastVbVmFragment].
     *
     * @since 0.0.9
     */
    protected val defaultTag:String
        get() = this.javaClass.simpleName

    /**
     * @param savedInstanceState
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