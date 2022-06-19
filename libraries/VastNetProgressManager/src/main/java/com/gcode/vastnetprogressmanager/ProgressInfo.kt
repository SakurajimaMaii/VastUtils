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

package com.gcode.vastnetprogressmanager

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


// Author: SakurajimaMai
// Email: guihy2019@gmail.com
// Date: 2022/5/29
// Description: 
// Documentation:

@Parcelize
class ProgressInfo(
    val currentBytes:Long,
    val contentLength:Long,
    val intervalTime:Long,
    val eachBytes:Long,
    val id:Long,
    val finish: Boolean
): Parcelable{

    fun getPercent(): Int {
        return if (currentBytes <= 0 || contentLength <= 0) 0 else (100 * currentBytes / contentLength).toInt()
    }


}