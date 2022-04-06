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

class BaseBindingAdapterActivity: VastVbActivity<ActivityBaseBindingAdapterBinding>() {

    private val datas:MutableList<VastBindAdapterItem> = ArrayList()

    override fun initView(savedInstanceState:Bundle?) {

        initData()

        val adapter = BaseBindingAdapter(datas)

        adapter.setOnItemClickListener(null)
        object :VastBindAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                // Something you want to do
                showShortMsg("Something you want to do")
            }
        }
        adapter.setOnItemLongClickListener(null)
        object :VastBindAdapter.OnItemLongClickListener{
            override fun onItemLongClick(view: View, position: Int): Boolean {
                // Something you want to do
                return true
            }
        }

        mBinding.dataRv.adapter = adapter
        mBinding.dataRv.layoutManager = LinearLayoutManager(this)
    }

    private fun initData() {

        val click = object :VAapClickEventListener{
            override fun vAapClickEvent(view: View, pos: Int) {
                showShortMsg("Click event and pos is $pos.")
            }
        }

        val longClick = object :VAdpLongClickEventListener{
            override fun vAdpLongClickEvent(view: View, pos: Int): Boolean {
                showShortMsg("Long click event and pos is $pos.")
                return true
            }
        }

        for(i in 0..10){
            datas.add(Person(i.toString(),i.toString(),click,null))
            datas.add(Picture(R.drawable.ic_knots,null,null))
        }
    }
}