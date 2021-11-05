package com.gcode.vastnetstatelayout.view

import android.content.Context
import android.view.View
import com.gcode.vastnetstatelayout.R
import com.gcode.vastnetstatelayout.interfaces.INetErrorView
import com.gcode.vastnetstatelayout.interfaces.INetErrorView.OnRetryClickListener

/**
 * Created by Vast Gui on 2021/11/5
 */
class SimpleNetErrorView : INetErrorView {
    private var mRetryClickListener: OnRetryClickListener? = null
    private var mView: View? = null

    override fun setRetryClickListener(retryClickListener: OnRetryClickListener?) {
        mRetryClickListener = retryClickListener
        mView?.findViewById<View>(R.id.btn_retry)?.setOnClickListener {
            mRetryClickListener?.onRetryClicked()
        }
    }

    override fun getView(context: Context?): View {
        if (mView == null) {
            mView = View.inflate(context, R.layout.common_net_error, null)
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