package com.gcode.vastutils.activity;

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcode.vastadapter.adapter.VastBindingAdapterItem
import com.gcode.vasttools.utils.DensityUtils
import com.gcode.vasttools.utils.LogUtils
import com.gcode.vasttools.utils.MsgWindowUtils
import com.gcode.vastutils.R
import com.gcode.vastutils.adapter.BaseBindingAdapter
import com.gcode.vastutils.databinding.ActivityBaseBindingAdapterBinding
import com.gcode.vastutils.model.Person
import com.gcode.vastutils.model.Picture

class BaseBindingAdapterActivity:AppCompatActivity() {

    private lateinit var binding:ActivityBaseBindingAdapterBinding

    private val datas:MutableList<VastBindingAdapterItem> = ArrayList()

    private val click = { _: View, _: VastBindingAdapterItem, pos:Int, _:Long->
        MsgWindowUtils.showShortMsg(this,"Hello,User.And position is $pos")
    }

    private val longClick = { _: View, _: VastBindingAdapterItem, pos:Int, _:Long->
        MsgWindowUtils.showShortMsg(this,"Hello,User.And position is $pos")
        true
    }

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base_binding_adapter)

        binding.list.layoutManager = LinearLayoutManager(this)

        initData()

        val adapter = BaseBindingAdapter(datas)
        binding.list.adapter = adapter

        LogUtils.i(this.javaClass,this.javaClass.simpleName,"${adapter.itemCount}")
    }

    private fun initData() {
        for(i in 0..10){
            datas.add(Person(i.toString(),i.toString(),null,null,click,null))
            datas.add(Picture(R.drawable.ic_knots,null,null,null,longClick))
        }
    }
}