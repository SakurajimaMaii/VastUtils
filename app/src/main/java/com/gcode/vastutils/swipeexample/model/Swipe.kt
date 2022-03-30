package com.gcode.vastutils.swipeexample.model

import com.gcode.vastadapter.interfaces.VAapClickEventListener
import com.gcode.vastadapter.interfaces.VAdpLongClickEventListener
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
    override var vbAdpClickEventListener: VAapClickEventListener?,
    override var vbAdpLongClickEventListener: VAdpLongClickEventListener?,
) :VastBindAdapterItem {
    override fun getVBAdpItemType(): Int {
        return R.layout.item_menu
    }
}