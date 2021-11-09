package com.gcode.vasttools.utils

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import android.view.WindowMetrics
import androidx.annotation.RequiresApi
import com.gcode.vasttools.internal.annotation.UnderTest
import com.gcode.vasttools.model.AspectRatioDevice

/**
 * Screen size utils
 *
 * Get your device screen size
 */
object ScreenSizeUtils {

    @UnderTest
    private val DefaultDevice = AspectRatioDevice("DefaultDevice", 1.97f)

    /**
     * Aspect ratio map
     *
     * In order to fit the aspect ratio of more mobile devices
     */
    @UnderTest
    private var AspectRatioDeviceList = mutableListOf<AspectRatioDevice>()

    /**
     * Determine whether it is a full screen(in Api 31).
     *
     * Get the aspectRatio from the [AspectRatioDeviceList] by
     * the value of [SystemUtils.deviceBrand], if not found,
     * use **1.97** as the reference value.
     *
     * @param context Context for the transform.
     *
     * @return `true` if your device is full screen,`false` if your device is not full screen.
     */
    @RequiresApi(Build.VERSION_CODES.S)
    fun isAllScreenDeviceApi31(
        context: Context
    ): Boolean {
        val vm: WindowMetrics =
            (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).currentWindowMetrics
        val width: Float = vm.bounds.width().toFloat()
        val height: Float = vm.bounds.height().toFloat()
        val device = AspectRatioDeviceList.firstOrNull { it.tag == SystemUtils.deviceBrand }
        val aspectRatio = device?.aspectRatio ?: DefaultDevice.aspectRatio
        if (height / width >= aspectRatio) {
            return true
        }
        return false
    }

    /**
     * Determine whether it is a full screen(in Api 30).
     *
     * Get the aspectRatio from the [AspectRatioDeviceList] by
     * the value of [SystemUtils.deviceBrand], if not found,
     * use **1.97** as the reference value.
     *
     * @param context Context for the transform.
     *
     * @return `true` if your device is full screen,`false` if your device is not full screen.
     */
    @RequiresApi(Build.VERSION_CODES.R)
    fun isAllScreenDeviceApi30(
        context: Context
    ): Boolean {
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
        val device = AspectRatioDeviceList.firstOrNull { it.tag == SystemUtils.deviceBrand }
        val aspectRatio = device?.aspectRatio ?: DefaultDevice.aspectRatio
        if (height / width >= aspectRatio) {
            return true
        }
        return false
    }

    /**
     * Determine whether it is a full screen(in Api 30 Down).
     *
     * Get the aspectRatio from the [AspectRatioDeviceList] by
     * the value of [SystemUtils.deviceBrand], if not found,
     * use **1.97** as the reference value.
     *
     * @param context Context for the transform.
     *
     * @return `true` if your device is full screen,`false` if your device is not full screen.
     */
    fun isAllScreenDeviceApi30Down(
        context: Context
    ): Boolean {
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
        val device = AspectRatioDeviceList.firstOrNull { it.tag == SystemUtils.deviceBrand }
        val aspectRatio = device?.aspectRatio ?: DefaultDevice.aspectRatio
        if (height / width >= aspectRatio) {
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
            // The full screen is needs to get the height through this method.
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
            // The full screen is needs to get the height through this method.
            getScreenRealHeightApi30(context)
        } else {
            getScreenHeight(context); }

    /**
     * Get mobile screen height Api 30 Down
     * @param context
     */
    fun getMobileScreenHeightApi30Down(context: Context) =
        if (isAllScreenDeviceApi30Down(context)) {
            // The full screen needs to get the height through this method.
            getScreenRealHeightApi30Down(context)
        } else {
            getScreenHeight(context)
        }

    /**
     * @param devices Add the aspect ratio of the device and its corresponding screen.
     */
    @UnderTest
    fun addDevice(vararg devices: AspectRatioDevice) {
        devices.forEach {
            AspectRatioDeviceList.add(it)
        }
    }

    /**
     * Remove device by [device]
     */
    @UnderTest
    fun removeDevice(device: AspectRatioDevice) {
        AspectRatioDeviceList.remove(device)
    }

    /**
     * Reset [AspectRatioDeviceList]
     */
    @UnderTest
    fun resetDeviceList() {
        AspectRatioDeviceList.clear()
    }
}