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
    var vbAdpClickEventListener: VAapClickEventListener?,
    var vbAdpLongClickEventListener: VAdpLongClickEventListener?,
) :VastBindAdapterItem {

    override fun setVBAapClickEventListener(l: VAapClickEventListener?) {
        vbAdpClickEventListener = l
    }

    override fun getVBAapClickEventListener(): VAapClickEventListener? {
        return vbAdpClickEventListener
    }

    override fun setVBAdpLongClickEventListener(l: VAdpLongClickEventListener?) {
        vbAdpLongClickEventListener = l
    }

    override fun getVBAdpLongClickEventListener(): VAdpLongClickEventListener? {
        return vbAdpLongClickEventListener
    }

    override fun getVBAdpItemType(): Int {
        return R.layout.item_menu
    }
}