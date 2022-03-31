package com.gcode.vastutils.basebindadpexample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcode.vastadapter.base.VastBindAdapter
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

    private val click = object :VAapClickEventListener{
        override fun vAapClickEvent(view: View, pos: Int) {
            showShortMsg("Click event and pos is $pos.")
        }
    }

    private val longClick = object :VAdpLongClickEventListener{
        override fun vAdpLongClickEvent(view: View, pos: Int): Boolean {
            showShortMsg("Long click event and pos is $pos.")
            return true
        }
    }

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBindingAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()

        val adapter = BaseBindingAdapter(datas)

        adapter.setOnItemClickListener(object :VastBindAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                // Something you want to do
            }
        })
        adapter.setOnItemLongClickListener(object :VastBindAdapter.OnItemLongClickListener{
            override fun onItemLongClick(view: View, position: Int): Boolean {
                // Something you want to do
                return true
            }
        })

        binding.dataRv.adapter = adapter
        binding.dataRv.layoutManager = LinearLayoutManager(this)
    }

    private fun initData() {

        val click = object :VAapClickEventListener{
            override fun vAapClickEvent(view: View, pos: Int) {
                showShortMsg("Hello,User.And position is $pos")
            }
        }

        for(i in 0..10){
            datas.add(Person(i.toString(),i.toString(),click,null))
            datas.add(Picture(R.drawable.ic_knots,null,object :VAdpLongClickEventListener{
                override fun vAdpLongClickEvent(view: View, pos: Int): Boolean {
                    showShortMsg("Hello,User.And position is $pos")
                    return true
                }
            }))
        }
    }
}