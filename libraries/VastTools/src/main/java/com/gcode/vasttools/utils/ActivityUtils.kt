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

package com.gcode.vasttools.utils

import android.app.Activity
import android.os.Process
import kotlin.system.exitProcess


// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/6/20
// Description: 
// Documentation:

object ActivityUtils {

    private val activities = ArrayList<Activity>()

    /**
     * @param activity the activity you want to add.
     *
     * @since 0.0.9
     */
    @JvmStatic
    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    /**
     * @param activity the activity you want to remove.
     * @return true if [activities] contained the [activity],false
     *     otherwise.
     *
     * @since 0.0.9
     */
    @JvmStatic
    fun removeActivity(activity: Activity): Boolean {
        return activities.remove(activity)
    }

    /**
     * @param clazz the class of the activity you want to get.
     * @return the activity you want to get,null otherwise.
     *
     * @since 0.0.9
     */
    @JvmStatic
    fun getActivity(clazz: Class<*>): Activity? {
        for (activity in activities) {
            if (activity.javaClass == clazz) {
                return activity
            }
        }
        return null
    }

    /**
     * @return [activities].
     *
     * @since 0.0.9
     */
    @JvmStatic
    fun getActivities() = activities

    /**
     *
     */
    @JvmStatic
    fun finishAllActivity() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activities.clear()
    }

    @JvmStatic
    fun exitApp() {
        try {
            finishAllActivity()
            Process.killProcess(Process.myPid())
            exitProcess(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}