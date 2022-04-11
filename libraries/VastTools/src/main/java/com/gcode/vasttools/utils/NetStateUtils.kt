/*
 * MIT License
 *
 * Copyright (c) 2021 码上夏雨
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
@file:JvmName("NetStateUtils")

package com.gcode.vasttools.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.telephony.PhoneStateListener
import android.telephony.SignalStrength
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/4/2 9:03
// Description: With NetStateUtils, you can easily check some network status about your device
// Documentation: [NetStateUtils](https://sakurajimamaii.github.io/VastDocs/document/en/NetStateUtils.html)

@Throws(RuntimeException::class)
internal fun Context.getNetWorkInfo(): NetworkInfo? {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.activeNetworkInfo
    } else {
        throw RuntimeException("NetworkInfo was deprecated in API level 29.")
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Throws(RuntimeException::class)
internal fun Context.getNetworkCapabilities(): NetworkCapabilities? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = cm.activeNetwork
        cm.getNetworkCapabilities(nw)
    } else {
        throw RuntimeException("App api version should be greater than 29.")
    }
}

/**
 * Is network available
 *
 * @return True if network is available,false otherwise.
 */
fun Context.isNetworkAvailable(): Boolean {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        val networkInfo = getNetWorkInfo()
        if ((null != networkInfo) and (networkInfo!!.isConnected)) {
            networkInfo.isAvailable
        } else false
    } else {
        val networkCapabilities = getNetworkCapabilities()
        if (null != networkCapabilities) {
            when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else false
    }
}

/**
 * Is WIFI
 *
 * @return True if network is wifi mode,false otherwise.
 */
fun Context.isWIFI(): Boolean {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        val networkInfo = getNetWorkInfo()
        if (null != networkInfo) {
            networkInfo.type == ConnectivityManager.TYPE_WIFI
        } else false
    } else {
        val networkCapabilities = getNetworkCapabilities()
        networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false
    }
}

/**
 * Is mobile net
 *
 * @return True if network is wifi mode,false otherwise.
 */
fun Context.isMobile(): Boolean {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        val networkInfo = getNetWorkInfo()
        if (null != networkInfo) {
            networkInfo.type == ConnectivityManager.TYPE_MOBILE
        } else false
    } else {
        val networkCapabilities = getNetworkCapabilities()
        networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false
    }
}

/**
 * Get wifi signal strength.
 *
 * @return Return -1 when wifi is disconnected or unable,when wifi is connected,
 *         the signal strength is represented by 0-4.
 */
fun Context.getWifiDBM(): Int {
    if (!isWIFI()) return -1
    if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q){
        val wifiManager =
            applicationContext.getSystemService(AppCompatActivity.WIFI_SERVICE) as WifiManager
        val info = wifiManager.connectionInfo
        if (info.bssid != null) {
            // Signal strength, 5 means the acquired signal strength value is within 5
            return WifiManager.calculateSignalLevel(info.rssi, 5)
        }
    }else{
        val info = getNetworkCapabilities()
        val wm = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if(null != info){
            return wm.calculateSignalLevel(info.signalStrength)
        }
    }
    return -1
}