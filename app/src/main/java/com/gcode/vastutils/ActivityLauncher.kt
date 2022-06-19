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