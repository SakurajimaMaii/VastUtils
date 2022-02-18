package com.gcode.vastutils.basebindadpexample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcode.vastadapter.base.VAapClickEvent
import com.gcode.vastadapter.base.VAdpLongClickEvent
import com.gcode.vastadapter.interfaces.VastBindAdapterItem
import com.gcode.vasttools.utils.showShortMsg
import com.gcode.vastutils.R
import com.gcode.vastutils.databinding.ActivityBaseBindingAdapterBinding
import com.gcode.vastutils.basebindadpexample.model.Person
import com.gcode.vastutils.basebindadpexample.model.Picture

class BaseBindingAdapterActivity:AppCompatActivity() {

    private lateinit var binding:ActivityBaseBindingAdapterBinding

    private val datas:MutableList<VastBindAdapterItem> = ArrayList()

    private val click:VAapClickEvent = { _: View, pos:Int->
        showShortMsg("Hello,User.And position is $pos")
    }

    private val longClick:VAdpLongClickEvent = { _: View, pos:Int->
        showShortMsg("Hello,User.And position is $pos")
        true
    }

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBindingAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()

        val adapter = BaseBindingAdapter(datas)
        binding.dataRv.adapter = adapter
        binding.dataRv.layoutManager = LinearLayoutManager(this)
    }

    private fun initData() {
        for(i in 0..10){
            datas.add(Person(i.toString(),i.toString(),click,null))
            datas.add(Picture(R.drawable.ic_knots,null,longClick))
        }
    }
}