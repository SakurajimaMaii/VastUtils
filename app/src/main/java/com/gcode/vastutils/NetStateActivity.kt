package com.gcode.vastutils

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.gcode.vastnetstatelayout.interfaces.VastRetryClickListener
import com.gcode.vastnetstatelayout.view.VastNetState
import com.gcode.vastnetstatelayout.view.VastNetStateLayout
import com.gcode.vasttools.utils.MsgWindowUtils


class NetStateActivity : AppCompatActivity() {

    private var mNetStateLayout: VastNetStateLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_state)

        findViewById<View>(R.id.test).setOnClickListener {
            MsgWindowUtils.showShortMsg(
                this@NetStateActivity,
                "Hello"
            )
        }

        mNetStateLayout = findViewById<View>(R.id.net_state_layout) as VastNetStateLayout
        mNetStateLayout!!.contentState = VastNetState.CONTENT_STATE_SHOW_LOADING
        mNetStateLayout!!.setOnRetryClickListener(object : VastRetryClickListener {
            override fun onRetryClicked() {
                mNetStateLayout!!.contentState = VastNetState.CONTENT_STATE_HIDE
            }
        })

        findViewById<LinearLayout>(R.id.linear).background

        mHandler.sendEmptyMessageDelayed(0, 3000)
    }

    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            mNetStateLayout!!.contentState = VastNetState.CONTENT_STATE_SHOW_NET_ERROR
        }
    }
}