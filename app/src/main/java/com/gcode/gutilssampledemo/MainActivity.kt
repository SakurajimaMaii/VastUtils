package com.gcode.gutilssampledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcode.gutils.adapter.BaseAdapter
import com.gcode.gutils.adapter.BaseBindingAdapter
import com.gcode.gutils.adapter.BaseItem

class MainActivity : AppCompatActivity() {

    inner class TestBaseAdapter(items: MutableList<BaseItem>) : BaseAdapter(items) {
        override fun bindData(holder: RecyclerViewHolder, position: Int, item: BaseItem) {
            holder.findViewById<TextView>(R.id.firstName).text = (item as Person).firstName
            holder.findViewById<TextView>(R.id.lastName).text = item.lastName
        }
    }

    inner class TestBaseBindingAdapter(items: MutableList<BaseItem>) :BaseBindingAdapter(items){
        override fun setVariableId(): Int {
            return BR.item
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView1 = findViewById<RecyclerView>(R.id.RecyclerView1)
        val recyclerView2 = findViewById<RecyclerView>(R.id.RecyclerView2)

        val items:MutableList<BaseItem> = ArrayList<BaseItem>().apply {
            var i = 1
            repeat(10){
                add(Person("张$i","王$i"))
                i++
            }
        }

        recyclerView1.adapter = TestBaseAdapter(items)
        recyclerView1.layoutManager = LinearLayoutManager(this)
        recyclerView2.adapter = TestBaseBindingAdapter(items)
        recyclerView2.layoutManager = LinearLayoutManager(this)
    }
}