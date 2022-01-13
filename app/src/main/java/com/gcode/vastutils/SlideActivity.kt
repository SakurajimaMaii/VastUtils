package com.gcode.vastutils

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcode.vastswipeview.VastSwipeViewMgr
import com.gcode.vastswipeview.adapter.VastSwipeViewAdapter
import com.gcode.vastswipeview.adapter.VastSwipeViewMenuVH
import com.gcode.vastswipeview.annotation.VastSwipeViewConstant.*
import com.gcode.vastswipeview.interfaces.VastSwipeContentItem
import com.gcode.vastswipeview.model.VastSwipeMenuItem
import com.gcode.vastswipeview.view.VastSwipeView
import com.gcode.vastswipeview.view.VastSwipeViewItem
import com.gcode.vastutils.model.Person

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

        vastSwipeMenuMgr = VastSwipeViewMgr(this).apply {
            setMenuStyle(LEFT_RIGHT)
        }

        lists = initData()

        //创建删除菜单
        val deleteItem = VastSwipeMenuItem(this@SlideActivity)
        deleteItem.setBackgroundByColorInt(0xFF1e90ff)
        deleteItem.setTitleByString("删除")
        deleteItem.setTitleColorByColorInt(Color.WHITE)
        deleteItem.setIconByResId(R.drawable.ic_delete)
        deleteItem.setClickEvent { item: VastSwipeMenuItem, position: Int ->
            Toast.makeText(this@SlideActivity, "$position", Toast.LENGTH_SHORT).show();
        }
        vastSwipeMenuMgr.setLeftMenuItems(ArrayList<VastSwipeMenuItem>().apply {
            add(deleteItem)
        })
        //创建刷新菜单
        val refreshItem = VastSwipeMenuItem(this@SlideActivity)
        refreshItem.setBackgroundByColorInt(0xFFff4757)
        refreshItem.setTitleByString("刷新")
        refreshItem.setIconByResId(R.drawable.ic_refresh)
        val revokeItem = VastSwipeMenuItem(this@SlideActivity)
        revokeItem.setBackgroundByColorInt(0xff6ab04c)
        revokeItem.setTitleByString("撤销")
        revokeItem.setIconByResId(R.drawable.ic_revoke)
        vastSwipeMenuMgr.addRightMenuItems(ArrayList<VastSwipeMenuItem>().apply {
            add(deleteItem)
            add(refreshItem)
            //add(revokeItem)
        })

        vastSwipeMenuMgr.setSwipeMenuContentStyle(ICON_TITLE)
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
        vastSwipeView.adapter =
            VastSwipeViewAdapter(lists, this, ArrayList<VastSwipeViewMenuVH.Factory>().apply {
                add(contentVH.contentFactory())
            }, vastSwipeMenuMgr)
    }

    private fun initData(): MutableList<Person> {
        val list: ArrayList<Person> = ArrayList()
        var i = 'A'.code
        while (i <= 'Z'.code) {
            list.add(Person(i.toChar().toString(), i.toChar().toString()))
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

    class contentVH(val itemView: View) : VastSwipeViewMenuVH(itemView) {

        private var tv: TextView = itemView.findViewById(R.id.tv_value)

        class contentFactory : VastSwipeViewMenuVH.Factory {

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int,
                manager: VastSwipeViewMgr
            ): VastSwipeViewMenuVH {
                val contentView =
                    LayoutInflater.from(parent.context).inflate(R.layout.listview_item, null)
                val itemView = VastSwipeViewItem(contentView, manager)
                return contentVH(itemView)
            }

            override fun getType(): String {
                return "person"
            }
        }

        override fun onBindData(item: VastSwipeContentItem) {
            super.onBindData(item)
            tv.text = (item as Person).firstName
        }

    }
}