package com.gcode.vastutils.basebindadpexample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcode.vastadapter.interfaces.VAapClickEventListener
import com.gcode.vastadapter.interfaces.VAdpLongClickEventListener
import com.gcode.vastadapter.interfaces.VastBindAdapterItem
import com.gcode.vasttools.utils.showShortMsg
import com.gcode.vastutils.R
import com.gcode.vastutils.basebindadpexample.model.Person
import com.gcode.vastutils.basebindadpexample.model.Picture
import com.gcode.vastutils.databinding.ActivityBaseBindingAdapterBinding

class BaseBindingAdapterActivity:AppCompatActivity() {

    private lateinit var binding:ActivityBaseBindingAdapterBinding

    private val datas:MutableList<VastBindAdapterItem> = ArrayList()

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
            datas.add(Person(i.toString(),i.toString(),object :VAapClickEventListener{
                override fun vAapClickEvent(view: View, pos: Int) {
                    showShortMsg("Hello,User.And position is $pos")
                }
            },null))
            datas.add(Picture(R.drawable.ic_knots,null,object :VAdpLongClickEventListener{
                override fun vAdpLongClickEvent(view: View, pos: Int): Boolean {
                    showShortMsg("Hello,User.And position is $pos")
                    return true
                }
            }))
        }
    }
}