package com.gcode.vastutils.model

import android.view.View
import com.gcode.vastadapter.adapter.VastAdapterItem
import com.gcode.vastadapter.adapter.VastBindingAdapterItem
import com.gcode.vastswipeview.interfaces.VastSwipeContentItem
import com.gcode.vastutils.R

/**
 *作者:created by HP on 2021/4/2 15:54
 *邮箱:sakurajimamai2020@qq.com
 */
data class Person(
    val firstName: String, val lastName: String,
    override var vAdpClickEvent: ((View, VastAdapterItem,Int, Long) -> Unit)?,
    override var vAdpLongClickEvent: ((View, VastAdapterItem,Int, Long) -> Boolean)?,
    override var vbAdpClickEvent: ((View, VastBindingAdapterItem, Int, Long) -> Unit)?,
    override var vbAdpLongClickEvent: ((View,VastBindingAdapterItem, Int, Long) -> Boolean)?
) : VastSwipeContentItem, VastAdapterItem, VastBindingAdapterItem {
    override fun getType(): Int {
        return R.layout.menu_item
    }

    override fun getVAIType(): String {
        return "person"
    }

    override fun getVBAdpIType(): Int {
        return R.layout.item_bind_textview
    }
}
