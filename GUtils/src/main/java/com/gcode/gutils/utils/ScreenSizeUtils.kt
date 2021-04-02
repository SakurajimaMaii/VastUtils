package com.gcode.gutils.utils

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import androidx.annotation.RequiresApi


/**
 * 获取手机属性工具类
 */
object ScreenSizeUtils {

    /**
     * 判断是否是全面屏
     * @param context Context
     * @return Boolean
     */
    @RequiresApi(Build.VERSION_CODES.R)
    private fun isAllScreenDevice(context: Context): Boolean {
        //后续适配再启用
        //if (Build.VERSION.SDK_INT < 21) return false
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
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
     * 获取屏幕高度
     * 第一种，读取DisplayMetrics的heightPixels参数
     */
    private fun getScreenHeight(context: Context): Int {
        return context.resources?.displayMetrics?.heightPixels ?: 0
    }

    /**
     * 获取屏幕Real高度
     * 第二种，读取windowManager里面的defaultDisplay参数
     */
    @Volatile
    private var sRealSizes = arrayOfNulls<Point>(2)
    @RequiresApi(Build.VERSION_CODES.R)
    private fun getScreenRealHeight(context: Context): Int {
        var orientation = context.resources?.configuration?.orientation
        orientation = if (orientation == 1) 0 else 1
        if (sRealSizes[orientation] == null) {
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            context.display?.getRealSize(point)
            sRealSizes[orientation] = point
        }
        return sRealSizes[orientation]?.y ?: getScreenRealHeight(context)
    }

    fun getMobileScreenWidth(context: Context) = context.resources?.displayMetrics?.widthPixels ?: 0

    @RequiresApi(Build.VERSION_CODES.R)
    fun getMobileScreenHeight(context: Context) =
        if (isAllScreenDevice(context)) {
            // 全面屏要通过这个方法获取高度
            getScreenRealHeight(context)
        }
        else { getScreenHeight(context); }
}
