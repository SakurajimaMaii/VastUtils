package com.gcode.vastnetstatelayout.interfaces

import android.content.Context
import android.view.View
import androidx.annotation.Nullable

/**
 * [VastNetErrorView] is a interface for network error.
 * You can implement it to customize the UI.
 * SimpleNetErrorView has implemented it <p></p>
 *
 * Created by Vast Gui on 2021/11/5.
 */
interface VastNetErrorView {
    fun getView(context: Context?): View

    fun viewHide()

    fun viewShow()
}