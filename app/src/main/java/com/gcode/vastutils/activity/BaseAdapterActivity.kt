package com.gcode.vastutils.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcode.vastadapter.adapter.VastAdapterItem
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

    private val click = { _: View, _: VastAdapterItem, pos:Int, _:Long->
        MsgWindowUtils.showShortMsg(this@BaseAdapterActivity,"Hello,User.And position is $pos")
    }

    private val longClick = { _: View, _: VastAdapterItem, pos:Int, _:Long->
        MsgWindowUtils.showShortMsg(this@BaseAdapterActivity,"Hello,User.And position is $pos")
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
            datas.add(Picture(R.drawable.ic_knots,null,longClick,null,null))
        }
    }
}