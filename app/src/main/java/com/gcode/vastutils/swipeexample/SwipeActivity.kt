package com.gcode.vastutils.swipeexample

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastactfrag.VastVbActivity
import com.gcode.vastadapter.base.BaseVastBindAdapter
import com.gcode.vastadapter.interfaces.VastBindAdapterItem
import com.gcode.vastswiperecyclerview.VastSwipeRvMgr
import com.gcode.vastswiperecyclerview.annotation.ICON_TITLE
import com.gcode.vastswiperecyclerview.annotation.ONLY_ICON
import com.gcode.vastswiperecyclerview.interfaces.VastSwipeItemClickListener
import com.gcode.vastswiperecyclerview.interfaces.VastSwipeItemLongClickListener
import com.gcode.vastswiperecyclerview.interfaces.VastSwipeMenuCreator
import com.gcode.vastswiperecyclerview.model.VastSwipeMenu
import com.gcode.vastswiperecyclerview.view.VastSwipeMenuView
import com.gcode.vasttools.utils.DensityUtils.dp
import com.gcode.vasttools.utils.DensityUtils.sp
import com.gcode.vasttools.utils.showShortMsg
import com.gcode.vastutils.BR
import com.gcode.vastutils.R
import com.gcode.vastutils.databinding.ActivitySwipeBinding
import com.gcode.vastutils.swipeexample.model.Swipe

class SwipeActivity : VastVbActivity<ActivitySwipeBinding>(){

    private val tag:String = this.javaClass.simpleName

    private var lists: MutableList<VastBindAdapterItem> = ArrayList()

    private fun initData(): MutableList<VastBindAdapterItem> {
        val list: ArrayList<VastBindAdapterItem> = ArrayList()
        var i = 'A'.code
        while (i <= 'A'.code) {
            list.add(Swipe(i.toChar().toString(),null,null))
            i++
        }
        return list
    }

    class Adapter(data:MutableList<VastBindAdapterItem>):BaseVastBindAdapter(data){

        override fun setVariableId(): Int {
            return BR.item
        }

    }

    override fun onActCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {

        lists = initData()

        val manager = VastSwipeRvMgr(this).apply {
            setTitleSize(20f)
            setIconSize(40f.dp)
            setMenuWidth(100f.dp)
            setSwipeMenuContentStyle(ICON_TITLE)
            setSwipeItemClickListener(object: VastSwipeItemClickListener{
                override fun onClickEvent(view: View, adapterPosition: Int) {
                    showShortMsg("Hello")
                }
            })
            setSwipeItemLongClickListener(object: VastSwipeItemLongClickListener {
                override fun onLongClickEvent(view: View, adapterPosition: Int): Boolean {
                    showShortMsg("Long Hello")
                    return true
                }
            })
            setSwipeMenuCreator(object :VastSwipeMenuCreator{
                override fun onCreateMenu(
                    leftMenu: MutableList<VastSwipeMenu>,
                    rightMenu: MutableList<VastSwipeMenu>,
                    position: Int
                ) {
                    val delete = VastSwipeMenu(
                        this@SwipeActivity,"删除",ContextCompat.getDrawable(this@SwipeActivity,R.drawable.ic_delete))
                    leftMenu.add(delete)

                    val refresh = VastSwipeMenu(this@SwipeActivity,"刷新",ContextCompat.getDrawable(this@SwipeActivity,R.drawable.ic_refresh))
                    rightMenu.add(refresh)
                }
            })
        }

        val mAdapter = TAdapter(ArrayList<String>().apply {
            var i = 'A'.code
            while (i <= 'Z'.code) {
                this.add(i.toString())
                i++
            }
        })

        val tv = TextView(this).apply {

        }

        mBinding.swipeRv.apply {
            setManager(manager)
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@SwipeActivity)
        }
    }
}

class TAdapter(val data:ArrayList<String>): RecyclerView.Adapter<TAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        var tv:TextView = view.findViewById(R.id.tv_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }
}