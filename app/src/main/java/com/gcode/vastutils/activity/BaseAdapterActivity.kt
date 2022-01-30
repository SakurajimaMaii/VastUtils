package com.gcode.vastutils.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcode.vastadapter.base.VAapClickEvent
import com.gcode.vastadapter.base.VAdpLongClickEvent
import com.gcode.vastadapter.interfaces.VastAdapterItem
import com.gcode.vasttools.utils.MsgWindowUtils
import com.gcode.vastutils.R
import com.gcode.vastutils.adapter.*
import com.gcode.vastutils.databinding.ActivityBaseAdapterBinding
import com.gcode.vastutils.model.Person
import com.gcode.vastutils.model.Picture

class BaseAdapterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityBaseAdapterBinding

    private lateinit var adapter: BaseAdapter

    private val datas:MutableList<VastAdapterItem> = ArrayList()

    private val click:VAapClickEvent = { _: View, pos:Int,->
        val item = adapter.getItemByPos(pos)
        MsgWindowUtils.showShortMsg(this@BaseAdapterActivity,"Click event and pos is $pos,item inf ${(item as Person).firstName}")
    }

    private val longClick:VAdpLongClickEvent = { _: View, pos:Int,->
        MsgWindowUtils.showShortMsg(this@BaseAdapterActivity,"Long click event and pos is $pos")
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base_adapter)

        initData()

        adapter = BaseAdapter(datas, mutableListOf(AViewHolder.Factory(), BViewHolder.Factory()))

        binding.dataList.adapter = adapter
        binding.dataList.layoutManager = LinearLayoutManager(this)
    }

    private fun initData() {
        for(i in 0..10){
            datas.add(Person(i.toString(),i.toString(),click,null,null,null))
            datas.add(Picture(resources.getDrawable(R.drawable.ic_knots),null,longClick,null,null))
        }
    }
}