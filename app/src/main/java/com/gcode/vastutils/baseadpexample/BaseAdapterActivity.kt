package com.gcode.vastutils.baseadpexample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcode.vastadapter.base.VastAdapter
import com.gcode.vastadapter.interfaces.VAapClickEventListener
import com.gcode.vastadapter.interfaces.VAdpLongClickEventListener
import com.gcode.vastadapter.interfaces.VastAdapterItem
import com.gcode.vasttools.utils.showShortMsg
import com.gcode.vastutils.R
import com.gcode.vastutils.baseadpexample.model.AExample
import com.gcode.vastutils.baseadpexample.model.BExample
import com.gcode.vastutils.baseadpexample.viewholder.AViewHolder
import com.gcode.vastutils.baseadpexample.viewholder.BViewHolder
import com.gcode.vastutils.databinding.ActivityBaseAdapterBinding

class BaseAdapterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityBaseAdapterBinding

    private lateinit var adapter: BaseAdapter

    private val datas:MutableList<VastAdapterItem> = ArrayList()

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base_adapter)

        initData()

        adapter = BaseAdapter(datas, mutableListOf(AViewHolder.Factory(), BViewHolder.Factory()))

        adapter.setOnItemClickListener(object :VastAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                // Something you want to do
            }
        })
        adapter.setOnItemLongClickListener(object:VastAdapter.OnItemLongClickListener{
            override fun onItemLongClick(view: View, position: Int): Boolean {
                // Something you want to do
                return true
            }
        })

        binding.dataList.adapter = adapter
        binding.dataList.layoutManager = LinearLayoutManager(this)
    }

    private fun initData() {
        for(i in 0..10){
            datas.add(AExample(i.toString(),click,null))
            datas.add(BExample(R.drawable.ic_knots,null,longClick))
        }
    }
}