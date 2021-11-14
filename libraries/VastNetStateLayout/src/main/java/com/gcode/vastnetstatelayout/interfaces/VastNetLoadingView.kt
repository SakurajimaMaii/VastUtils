package com.gcode.vastnetstatelayout.interfaces

import android.content.Context
import android.view.View

/**
 * [VastNetLoadingView] is a interface for  loading.
 * You can implement it to customize the UI.
 * SimpleNetLoadingView has implemented it <p></p>
 *
 * Created by Vast Gui on 2021/11/5
 */
interface VastNetLoadingView {
    fun getView(context:Context):View

    fun viewHide()

    fun viewShow()
}
