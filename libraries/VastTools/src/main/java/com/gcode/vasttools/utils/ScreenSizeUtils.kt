@file:JvmName("ScreenSizeUtils")

package com.gcode.vasttools.utils

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import android.view.WindowMetrics
import androidx.annotation.RequiresApi


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
    return sRealSizes[orientation]?.y ?: 0
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
    return sRealSizes[orientation]?.y ?: 0
}

/**
 * Get mobile screen height.
 *
 *
 */
fun Context.getMobileScreenHeight(): Int {
    return when{
        (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) -> getScreenRealHeightApi31(this)
        (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) -> getScreenRealHeightApi30(this)
        else-> getScreenRealHeightApi30Down(this)
    }
}

/**
 * Get mobile screen width.
 *
 * @return The width of the screen, in **pixels**
 */
fun Context.getMobileScreenWidth() = this.resources?.displayMetrics?.widthPixels ?: 0