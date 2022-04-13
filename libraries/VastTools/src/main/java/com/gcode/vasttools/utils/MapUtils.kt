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

@file:JvmName("MapUtils")

package com.gcode.vasttools.utils

import android.content.Context
import android.location.LocationManager

// Author: Vast Gui 
// Email: guihy2019@gmail.com
// Date: 2022/4/13 17:41
// Description:
// Documentation:

fun Context.isOPen(): Boolean {
    val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    // Through GPS satellite positioning, the positioning level can be accurate to the street
    // (through 24 satellite positioning, the positioning is accurate and fast in outdoor and open places).
    val gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    // Position determined by WLAN or mobile network (3G/2G) (also called AGPS, Assisted GPS Positioning.
    // Mainly used for positioning indoors or in densely covered places (buildings or dense deep forest, etc.).
    val network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    if (gps && network) {
        return true
    }
    return false
}