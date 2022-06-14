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

package com.gcode.vasttools.activity

import android.content.Context


// Author: SakurajimaMai
// Email: guihy2019@gmail.com
// Date: 2022/6/11
// Description: 
// Documentation:

/**
 * @since 0.0.9
 */
internal interface VastBaseAppCompatActivity {

    /**
     * True if you want to show the ActionBar,false otherwise.
     *
     * @since 0.0.6
     */
    var enableActionBar:Boolean

    /**
     * True if you want to set fullscreen,false otherwise.
     *
     * If you set [enableFullScreen] to true,the ActionBar
     * will not be shown.
     *
     * @since 0.0.6
     */
    var enableFullScreen:Boolean

    /**
     * The [Context] of the activity.
     *
     * @since 0.0.8
     */
    val mContext: Context

}