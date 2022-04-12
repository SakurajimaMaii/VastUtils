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

package com.gcode.vastutils

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.gcode.vastutils.activity.ResultFragment


/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/2/1
 */
class ActivityLauncher private constructor(activity: FragmentActivity) {

    companion object {
        const val TAG = "ActLauncherUtils"

        fun init(fragment: Fragment): ActivityLauncher {
            return init(fragment.requireActivity())
        }

        fun init(activity: FragmentActivity): ActivityLauncher {
            return ActivityLauncher(activity)
        }
    }


    private var mContext: Context = activity

    private var mResultFragment: ResultFragment? = null

    init {
        mResultFragment = getResultFragment(activity)
    }

    private fun getResultFragment(activity: FragmentActivity): ResultFragment {
        var resultFragment: ResultFragment? = findResultFragment(activity)
        if (null == resultFragment) {
            resultFragment = ResultFragment().newInstance()
            val fragmentManager: FragmentManager = activity.supportFragmentManager
            fragmentManager
                .beginTransaction()
                .add(resultFragment, TAG)
                .commitAllowingStateLoss()
            fragmentManager.executePendingTransactions()
        }
        return resultFragment
    }

    private fun findResultFragment(activity: FragmentActivity) =
        if (null != activity.supportFragmentManager.findFragmentByTag(TAG)) {
            activity.supportFragmentManager.findFragmentByTag(TAG) as ResultFragment
        } else {
            null
        }

    fun startActivityForResult(clazz: Class<*>?, callback: Callback?) {
        val intent = Intent(mContext, clazz)
        startActivityForResult(intent, callback)
    }

    fun startActivityForResult(intent: Intent?, callback: Callback?) {
        if (mResultFragment != null) {
            mResultFragment!!.startActivityForResult(intent, callback)
        } else {
            throw RuntimeException("please do init first!")
        }
    }

    interface Callback {
        fun onActivityResult(resultCode: Int, data: Intent?)
    }
}