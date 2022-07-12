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

package com.gcode.vasttools.activity

import android.content.Context
import com.google.android.material.snackbar.Snackbar


// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/7/6
// Description: 
// Documentation:

/**
 * Base Activity
 *
 * @since 0.0.6
 */
internal interface VastBaseActivity {

    /**
     * The [Context] of the activity.
     *
     * @since 0.0.8
     */
    val mContext: Context

    /**
     * Default tag for log.
     *
     * The value of [defaultTag] will be the class name that extends
     * [VastVbActivity] , [VastVmActivity] or [VastVbVmActivity].
     *
     * @since 0.0.9
     */
    val defaultTag:String

    /**
     * Default [Snackbar] for activity.
     *
     * @since 0.0.9
     */
    val mSnackbar:Snackbar

}