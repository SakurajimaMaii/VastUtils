package com.gcode.vastutils.model

import android.view.View
import com.gcode.vastadapter.base.VAapClickEvent
import com.gcode.vastadapter.base.VAdpLongClickEvent
import com.gcode.vastadapter.interfaces.VastAdapterItem
import com.gcode.vastadapter.interfaces.VastBindingAdapterItem
import com.gcode.vastswipeview.interfaces.VastSwipeContentItem
import com.gcode.vastutils.R

/**
 *作者:created by HP on 2021/4/2 15:54
 *邮箱:sakurajimamai2020@qq.com
 */
data class Person(
    val firstName: String, val lastName: String,
    override var vAdpClickEvent: VAapClickEvent,
    override var vAdpLongClickEvent: VAdpLongClickEvent,
    override var vbAdpClickEvent: VAapClickEvent,
    override var vbAdpLongClickEvent: VAdpLongClickEvent,
) : VastSwipeContentItem, VastAdapterItem, VastBindingAdapterItem {
    override fun getType(): Int {
        return R.layout.menu_item
    }

    override fun getVAdpItemType(): String {
        return "person"
    }

    override fun getVBAdpItemType(): Int {
        return R.layout.item_bind_textview
    }
}
