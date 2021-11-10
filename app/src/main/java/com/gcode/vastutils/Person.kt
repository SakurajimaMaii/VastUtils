package com.gcode.vastutils

import com.gcode.vastadapter.BaseVastItem

/**
 *作者:created by HP on 2021/4/2 15:54
 *邮箱:sakurajimamai2020@qq.com
 */
data class Person(val firstName:String,val lastName: String): BaseVastItem {
    override fun getItemViewType(): Int {
        return R.layout.item_layout
    }

    override fun getItemBindViewType(): Int {
        return R.layout.item_bind_layout
    }
}
