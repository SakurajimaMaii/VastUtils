package com.gcode.utilssampledemo

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcode.tools.adapter.BaseGcodeAdapter
import com.gcode.tools.adapter.BaseGcodeBindingAdapter
import com.gcode.tools.utils.*

class MainActivity : AppCompatActivity() {

    private val tag:String = "Hello"

    private lateinit var linearLayout:View

    inner class TestBaseAdapter(items: MutableList<Person>) : BaseGcodeAdapter<Person>(items) {
        override fun bindData(holder: RecyclerViewHolder, position: Int, item: Person) {
            holder.findViewById<TextView>(R.id.firstName).text = item.firstName
            holder.findViewById<TextView>(R.id.lastName).text = item.lastName
        }
    }

    inner class TestBaseBindingAdapter(items: MutableList<Person>) :BaseGcodeBindingAdapter<Person>(items){
        override fun setVariableId(): Int {
            return BR.item
        }
    }

    val items:MutableList<Person> = ArrayList<Person>().apply {
        var i = 1
        repeat(5){
            add(Person("张$i","王$i"))
            i++
        }
    }

    val person:MutableList<Person> = ArrayList<Person>().apply {
        var i = 5
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

        linearLayout.setBackgroundColor(resources.getColor(R.color.util_light_yellow,null))

        val recyclerView1 = findViewById<RecyclerView>(R.id.RecyclerView1)

        val button = findViewById<Button>(R.id.addButton)

        testadapter.setOnItemClickListener(object :BaseGcodeAdapter.OnItemClickListener{
            override fun onItemClick(itemView: View?, pos: Int, itemId: Long) {
                Toast.makeText(this@MainActivity,"$pos and $itemId ^-^",Toast.LENGTH_SHORT).show()
            }
        })

        testadapter.setOnItemLongClickListener(object : BaseGcodeAdapter.OnItemLongClickListener{
            override fun onItemLongClick(itemView: View?, pos: Int, itemId: Long): Boolean {
                Toast.makeText(this@MainActivity,"^-^",Toast.LENGTH_SHORT).show()
                return true
            }
        })

        recyclerView1.adapter = testadapter
        recyclerView1.layoutManager = LinearLayoutManager(this)

        button.setOnClickListener {
            if(!testadapter.removeItemByObj(null)){
                Toast.makeText(this@MainActivity,"HHHHHHHHHHHHHHHHHHHHHHHHHH",Toast.LENGTH_SHORT).show()
            }
        }

        LogUtils.setLogEnabled(false)
        LogUtils.i(this.javaClass,"Hello","${1+2}")
        LogUtils.d(this.javaClass,"Hello","${1+2}")
        LogUtils.e(this.javaClass,"Hello","${1+2}")
        LogUtils.v(this.javaClass,"Hello","${1+2}")
        LogUtils.w(this.javaClass,"Hello","${1+2}")
    }
}