/*
 * MIT License
 *
 * Copyright (c) 2021 码上夏雨
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.gcode.vastutils.activity

import android.os.*
import com.gcode.vastnetstatelayout.interfaces.VastEmptyDataListener
import com.gcode.vastnetstatelayout.interfaces.VastLoadingErrorListener
import com.gcode.vastnetstatelayout.interfaces.VastLoadingListener
import com.gcode.vastnetstatelayout.interfaces.VastNetErrorListener
import com.gcode.vastnetstatelayout.view.VastNetStateMgr
import com.gcode.vasttools.base.VastVbActivity
import com.gcode.vastutils.R
import com.gcode.vastutils.databinding.ActivityNetStateBinding


class NetStateActivity : VastVbActivity<ActivityNetStateBinding>() {

    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            mBinding.netStateLayout.showSuccess()
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
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
}