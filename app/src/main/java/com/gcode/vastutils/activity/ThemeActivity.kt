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

package com.gcode.vastutils.activity

import android.os.Bundle
import com.gcode.vasttools.base.VastVbActivity
import com.gcode.vasttools.skin.VastSkinManager
import com.gcode.vasttools.utils.LogUtils
import com.gcode.vasttools.utils.colorHex2Int
import com.gcode.vasttools.utils.colorInt2Hex
import com.gcode.vastutils.databinding.ActivityThemeBinding

class ThemeActivity : VastVbActivity<ActivityThemeBinding>() {

    private val tag = this.javaClass.simpleName

    override fun initView(savedInstanceState: Bundle?) {

        mBinding.start.setOnClickListener {
            VastSkinManager.loadSkin("data/data/com.gcode.vastutils/files/darkskin-debug.apk")
        }

        mBinding.end.setOnClickListener {
            VastSkinManager.loadSkin("")
        }

        LogUtils.d(tag, colorInt2Hex(mBinding.start.currentTextColor))

    }

}