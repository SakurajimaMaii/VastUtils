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

package com.gcode.vasttools.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * @Author: Vast Gui
 * @Email: guihy2019@gmail.com
 * @Date: 2022/3/10 15:27
 * @Description: Toast utils.
 * @Documentation:
 */

object ToastUtils {
    /**
     * @param context Context
     * @param msg String
     */
    @JvmStatic
    fun showShortMsg(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * @param context Context
     * @param msg String
     */
    @JvmStatic
    fun showLongMsg(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}

fun Context.showShortMsg(msg: String) = ToastUtils.showShortMsg(this, msg)

fun Context.showShortMsg(@StringRes id: Int) = ToastUtils.showShortMsg(
    this, this.resources.getString(id)
)

fun Context.showLongMsg(msg: String) = ToastUtils.showShortMsg(this, msg)

fun Context.showLongMsg(@StringRes id: Int) = ToastUtils.showShortMsg(
    this, this.resources.getString(id)
)