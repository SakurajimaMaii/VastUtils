/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("AppUtils")

package com.gcode.vasttools.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.annotation.RequiresApi

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/10 15:27
// Description: Help you to get app information.
// Documentation: [AppUtils](https://sakurajimamaii.github.io/VastDocs/document/en/AppUtils.html)

/**
 * @return app name
 */
@Synchronized
fun Context.getAppName(): String? {
    try {
        val packageManager: PackageManager = this.packageManager
        val packageInfo: PackageInfo = packageManager.getPackageInfo(
            this.packageName, 0
        )
        val labelRes = packageInfo.applicationInfo.labelRes
        return this.resources.getString(labelRes)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

/**
 * @return The version name of the current application
 */
@Synchronized
fun Context.getVersionName(): String? {
    try {
        val packageManager = this.packageManager
        val packageInfo = packageManager.getPackageInfo(
            this.packageName, 0
        )
        return packageInfo.versionName
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

/**
 * @return The version code of the current application
 */
@Synchronized
fun Context.getVersionCode() =
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
        getVersionCodeApi28Above(this)
    }else{
        getVersionCodeApi28Down(this)
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
 * @return The icon of the application
 */
@Synchronized
fun Context.getAppBitmap(): Bitmap? {
    var packageManager: PackageManager? = null
    var applicationInfo: ApplicationInfo?
    try {
        packageManager = this.applicationContext
            .packageManager
        applicationInfo = packageManager.getApplicationInfo(
            this.packageName, 0
        )
    } catch (e: PackageManager.NameNotFoundException) {
        applicationInfo = null
    }
    val d =
        packageManager!!.getApplicationIcon(applicationInfo!!)
    val bd = d as BitmapDrawable
    return bd.bitmap
}


/**
 * @return true if the app is debuggable.false otherwise.
 */
@Synchronized
fun Context.getAppDebug(): Boolean {
    return try {
        val info = this.applicationInfo
        info.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    } catch (e: java.lang.Exception) {
        false
    }
}