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