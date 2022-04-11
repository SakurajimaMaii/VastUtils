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

@file:JvmName("SystemUtils")

package com.gcode.vasttools.utils

import android.os.Build
import java.util.*

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 15:27
// Description: Help you to get the system information.
// Documentation: [SystemUtils](https://sakurajimamaii.github.io/VastDocs/document/en/SystemUtils.html)

/**
 * Returns the language code of this Locale.
 *
 * For example: the current setting is "Chinese-China", then "zh" will
 * be returned.
 */
val systemLanguage: String
    get() = Locale.getDefault().language

/** Returns an array of all installed locales. */
val systemLanguageList: Array<out Locale>
    get() = Locale.getAvailableLocales()

/**
 * Get the current mobile phone android version.
 *
 * For example: "11" will be returned.
 */
val systemAndroidVersion: String
    get() = Build.VERSION.RELEASE

/** Returns the end-user-visible name for the end product. */
val systemModel: String
    get() = Build.MODEL

/**
 * Returns the consumer-visible brand with which the product/hardware
 * will be associated, if any.
 */
val deviceBrand: String
    get() = Build.BRAND