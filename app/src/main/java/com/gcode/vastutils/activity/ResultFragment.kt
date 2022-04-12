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