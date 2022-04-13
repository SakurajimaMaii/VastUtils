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

package com.gcode.vastutils.activity.baseadpexample

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcode.vastadapter.base.VastAdapter
import com.gcode.vastadapter.interfaces.VAapClickEventListener
import com.gcode.vastadapter.interfaces.VAdpLongClickEventListener
import com.gcode.vastadapter.interfaces.VastAdapterItem
import com.gcode.vasttools.base.VastVbActivity
import com.gcode.vasttools.utils.showShortMsg
import com.gcode.vastutils.R
import com.gcode.vastutils.activity.baseadpexample.model.AExample
import com.gcode.vastutils.activity.baseadpexample.model.BExample
import com.gcode.vastutils.activity.baseadpexample.viewholder.AViewHolder
import com.gcode.vastutils.activity.baseadpexample.viewholder.BViewHolder
import com.gcode.vastutils.databinding.ActivityBaseAdapterBinding

class BaseAdapterActivity : VastVbActivity<ActivityBaseAdapterBinding>() {

    private lateinit var adapter: BaseAdapter

    private val datas: MutableList<VastAdapterItem> = ArrayList()

    private val click = object : VAapClickEventListener {
        override fun vAapClickEvent(view: View, pos: Int) {
            showShortMsg("Click event and pos is $pos.")
        }
    }

    private val longClick = object : VAdpLongClickEventListener {
        override fun vAdpLongClickEvent(view: View, pos: Int): Boolean {
            showShortMsg("Long click event and pos is $pos.")
            return true
        }
    }

    override fun initView(savedInstanceState: Bundle?) {

        initData()

        adapter = BaseAdapter(datas, mutableListOf(AViewHolder.Factory(), BViewHolder.Factory()))

        adapter.setOnItemClickListener(object : VastAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                // Something you want to do
            }
        })
        adapter.setOnItemLongClickListener(object : VastAdapter.OnItemLongClickListener {
            override fun onItemLongClick(view: View, position: Int): Boolean {
                // Something you want to do
                return true
            }
        })

        mBinding.dataList.adapter = adapter
        mBinding.dataList.layoutManager = LinearLayoutManager(this)
    }

    private fun initData() {
        for (i in 0..10) {
            datas.add(AExample(i.toString(), click, null))
            datas.add(BExample(R.drawable.ic_knots, null, longClick))
        }
    }
}