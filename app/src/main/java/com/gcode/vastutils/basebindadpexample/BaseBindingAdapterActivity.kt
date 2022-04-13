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

package com.gcode.vastutils.basebindadpexample

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcode.vastadapter.base.VastBindAdapter
import com.gcode.vastadapter.interfaces.VAapClickEventListener
import com.gcode.vastadapter.interfaces.VAdpLongClickEventListener
import com.gcode.vastadapter.interfaces.VastBindAdapterItem
import com.gcode.vasttools.base.VastVbActivity
import com.gcode.vasttools.utils.showShortMsg
import com.gcode.vastutils.R
import com.gcode.vastutils.basebindadpexample.model.Person
import com.gcode.vastutils.basebindadpexample.model.Picture
import com.gcode.vastutils.databinding.ActivityBaseBindingAdapterBinding

class BaseBindingAdapterActivity : VastVbActivity<ActivityBaseBindingAdapterBinding>() {

    private val datas: MutableList<VastBindAdapterItem> = ArrayList()

    override fun initView(savedInstanceState: Bundle?) {

        initData()

        val adapter = BaseBindingAdapter(datas, this)

        adapter.setOnItemClickListener(object : VastBindAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                // Something you want to do
            }
        })
        adapter.setOnItemLongClickListener(object : VastBindAdapter.OnItemLongClickListener {
            override fun onItemLongClick(view: View, position: Int): Boolean {
                // Something you want to do
                return true
            }
        })


        mBinding.dataRv.adapter = adapter
        mBinding.dataRv.layoutManager = LinearLayoutManager(this)
    }

    private fun initData() {

        val click = object : VAapClickEventListener {
            override fun vAapClickEvent(view: View, pos: Int) {
                showShortMsg("Click event and pos is $pos.")
            }
        }

        val longClick = object : VAdpLongClickEventListener {
            override fun vAdpLongClickEvent(view: View, pos: Int): Boolean {
                showShortMsg("Long click event and pos is $pos.")
                return true
            }
        }

        for (i in 0..10) {
            datas.add(Person(i.toString(), i.toString(), click, null))
            datas.add(Picture(R.drawable.ic_knots, null, longClick))
        }
    }
}