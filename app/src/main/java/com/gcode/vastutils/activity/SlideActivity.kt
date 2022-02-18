package com.gcode.vastutils.activity

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcode.vastswipeview.VastSwipeViewMgr
import com.gcode.vastswipeview.adapter.VastSwipeViewAdapter
import com.gcode.vastswipeview.annotation.VastSwipeViewConstant.*
import com.gcode.vastswipeview.interfaces.VastSwipeContentItem
import com.gcode.vastswipeview.view.VastSwipeView
import com.gcode.vastutils.R
import com.gcode.vastutils.basebindadpexample.model.Person

class SlideActivity : AppCompatActivity() {

    private lateinit var vastSwipeView: VastSwipeView

    private var inflater: LayoutInflater? = null
    private var context: Context? = null
    private var lists: MutableList<Person> = ArrayList()

    private lateinit var vastSwipeMenuMgr: VastSwipeViewMgr

    private val tag = "vasttest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)

        lists = initData()

        vastSwipeMenuMgr = VastSwipeViewMgr(this)
        vastSwipeMenuMgr.setMenuStyle(ONLY_RIGHT)

        vastSwipeMenuMgr.setEventListener(object : VastSwipeViewMgr.EventListener {
            override fun eventDownListener(position: Int, event: MotionEvent) {
                //Log.d(tag,"${vastSwipeListView.adapter?.itemCount}")
            }

            override fun eventMoveListener(position: Int, event: MotionEvent) {
                //Log.d(tag,"eventMoveListener")
            }

            override fun eventUpListener(position: Int, event: MotionEvent) {
                //Log.d(tag,"eventUpListener")
            }

            override fun eventCancelListener(position: Int, event: MotionEvent) {
                //Log.d(tag,"eventCancelListener")
            }
        })
        vastSwipeMenuMgr.setMenuOpenDuration(200)
        vastSwipeMenuMgr.setLeftMenuOpenDistanceThreshold(50)
        vastSwipeMenuMgr.setRightMenuOpenDistanceThreshold(50)

        inflater = LayoutInflater.from(this)
        context = applicationContext
        vastSwipeView = findViewById(R.id.listview)
        vastSwipeView.setManager(vastSwipeMenuMgr)
        vastSwipeView.layoutManager = LinearLayoutManager(this)
        vastSwipeView.adapter = Adapter(lists,vastSwipeMenuMgr)
    }

    private fun initData(): MutableList<Person> {
        val list: ArrayList<Person> = ArrayList()
        var i = 'A'.code
        while (i <= 'Z'.code) {
            list.add(Person(i.toChar().toString(), i.toChar().toString(),null,null))
            i++
        }
        return list
    }

    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            resources.displayMetrics
        ).toInt()
    }

    class Adapter(data:MutableList<Person>,manager:VastSwipeViewMgr):VastSwipeViewAdapter(data,manager){
        override fun bindData(
            holder: AdapterViewHolder,
            position: Int,
            item: VastSwipeContentItem
        ) {
            holder.findViewById<TextView>(R.id.tv_value).text = (item as Person).firstName
        }
    }
}