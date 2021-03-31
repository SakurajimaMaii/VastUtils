package com.gcode.gutils

import android.app.Activity

/**
 * Activity管理工具类
 */
object ActivityCollectorUtils {
    private val activities = ArrayList<Activity>()

    /**
     * 添加activity
     * @param activity Activity
     */
    fun addActivity(activity: Activity){
        activities.add(activity)
    }

    /**
     * 移出activity
     * @param activity Activity
     */
    fun removeActivity(activity: Activity){
        activities.remove(activity)
    }

    fun finishAll(){
        for(activity in activities){
            if(!activity.isFinishing)
                activity.finish()
        }
        activities.clear()
    }
}