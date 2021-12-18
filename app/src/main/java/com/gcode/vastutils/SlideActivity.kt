package com.gcode.vastutils

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gcode.vastswipelistview.VastSwipeMenuMgr
import com.gcode.vastswipelistview.annotation.VastSwipeListViewConstant.*
import com.gcode.vastswipelistview.model.VastSwipeMenuItem
import com.gcode.vastswipelistview.view.VastSwipeListView

class SlideActivity : AppCompatActivity() {

    private lateinit var vastSwipeListView: VastSwipeListView

    private lateinit var delListViewAdapter: DelListViewAdapter

    private var inflater: LayoutInflater? = null
    private var context: Context? = null
    private var lists: List<String> = ArrayList()

    private lateinit var vastSwipeMenuMgr: VastSwipeMenuMgr

    private val tag = "vasttest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)

        vastSwipeMenuMgr = VastSwipeMenuMgr(this).apply {
            setMenuStyle(LEFT_RIGHT)
        }

        //创建删除菜单
        val deleteItem = VastSwipeMenuItem(this@SlideActivity)
        deleteItem.setBackgroundByColorInt(0xFF1e90ff)
        deleteItem.setTitleByString("撤销")
        deleteItem.setTitleColorByColorInt(Color.WHITE)
        deleteItem.setIconByResId(R.drawable.ic_delete)
        deleteItem.setClickEvent { item: VastSwipeMenuItem, position: Int ->
            run {
                Toast.makeText(this@SlideActivity, "${item.title} $position", Toast.LENGTH_SHORT)
                    .show()
            }
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
            add(refreshItem)
            add(revokeItem)
        })

        vastSwipeMenuMgr.setSwipeMenuContentStyle(ICON_TITLE)
        vastSwipeMenuMgr.setEventListener(object : VastSwipeMenuMgr.EventListener {
            override fun eventDownListener(position: Int, event: MotionEvent) {
                //Log.d(tag,"eventDownListener")
            }

            override fun eventMoveListener(position: Int, event: MotionEvent) {
                //Log.d(tag,"eventMoveListener")
            }

            override fun eventUpListener(position: Int, event: MotionEvent) {
                //Log.d(tag,"eventUpListener")
            }
        })
        vastSwipeMenuMgr.setMenuOpenDuration(200)
        vastSwipeMenuMgr.setLeftMenuOpenDistanceThreshold(50)
        vastSwipeMenuMgr.setRightMenuOpenDistanceThreshold(50)

        inflater = LayoutInflater.from(this)
        context = applicationContext
        vastSwipeListView = findViewById(R.id.listview)
        lists = initData()
        delListViewAdapter = DelListViewAdapter(this, R.layout.listview_item, lists)
        vastSwipeListView.setSwipeMenuMgr(vastSwipeMenuMgr)
        vastSwipeListView.adapter = delListViewAdapter
        vastSwipeListView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, arg2, _ ->
                Toast.makeText(
                    context,
                    "位置   " + arg2 + "  >>>  value：" + lists[arg2],
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun initData(): List<String> {
        val list: ArrayList<String> = ArrayList()
        var i = 'A'.code
        while (i <= 'Z'.code) {
            list.add(i.toChar().toString() + "")
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
}