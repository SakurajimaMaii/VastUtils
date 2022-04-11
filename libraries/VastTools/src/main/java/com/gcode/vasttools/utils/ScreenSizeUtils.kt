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

@file:JvmName("ScreenSizeUtils")

package com.gcode.vasttools.utils

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import android.view.WindowMetrics
import androidx.annotation.RequiresApi

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 15:27
// Description: Help you to get the width and height of screen.
// Documentation: [ScreenSizeUtils](https://sakurajimamaii.github.io/VastDocs/document/en/ScreenSizeUtils.html)

data class ScreenSize(val width: Int, val height:Int)

private var mScreenSize = ScreenSize(0,0)

@RequiresApi(Build.VERSION_CODES.S)
private fun getScreenSizeApi31(context: Context): ScreenSize {
    val vm: WindowMetrics =
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).currentWindowMetrics
    return ScreenSize(vm.bounds.width(),vm.bounds.height())
}

@RequiresApi(Build.VERSION_CODES.R)
private fun getScreenSizeApi30(context: Context): ScreenSize {
    val orientation = context.resources?.configuration?.orientation
    val point = Point()
    context.display?.getRealSize(point)
    return if(orientation == Configuration.ORIENTATION_PORTRAIT){
        ScreenSize(point.x, point.y)
    }else{
        ScreenSize(point.y, point.x)
    }
}

private fun getScreenSizeApi30Below(context: Context): ScreenSize {
    val orientation = context.resources?.configuration?.orientation
    val point = Point()
    val vm: WindowManager =
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    vm.defaultDisplay.getRealSize(point)
    return if(orientation == Configuration.ORIENTATION_PORTRAIT){
        ScreenSize(point.x, point.y)
    }else{
        ScreenSize(point.y, point.x)
    }
}

private fun Context.getMobileScreenSize(): ScreenSize {
    return when {
        (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) -> getScreenSizeApi31(this)
        (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) -> getScreenSizeApi30(this)
        else -> getScreenSizeApi30Below(this)
    }
}

/**
 * Get mobile screen height.
 *
 * @return The width of the screen, in **pixels**
 */
fun Context.getMobileScreenHeight():Int {
    mScreenSize = getMobileScreenSize()
    return mScreenSize.height
}

/**
 * Get mobile screen width.
 *
 * @return The width of the screen, in **pixels**
 */
fun Context.getMobileScreenWidth():Int {
    mScreenSize = getMobileScreenSize()
    return mScreenSize.width
}