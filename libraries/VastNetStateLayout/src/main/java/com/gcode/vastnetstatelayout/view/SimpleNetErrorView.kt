package com.gcode.vastnetstatelayout.view

import android.content.Context
import android.view.View
import com.gcode.vastnetstatelayout.R
import com.gcode.vastnetstatelayout.interfaces.VastNetErrorView
import com.gcode.vastnetstatelayout.interfaces.VastRetryClickListener

/**
 * Created by Vast Gui on 2021/11/5
 */
internal class SimpleNetErrorView : VastNetErrorView {
    private var vastRetryClickListener: VastRetryClickListener? = null
    private var mView: View? = null

    override fun setRetryClickListener(retryClickListener: VastRetryClickListener?) {
        vastRetryClickListener = retryClickListener
        mView?.findViewById<View>(R.id.btn_retry)?.setOnClickListener {
            vastRetryClickListener?.onRetryClicked()
        }
    }

    override fun getView(context: Context?): View {
        if (mView == null) {
            mView = View.inflate(context, R.layout.common_net_error, null)
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