package com.gcode.vastutils.model

import com.gcode.vastadapter.BaseVastItem
import com.gcode.vastadapter.interfaces.VastAdapterItem
import com.gcode.vastswipeview.interfaces.VastSwipeContentItem
import com.gcode.vastutils.R

/**
 *作者:created by HP on 2021/4/2 15:54
 *邮箱:sakurajimamai2020@qq.com
 */
data class Person(val firstName:String,val lastName: String): BaseVastItem, VastSwipeContentItem,VastAdapterItem {
    override fun getItemViewType(): Int {
        return R.layout.item_textview
    }

    override fun getItemBindViewType(): Int {
        return R.layout.item_bind_layout
    }

    override fun getType(): Int {
        return R.layout.menu_item
    }

    override fun getVastAdapterItemType(): String {
        return "person"
    }
}
