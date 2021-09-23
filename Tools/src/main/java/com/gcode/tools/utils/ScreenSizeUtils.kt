package com.gcode.tools.utils

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import com.gcode.tools.exception.BuildVersionException


/**
 * 获取手机属性工具类
 */
object ScreenSizeUtils {

    /**
     * Is all screen device
     * @param activity
     * @return
     */
    @Throws(BuildVersionException::class)
    fun isAllScreenDevice(activity: Activity): Boolean {
        //后续适配再启用
        val point = Point()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> { //设备api大于30
                activity.baseContext.display?.getRealSize(point)
            }
            else -> {
                activity.windowManager.defaultDisplay.getRealSize(point)
            }
        }
        val width: Float
        val height: Float
        if (point.x < point.y) {
            width = point.x.toFloat()
            height = point.y.toFloat()
        } else {
            width = point.y.toFloat()
            height = point.x.toFloat()
        }
        if (height / width >= 1.97f) {
            return true
        }
        return false
    }

    /**
     * Get screen height
     * Read the heightPixels parameter of DisplayMetrics
     * @param activity
     * @return
     */
    private fun getScreenHeight(activity: Activity): Int {
        return activity.resources?.displayMetrics?.heightPixels ?: 0
    }

    /**
     * S real sizes
     * Read the defaultDisplay parameter in windowManager
     */
    @Volatile
    private var sRealSizes = arrayOfNulls<Point>(2)

    /**
     * Get screen real height
     * @param activity
     * @return
     */
    private fun getScreenRealHeight(activity: Activity): Int {
        var orientation = activity.resources?.configuration?.orientation
        orientation = if (orientation == 1) 0 else 1
        if (sRealSizes[orientation] == null) {
            activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> { //设备api大于30
                    activity.baseContext.display?.getRealSize(point)
                }
                else -> {
                    activity.windowManager.defaultDisplay.getRealSize(point)
                }
            }
            sRealSizes[orientation] = point
        }
        return sRealSizes[orientation]?.y ?: getScreenRealHeight(activity)
    }

    /**
     * Get mobile screen width
     * @param activity
     */
    fun getMobileScreenWidth(activity: Activity) = activity.resources?.displayMetrics?.widthPixels ?: 0

    /**
     * Get mobile screen height
     * @param activity
     */
    @Throws(BuildVersionException::class)
    fun getMobileScreenHeight(activity: Activity) =
        if (isAllScreenDevice(activity)) {
            // 全面屏要通过这个方法获取高度
            getScreenRealHeight(activity)
        }
        else { getScreenHeight(activity); }
}

