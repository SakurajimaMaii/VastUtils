package com.gcode.vastutils.basebindadpexample.model

import com.gcode.vastadapter.base.VAapClickEvent
import com.gcode.vastadapter.base.VAdpLongClickEvent
import com.gcode.vastadapter.interfaces.VastBindAdapterItem
import com.gcode.vastutils.R

/**
 *作者:created by HP on 2021/4/2 15:54
 *邮箱:sakurajimamai2020@qq.com
 */
data class Person(
    val firstName: String, val lastName: String,
    override var vbAdpClickEvent: VAapClickEvent,
    override var vbAdpLongClickEvent: VAdpLongClickEvent,
) :VastBindAdapterItem {

    override fun getVBAdpItemType(): Int {
        return R.layout.item_bind_textview
    }

}