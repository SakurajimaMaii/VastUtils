package com.gcode.vastnetstatelayout.view

import android.content.Context
import android.view.View
import com.gcode.vastnetstatelayout.R
import com.gcode.vastnetstatelayout.interfaces.INetLoadingView

/**
 * Created by Vast Gui on 2021/11/5.
 */
class SimpleNetLoadingView : INetLoadingView {

    private var mView: View? = null

    override fun getView(context: Context): View {
        if (mView == null) {
            mView = View.inflate(context, R.layout.common_shade_loading, null)
        }
        return mView!!
    }

    override fun hide() {
        mView?.visibility = View.GONE
    }

    override fun show() {
        mView?.visibility = View.VISIBLE
    }
}