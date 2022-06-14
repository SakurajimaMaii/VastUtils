/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gcode.vastutils.swipeexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastadapter.interfaces.VastBindAdapterItem
import com.gcode.vastswiperecyclerview.VastSwipeRvMgr
import com.gcode.vastswiperecyclerview.annotation.ICON_TITLE
import com.gcode.vastswiperecyclerview.interfaces.VastSwipeItemClickListener
import com.gcode.vastswiperecyclerview.interfaces.VastSwipeItemLongClickListener
import com.gcode.vastswiperecyclerview.interfaces.VastSwipeMenuCreator
import com.gcode.vastswiperecyclerview.model.VastSwipeMenu
import com.gcode.vasttools.activity.VastVbActivity
import com.gcode.vasttools.utils.dp
import com.gcode.vasttools.utils.showShortMsg
import com.gcode.vastutils.R
import com.gcode.vastutils.databinding.ActivitySwipeBinding
import com.gcode.vastutils.swipeexample.model.Swipe

class SwipeActivity : VastVbActivity<ActivitySwipeBinding>(){

    private val tag:String = this.javaClass.simpleName

    private var lists: MutableList<VastBindAdapterItem> = ArrayList()

    private fun initData(): MutableList<VastBindAdapterItem> {
        val list: ArrayList<VastBindAdapterItem> = ArrayList()
        var i = 'A'.code
        while (i <= 'A'.code) {
            list.add(Swipe(i.toChar().toString(),null,null))
            i++
        }
        return list
    }

    override fun initView(savedInstanceState: Bundle?) {
        lists = initData()

        val manager = VastSwipeRvMgr(this).apply {
            setTitleSize(20f)
            setIconSize(40f.dp)
            setMenuWidth(100f.dp)
            setSwipeMenuContentStyle(ICON_TITLE)
            setSwipeItemClickListener(object: VastSwipeItemClickListener{
                override fun onClickEvent(view: View, adapterPosition: Int) {
                    showShortMsg("Hello")
                }
            })
            setSwipeItemLongClickListener(object: VastSwipeItemLongClickListener {
                override fun onLongClickEvent(view: View, adapterPosition: Int): Boolean {
                    showShortMsg("Long Hello")
                    return true
                }
            })
            setSwipeMenuCreator(object :VastSwipeMenuCreator{
                override fun onCreateMenu(
                    leftMenu: MutableList<VastSwipeMenu>,
                    rightMenu: MutableList<VastSwipeMenu>,
                    position: Int
                ) {
                    val delete = VastSwipeMenu(
                        this@SwipeActivity,"删除",ContextCompat.getDrawable(this@SwipeActivity,R.drawable.ic_delete))
                    leftMenu.add(delete)

                    val refresh = VastSwipeMenu(this@SwipeActivity,"刷新",ContextCompat.getDrawable(this@SwipeActivity,R.drawable.ic_refresh))
                    rightMenu.add(refresh)
                }
            })
        }

        val mAdapter = TAdapter(ArrayList<String>().apply {
            var i = 'A'.code
            while (i <= 'Z'.code) {
                this.add(i.toString())
                i++
            }
        })

        mBinding.swipeRv.apply {
            setManager(manager)
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@SwipeActivity)
        }
    }
}

class TAdapter(val data:ArrayList<String>): RecyclerView.Adapter<TAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        var tv:TextView = view.findViewById(R.id.tv_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }
}