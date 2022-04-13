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

package com.gcode.vasttools.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 15:27
// Description: Toast utils.
// Documentation: [ToastUtils](https://sakurajimamaii.github.io/VastDocs/document/en/ToastUtils.html)

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