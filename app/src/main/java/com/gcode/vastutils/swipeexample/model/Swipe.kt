package com.gcode.vastutils.swipeexample.model

import com.gcode.vastadapter.base.VAapClickEvent
import com.gcode.vastadapter.base.VAdpLongClickEvent
import com.gcode.vastadapter.interfaces.VastBindAdapterItem
import com.gcode.vastutils.R

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/2/21
 */
class Swipe(
    val str:String,
    override var vbAdpClickEvent: VAapClickEvent,
    override var vbAdpLongClickEvent: VAdpLongClickEvent
) :VastBindAdapterItem {
    override fun getVBAdpItemType(): Int {
        return R.layout.item_menu
    }
}