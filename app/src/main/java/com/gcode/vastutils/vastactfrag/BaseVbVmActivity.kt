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

package com.gcode.vastutils.vastactfrag

import android.os.Bundle
import com.gcode.vasttools.base.VastVbVmActivity
import com.gcode.vasttools.utils.LogUtils
import com.gcode.vasttools.utils.getMobileScreenHeight
import com.gcode.vasttools.utils.getMobileScreenWidth
import com.gcode.vastutils.databinding.ActivityBaseVbBinding
import com.gcode.vastutils.viewModel.BaseVM

class BaseVbVmActivity : VastVbVmActivity<ActivityBaseVbBinding,BaseVM>() {

    override fun initView(savedInstanceState: Bundle?) {

        enableFullScreen = true // 启用全面屏

        mBinding.addOne.setOnClickListener {
            mViewModel.addOne()
        }

        mViewModel.count.observe(this){
            mBinding.count.text = it.toString()
        }

    }

}