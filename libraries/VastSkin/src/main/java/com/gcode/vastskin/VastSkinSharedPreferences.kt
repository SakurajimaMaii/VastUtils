/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gcode.vastskin

import android.content.SharedPreferences

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/27 19:47
// Description:
// Documentation:

/**
 * VastSkinSharedPreferences is used to get the skin path from [SharedPreferences]
 *
 * @since 0.0.6
 */
internal object VastSkinSharedPreferences {

    fun reset() {
        VastSkinManager.sharedPreferences.edit().apply {
            remove(THEME_PATH)
            apply()
        }
    }

    var skin: String?
        get() = VastSkinManager.sharedPreferences.getString(THEME_PATH, null)
        set(skinPath) {
            VastSkinManager.sharedPreferences.edit().apply {
                putString(THEME_PATH, skinPath)
                apply()
            }
        }

}