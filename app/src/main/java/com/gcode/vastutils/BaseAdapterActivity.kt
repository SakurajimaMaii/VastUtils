package com.gcode.vastutils

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcode.vastadapter.baseadapter.BaseVastAdapter
import com.gcode.vastadapter.interfaces.VastAdapterItem
import com.gcode.vasttools.utils.MsgWindowUtils
import com.gcode.vastutils.adapter.AViewHolder
import com.gcode.vastutils.adapter.BViewHolder
import com.gcode.vastutils.adapter.BaseAdapter
import com.gcode.vastutils.databinding.ActivityBaseAdapterBinding
import com.gcode.vastutils.model.Person
import com.gcode.vastutils.model.Picture

class BaseAdapterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityBaseAdapterBinding

    private lateinit var adapter: BaseAdapter

    private val datas:MutableList<VastAdapterItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_base_adapter)

        initData()

        adapter = BaseAdapter(datas, mutableListOf(AViewHolder.Factory(), BViewHolder.Factory()))
        adapter.setOnItemClickListener(object :BaseVastAdapter.OnItemClickListener{
            override fun onItemClick(itemView: View?, pos: Int, itemId: Long) {

            }
        })

        binding.dataList.adapter = adapter
        binding.dataList.layoutManager = LinearLayoutManager(this)
    }

    private fun initData() {
        for(i in 0..10){
            datas.add(Person(i.toString(),i.toString()))
            datas.add(Picture(R.drawable.ic_knots))
        }
    }
}