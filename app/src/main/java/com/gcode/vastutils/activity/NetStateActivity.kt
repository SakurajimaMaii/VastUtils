package com.gcode.vastutils.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.gcode.vastnetstatelayout.interfaces.VastEmptyDataListener
import com.gcode.vastnetstatelayout.interfaces.VastLoadingErrorListener
import com.gcode.vastnetstatelayout.interfaces.VastNetErrorListener
import com.gcode.vastnetstatelayout.interfaces.VastLoadingListener
import com.gcode.vastnetstatelayout.view.VastNetStateMgr
import com.gcode.vastutils.R
import com.gcode.vastutils.databinding.ActivityNetStateBinding


class NetStateActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityNetStateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNetStateBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val vastNetStateMgr = VastNetStateMgr(this)
        vastNetStateMgr.setNetErrorListener(object :VastNetErrorListener{
            override fun onNetWorkError() {
                // Something to do when network error
            }
        })
        vastNetStateMgr.setLoadingListener(object : VastLoadingListener {
            override fun onLoading() {
                object:Handler(Looper.getMainLooper()) {
                    override fun handleMessage(msg: Message) {
                        super.handleMessage(msg)
                        mBinding.netStateLayout.showSuccess()
                    }
                }.sendEmptyMessageDelayed(0, 1000)
            }
        })
        vastNetStateMgr.setLoadingErrorListener(object :VastLoadingErrorListener{
            override fun onLoadingError() {
                // Something to do when loading error
            }
        })
        vastNetStateMgr.setEmptyDataListener(object :VastEmptyDataListener{
            override fun onEmptyData() {
                // Something to do when empty data
            }
        })
        vastNetStateMgr.setNetErrorView(R.layout.error_page)
        mBinding.netStateLayout.setVastNetStateMgr(vastNetStateMgr)
        mBinding.netStateLayout.showNetError()

        //mHandler.sendEmptyMessageDelayed(0, 1000)
    }

    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            mBinding.netStateLayout.showSuccess()
        }
    }
}