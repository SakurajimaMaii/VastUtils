package com.gcode.vastnetstatelayout.view

import android.content.Context
import android.view.View
import com.gcode.vastnetstatelayout.R
import com.gcode.vastnetstatelayout.interfaces.VastNetErrorView
import com.gcode.vastnetstatelayout.interfaces.VastRetry

/**
 * [SimpleNetErrorView] provides a basic Error page,
 * which will be displayed when you have not set
 * [VastNetStateLayout.mNetErrorView].
 *
 * This page contains a Button, you can use
 * [VastNetStateLayout.setOnRetryClickListener]
 * to set its click event.
 *
 * Created by Vast Gui on 2021/11/5
 */
internal class SimpleNetErrorView : VastNetErrorView,VastRetry {
    private var vastRetryClickListener: VastRetry.VastRetryClickListener? = null
    private var mView: View? = null

    override fun setRetryClickListener(retryClickListener: VastRetry.VastRetryClickListener?) {
        vastRetryClickListener = retryClickListener
        mView?.findViewById<View>(R.id.btn_retry)?.setOnClickListener {
            vastRetryClickListener?.onRetryClicked()
        }
    }

    override fun getView(context: Context?): View {
        if (mView == null) {
            mView = View.inflate(context, R.layout.simple_net_error_page, null)
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