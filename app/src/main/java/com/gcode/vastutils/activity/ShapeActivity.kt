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

import android.graphics.drawable.GradientDrawable.RECTANGLE
import android.os.Bundle
import android.os.PersistableBundle
import com.gcode.vasttools.base.VastVbActivity
import com.gcode.vasttools.utils.*
import com.gcode.vastutils.databinding.ActivityShapeBinding

class ShapeActivity : VastVbActivity<ActivityShapeBinding>() {

    override fun initView(savedInstanceState: Bundle?) {

        validateIDCardNumber("123456789101112131")

        val states = arrayOfNulls<IntArray>(6).apply {
            set(0, intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled))
            set(1, intArrayOf(android.R.attr.state_focused, android.R.attr.state_enabled))
            set(2, intArrayOf(-android.R.attr.state_focused, android.R.attr.state_enabled))
            set(3, intArrayOf(android.R.attr.state_focused))
            set(4, intArrayOf(android.R.attr.state_window_focused))
            set(5, intArrayOf())
        }

        val colorList = IntArray(6).apply {
            set(0, colorHex2Int("#00F260"))
            set(1, colorHex2Int("#FFFFFF"))
            set(2, colorHex2Int("#0575E6"))
            set(3, colorHex2Int("#FFFFFF"))
            set(4, colorHex2Int("#EF3B36"))
            set(5, colorHex2Int("#0575E6"))
        }

        val btnbk1 = ShapeAndStateUtils.create()
            .setShape(RECTANGLE)
            .setRadius(50f)
            .setStroke(15f, colorHex2Int("#3E5151"))
            .setBgColorStateList(states,colorList)
            .build()

        val btnbk2 = ShapeAndStateUtils.create()
            .setShape(RECTANGLE)
            .setRadius(50f)
            .setGradient(45, colorHex2Int("#0F2027"),colorHex2Int("#78ffd6"))
            .build()

        mBinding.btn1.background = btnbk1

        //mBinding.btn2.background = btnbk2
    }

}