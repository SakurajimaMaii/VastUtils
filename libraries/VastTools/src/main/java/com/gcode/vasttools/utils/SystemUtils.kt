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