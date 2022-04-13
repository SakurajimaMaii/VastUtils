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

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcode.vastadapter.base.VastBindAdapter
import com.gcode.vastadapter.interfaces.VAapClickEventListener
import com.gcode.vastadapter.interfaces.VastBindAdapterItem
import com.gcode.vasttools.base.VastVbActivity
import com.gcode.vastutils.BR
import com.gcode.vastutils.R
import com.gcode.vastutils.activity.baseadpexample.BaseAdapterActivity
import com.gcode.vastutils.activity.basebindadpexample.BaseBindingAdapterActivity
import com.gcode.vastutils.databinding.ActivityMainBinding
import com.gcode.vastutils.model.IntentSelect

class MainActivity : VastVbActivity<ActivityMainBinding>() {

    // 列表rv适配器
    inner class Adapter(
        data:MutableList<VastBindAdapterItem>,
        context: Context
    ): VastBindAdapter(data,context) {
        override fun setVariableId(): Int {
            return BR.intentSelect
        }
    }

    // 列表数据源
    private val data:MutableList<VastBindAdapterItem> = ArrayList()

    private val context = this

    override fun initView(savedInstanceState: Bundle?) {
        initData()

        mBinding.rv.apply{
            adapter = Adapter(data,context)
            layoutManager = LinearLayoutManager(context)
        }

    }

    private fun initData(){
        data.apply {
            add(IntentSelect(
                context.resources.getString(R.string.loading_page),
                object: VAapClickEventListener{
                    override fun vAapClickEvent(view: View, pos: Int) {
                        context.startActivity(Intent(context, NetStateActivity::class.java))
                    }
                }
            ))

            add(IntentSelect(
                context.resources.getString(R.string.base_adapter),
                object: VAapClickEventListener{
                    override fun vAapClickEvent(view: View, pos: Int) {
                        context.startActivity(Intent(context, BaseAdapterActivity::class.java))
                    }
                }
            ))

            add(IntentSelect(
                context.resources.getString(R.string.base_bind_adapter),
                object: VAapClickEventListener{
                    override fun vAapClickEvent(view: View, pos: Int) {
                        context.startActivity(Intent(context, BaseBindingAdapterActivity::class.java))
                    }
                }
            ))

            add(IntentSelect(
                context.resources.getString(R.string.base_intent),
                object: VAapClickEventListener{
                    override fun vAapClickEvent(view: View, pos: Int) {
                        context.startActivity(Intent(context, IntentActivity::class.java))
                    }
                }
            ))

            add(IntentSelect(
                context.resources.getString(R.string.shape),
                object: VAapClickEventListener{
                    override fun vAapClickEvent(view: View, pos: Int) {
                        context.startActivity(Intent(context, ShapeActivity::class.java))
                    }
                }
            ))

            add(IntentSelect(
                context.resources.getString(R.string.base_fragment_activity),
                object: VAapClickEventListener{
                    override fun vAapClickEvent(view: View, pos: Int) {
                        context.startActivity(Intent(context, BaseFragmentActivity::class.java))
                    }
                }
            ))

            add(IntentSelect(
                context.resources.getString(R.string.theme),
                object: VAapClickEventListener{
                    override fun vAapClickEvent(view: View, pos: Int) {
                        context.startActivity(Intent(context, ThemeActivity::class.java))
                    }
                }
            ))
        }
    }

}