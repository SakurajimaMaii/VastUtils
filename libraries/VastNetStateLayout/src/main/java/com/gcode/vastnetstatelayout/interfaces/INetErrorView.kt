package com.gcode.vastnetstatelayout.interfaces

import android.content.Context
import android.view.View
import androidx.annotation.Nullable

/**
 * INetErrorView is a interface for network error.
 * You can implement it to customize the UI.
 * SimpleNetErrorView has implemented it <p></p>
 *
 * Created by Vast Gui on 2021/11/5.
 */
interface INetErrorView {
    fun setRetryClickListener(@Nullable retryClickListener: OnRetryClickListener?)

    fun getView(context: Context?): View

    fun hide()

    fun show()

    interface OnRetryClickListener {
        fun onRetryClicked()
    }
}