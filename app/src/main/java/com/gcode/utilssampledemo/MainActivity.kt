package com.gcode.utilssampledemo

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcode.tools.adapter.BaseUtilAdapter
import com.gcode.tools.adapter.BaseUtilBindingAdapter
import com.gcode.tools.adapter.BaseUtilItem
import com.gcode.widget.ShapeButton

class MainActivity : AppCompatActivity() {

    private val tag:String = this.javaClass.simpleName

    private lateinit var linearLayout:View

    inner class TestBaseAdapter(items: MutableList<BaseUtilItem>) : BaseUtilAdapter(items) {
        override fun bindData(holder: RecyclerViewHolder, position: Int, item: BaseUtilItem) {
            holder.findViewById<TextView>(R.id.firstName).text = (item as Person).firstName
            holder.findViewById<TextView>(R.id.lastName).text = item.lastName
        }
    }

    inner class TestBaseBindingAdapter(items: MutableList<BaseUtilItem>) :BaseUtilBindingAdapter(items){
        override fun setVariableId(): Int {
            return BR.item
        }
    }

    val items:MutableList<BaseUtilItem> = ArrayList<BaseUtilItem>().apply {
        var i = 1
        repeat(30){
            add(Person("张$i","王$i"))
            i++
        }
    }

    val testadapter = TestBaseAdapter(items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayout = LayoutInflater.from(this).inflate(R.layout.activity_main,null)

        linearLayout.setBackgroundColor(resources.getColor(R.color.lightyellow,null))

        val recyclerView1 = findViewById<RecyclerView>(R.id.RecyclerView1)

        val recyclerView2 = findViewById<RecyclerView>(R.id.RecyclerView2)

        val button4 = findViewById<ShapeButton>(R.id.button4)

        recyclerView1.adapter = testadapter
        recyclerView1.layoutManager = LinearLayoutManager(this)

//        var j = 31
//        repeat(10){
//            testadapter.addItemByPos(Person("张$j","王$j"),-1500)
//            j++
//        }

        val adapter = TestBaseBindingAdapter(items)
        adapter.setOnItemClickListener(object :BaseUtilBindingAdapter.OnItemClickListener{
            override fun onItemClick(itemView: View?, pos: Int) {

            }

            override fun onItemClick(itemView: View?, pos: Int, itemId: Long) {
                Toast.makeText(this@MainActivity,"$pos and $itemId ^-^",Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView2.adapter = adapter
        recyclerView2.layoutManager = LinearLayoutManager(this)

        Log.d(tag,"${testadapter.itemCount}  ${Build.VERSION.SDK_INT}")
    }
}