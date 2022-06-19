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

package com.gcode.vastutils.activity


import android.content.Intent

import android.util.SparseArray
import androidx.fragment.app.Fragment
import java.util.*

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/2/1
 */
class ResultFragment(): Fragment() {

    private val mCallbacks: SparseArray<com.gcode.vastutils.ActivityLauncher.Callback> =
        SparseArray<com.gcode.vastutils.ActivityLauncher.Callback>()

    private val mCodeGenerator: Random = Random()

    fun newInstance(): ResultFragment {
        return ResultFragment()
    }

    fun startActivityForResult(intent: Intent?, callback: com.gcode.vastutils.ActivityLauncher.Callback?) {
        val requestCode = makeRequestCode()
        mCallbacks.put(requestCode, callback)
        requireActivity().startActivityForResult(intent, requestCode)
    }

    /**
     * @return Randomly generate unique requestCode
     */
    private fun makeRequestCode(): Int {
        var requestCode: Int
        var tryCount = 0
        do {
            requestCode = mCodeGenerator.nextInt(0x0000FFFF)
            tryCount++
        } while (mCallbacks.indexOfKey(requestCode) >= 0 && tryCount < 10)
        return requestCode
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCallbacks[requestCode]?.onActivityResult(resultCode, data)
        mCallbacks.remove(requestCode)
    }
}