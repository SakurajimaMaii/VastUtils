package com.gcode.tools.utils

import android.view.View
import android.view.ViewTreeObserver

/**
 * View size utils
 *
 * Android gets the width and height of the View,
 * and solves the problem of getting the height and width to 0
 *
 * @constructor Create empty View size utils
 */
internal object ViewSizeUtils {
    fun getViewHeight(view: View): Int{
        val w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(w, h)
        return view.measuredHeight
    }

    fun getViewWidth(view: View): Int{
        val w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(w, h)
        return view.measuredWidth
    }
}