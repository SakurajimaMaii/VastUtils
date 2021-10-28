package com.gcode.tools.utils

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import com.gcode.tools.internal.exception.BuildVersionException

/**
 * Screen size utils
 * Get your device screen size
 * @constructor Create empty Screen size utils
 */
object ScreenSizeUtils {

    /**
     * Determine whether your device is full screen
     * @param context Context for the transform.
     * @return [true] if your device is full screen,[false] if your device is not full screen.
     */
    @Throws(BuildVersionException::class)
    fun isAllScreenDevice(context: Context): Boolean {
        //后续适配再启用
        val point = Point()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> { //设备api大于30
                context.display?.getRealSize(point)
            }
            else -> {
                val vm:WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                vm.defaultDisplay.getRealSize(point)
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
     * @param context Context for the transform.
     * @return The height of the screen, in **pixels**
     */
    private fun getScreenHeight(context: Context): Int {
        return context.resources?.displayMetrics?.heightPixels ?: 0
    }

    /**
     * S real sizes
     * Read the defaultDisplay parameter in windowManager
     */
    @Volatile
    private var sRealSizes = arrayOfNulls<Point>(2)

    /**
     * Get screen real height
     * @param context Context for the transform.
     * @return
     */
    private fun getScreenRealHeight(context: Context): Int {
        var orientation = context.resources?.configuration?.orientation
        orientation = if (orientation == 1) 0 else 1
        if (sRealSizes[orientation] == null) {
            val point = Point()
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> { //设备api大于30
                    context.display?.getRealSize(point)
                }
                else -> {
                    val vm:WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                    vm.defaultDisplay.getRealSize(point)
                }
            }
            sRealSizes[orientation] = point
        }
        return sRealSizes[orientation]?.y ?: getScreenRealHeight(context)
    }

    /**
     * Get mobile screen width
     * @param context Context for the transform.
     * @return The width of the screen, in **pixels**
     */
    fun getMobileScreenWidth(context: Context) = context.resources?.displayMetrics?.widthPixels ?: 0

    /**
     * Get mobile screen height
     * @param context Context for the transform.
     */
    @Throws(BuildVersionException::class)
    fun getMobileScreenHeight(context: Context) =
        if (isAllScreenDevice(context)) {
            // 全面屏要通过这个方法获取高度
            getScreenRealHeight(context)
        }
        else { getScreenHeight(context); }
}

