package com.gcode.tools.utils

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import android.view.WindowMetrics
import androidx.annotation.RequiresApi

/**
 * Screen size utils
 * Get your device screen size
 * @constructor Create empty Screen size utils
 */
object ScreenSizeUtils {

    @RequiresApi(Build.VERSION_CODES.S)
    fun isAllScreenDeviceApi31(context: Context): Boolean{
        val vm: WindowMetrics =
            (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).currentWindowMetrics
        val width: Float = vm.bounds.width().toFloat()
        val height: Float = vm.bounds.height().toFloat()
        if (height / width >= 1.97f) {
            return true
        }
        return false
    }

    /**
     * Is all screen device Api30 Above
     * @param context Context for the transform.
     * @return `true` if your device is full screen,`false` if your device is not full screen.
     */
    @RequiresApi(Build.VERSION_CODES.R)
    fun isAllScreenDeviceApi30(context: Context): Boolean {
        //后续适配再启用
        val point = Point()
        context.display?.getRealSize(point)
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
     * Is all screen device Api30 Down
     * @param context Context for the transform.
     * @return `true` if your device is full screen,`false` if your device is not full screen.
     */
    fun isAllScreenDeviceApi30Down(context: Context): Boolean {
        //后续适配再启用
        val point = Point()
        val vm: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        vm.defaultDisplay.getRealSize(point)
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
     * Get screen real height(in Api 31)
     * @param context Context for the transform.
     * @return Device Screen Real Height(in pixels).
     */
    @RequiresApi(Build.VERSION_CODES.S)
    private fun getScreenRealHeightApi31(context: Context): Int {
        val vm: WindowMetrics =
            (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).currentWindowMetrics
        return vm.bounds.height()
    }

    /**
     * Get screen real height(in Api 30)
     * @param context Context for the transform.
     * @return Device Screen Real Height(in pixels).
     */
    @RequiresApi(Build.VERSION_CODES.R)
    private fun getScreenRealHeightApi30(context: Context): Int {
        var orientation = context.resources?.configuration?.orientation
        orientation = if (orientation == 1) 0 else 1
        if (sRealSizes[orientation] == null) {
            val point = Point()
            context.display?.getRealSize(point)
            sRealSizes[orientation] = point
        }
        return sRealSizes[orientation]?.y ?: getScreenRealHeightApi30(context)
    }

    /**
     * Get screen real height Api 30 Down
     * @param context Context for the transform.
     * @return Device Screen Real Height(in pixels).
     */
    private fun getScreenRealHeightApi30Down(context: Context): Int {
        var orientation = context.resources?.configuration?.orientation
        orientation = if (orientation == 1) 0 else 1
        if (sRealSizes[orientation] == null) {
            val point = Point()
            val vm: WindowManager =
                context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            vm.defaultDisplay.getRealSize(point)
            sRealSizes[orientation] = point
        }
        return sRealSizes[orientation]?.y ?: getScreenRealHeightApi30Down(context)
    }

    /**
     * Get mobile screen width
     * @param context Context for the transform.
     * @return The width of the screen, in **pixels**
     */
    fun getMobileScreenWidth(context: Context) = context.resources?.displayMetrics?.widthPixels ?: 0

    /**
     * Get mobile screen height Api 30 Above
     * @param context Context for the transform.
     */
    @RequiresApi(Build.VERSION_CODES.S)
    fun getMobileScreenHeightApi31(context: Context) =
        if (isAllScreenDeviceApi31(context)) {
            // 全面屏要通过这个方法获取高度
            getScreenRealHeightApi31(context)
        } else {
            getScreenHeight(context); }

    /**
     * Get mobile screen height Api 30 Above
     * @param context Context for the transform.
     */
    @RequiresApi(Build.VERSION_CODES.R)
    fun getMobileScreenHeightApi30(context: Context) =
        if (isAllScreenDeviceApi30(context)) {
            // 全面屏要通过这个方法获取高度
            getScreenRealHeightApi30(context)
        } else {
            getScreenHeight(context); }

    /**
     * Get mobile screen height Api 30 Down
     * @param context
     */
    fun getMobileScreenHeightApi30Down(context: Context) =
        if (isAllScreenDeviceApi30Down(context)) {
            // 全面屏要通过这个方法获取高度
            getScreenRealHeightApi30Down(context)
        } else {
            getScreenHeight(context); }
}