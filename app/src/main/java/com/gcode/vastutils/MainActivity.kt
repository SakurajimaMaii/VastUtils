package com.gcode.vastutils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastadapter.BaseVastAdapter
import com.gcode.vastadapter.BaseVastBindingAdapter
import com.gcode.vasttools.utils.MsgWindowUtils
import com.gcode.vastutils.model.Person
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayout:View

    inner class TestBaseAdapter(items: MutableList<Person>) : BaseVastAdapter<Person>(items) {
        override fun bindData(holder: RecyclerViewHolder, position: Int, item: Person) {
            holder.findViewById<TextView>(R.id.firstName).text = item.firstName
            holder.findViewById<TextView>(R.id.lastName).text = item.lastName
        }
    }

    inner class TestBaseBindingAdapter(items: MutableList<Person>) :
        BaseVastBindingAdapter<Person>(items){
        override fun setVariableId(): Int {
            return com.gcode.vastutils.BR.item
        }
    }

    val items:MutableList<Person> = ArrayList<Person>().apply {
        var i = 1
        repeat(5){
            add(Person("张$i","王$i"))
            i++
        }
    }

    val testadapter = TestBaseAdapter(items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayout = LayoutInflater.from(this).inflate(R.layout.activity_main,null)

        val recyclerView1 = findViewById<RecyclerView>(R.id.RecyclerView1)

        val button = findViewById<Button>(R.id.addButton)


        testadapter.setOnItemClickListener(object : BaseVastAdapter.OnItemClickListener{
            override fun onItemClick(itemView: View?, pos: Int, itemId: Long) {
                Toast.makeText(this@MainActivity,"$pos and $itemId ^-^",Toast.LENGTH_SHORT).show()
            }
        })

        testadapter.setOnItemLongClickListener(object : BaseVastAdapter.OnItemLongClickListener{
            override fun onItemLongClick(itemView: View?, pos: Int, itemId: Long): Boolean {
                Toast.makeText(this@MainActivity,"^-^",Toast.LENGTH_SHORT).show()
                return true
            }
        })

        recyclerView1.adapter = testadapter
        recyclerView1.layoutManager = LinearLayoutManager(this)

        button.setOnClickListener {
            if(!testadapter.removeItemByObj(null)){
                MsgWindowUtils.showShortMsg(this,"VastUtils")
            }
        }
    }
}