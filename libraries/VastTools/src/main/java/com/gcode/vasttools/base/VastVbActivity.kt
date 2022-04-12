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
import androidx.viewbinding.ViewBinding
import com.gcode.vasttools.base.extension.getVbClass
import com.gcode.vasttools.base.extension.initSettings

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 16:05
// Description: Please make sure that the activity extends VastVbActivity when the activity using viewbinding.

abstract class VastVbActivity<VB : ViewBinding> : VastBaseActivity() {

    protected lateinit var mBinding: VB

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBind()
        initView(savedInstanceState)
        initSettings()
    }

    abstract fun initView(savedInstanceState: Bundle?)

    @Suppress("UNCHECKED_CAST")
    private fun initDataBind() {
        mBinding = getVbClass(this,0,layoutInflater)
        setContentView(mBinding.root)
    }

}