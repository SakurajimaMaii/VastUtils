package com.gcode.vastnetstatelayout.view

import android.content.Context
import android.view.View
import com.gcode.vastnetstatelayout.R
import com.gcode.vastnetstatelayout.interfaces.VastNetLoadingView

/**
 * [SimpleNetLoadingView] provides a basic loading
 * page,which will be displayed when you have not
 * set [VastNetStateLayout.mNetLoadingView].
 *
 * Created by Vast Gui on 2021/11/5.
 */
internal class SimpleNetLoadingView : VastNetLoadingView {

    private var mView: View? = null

    override fun getView(context: Context): View {
        if (mView == null) {
            mView = View.inflate(context, R.layout.simple_loading_view, null)
        }
        return mView!!
    }

    override fun viewHide() {
        mView?.visibility = View.GONE
    }

    override fun viewShow() {
        mView?.visibility = View.VISIBLE
    }
}