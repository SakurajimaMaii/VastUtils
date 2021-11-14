package com.gcode.vasttools.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * App info utils
 * Get information about App
 * @constructor Create empty App utils
 */
object AppInfoUtils {
    @Synchronized
    fun getAppName(context: Context): String? {
        try {
            val packageManager: PackageManager = context.packageManager
            val packageInfo: PackageInfo = packageManager.getPackageInfo(
                context.packageName, 0
            )
            val labelRes = packageInfo.applicationInfo.labelRes
            return context.resources.getString(labelRes)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * @param context
     * @return The version name of the current application
     */
    @Synchronized
    fun getVersionName(context: Context): String? {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                context.packageName, 0
            )
            return packageInfo.versionName
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * Get VersionCode
     * @return The version code of the current application
     */
    @Synchronized
    fun getVersionCode(context: Context) =
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            getVersionCodeApi28Above(context)
        }else{
            getVersionCodeApi28Down(context)
        }


    /**
     * Get VersionCode (in Api 28 Above)
     * @return The version code of the current application
     */
    @Synchronized
    @RequiresApi(Build.VERSION_CODES.P)
    internal fun getVersionCodeApi28Above(context: Context): Int {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                context.packageName, 0
            )
            return packageInfo.longVersionCode.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * Get VersionCode (in Api 28 Down)
     * @return The version code of the current application
     */
    internal fun getVersionCodeApi28Down(context: Context): Int{
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                context.packageName, 0
            )
            return packageInfo.versionCode
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * @param context
     * @return The package name of the application
     */
    @Synchronized
    fun getPackageName(context: Context): String? {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                context.packageName, 0
            )
            return packageInfo.packageName
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * @param context
     * @return The icon of the application
     */
    @Synchronized
    fun getAppBitmap(context: Context): Bitmap? {
        var packageManager: PackageManager? = null
        var applicationInfo: ApplicationInfo?
        try {
            packageManager = context.applicationContext
                .packageManager
            applicationInfo = packageManager.getApplicationInfo(
                context.packageName, 0
            )
        } catch (e: PackageManager.NameNotFoundException) {
            applicationInfo = null
        }
        val d =
            packageManager!!.getApplicationIcon(applicationInfo!!)
        val bd = d as BitmapDrawable
        return bd.bitmap
    }

    @Synchronized
    fun getAppDebug(context: Context): Boolean {
        return try {
            val info = context.applicationInfo
            info.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
        } catch (e: java.lang.Exception) {
            false
        }
    }
}