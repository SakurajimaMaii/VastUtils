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

package com.gcode.vasttools.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class VastVbVmActivity<VB: ViewBinding,VM: ViewModel> : VastVmActivity<VM>() {

    protected lateinit var mBinding:VB

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBind()
        onActCreate(savedInstanceState,null)
    }

    final override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initDataBind()
        onActCreate(savedInstanceState, persistentState)
    }

    /**
     * Used to replace the original onCreate method
     *
     * By default, persistentState value is null
     */
    abstract fun onActCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle? = null)

    @Suppress("UNCHECKED_CAST")
    private fun initDataBind() {
        val superClass = javaClass.genericSuperclass
        val clazz = (superClass as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = clazz.getMethod("inflate", LayoutInflater::class.java)
        mBinding = method.invoke(null, layoutInflater) as VB
        setContentView(mBinding.root)
    }
}