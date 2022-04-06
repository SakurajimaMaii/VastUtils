package com.gcode.vastutils.baseadpexample.model

import com.gcode.vastadapter.interfaces.VAapClickEventListener
import com.gcode.vastadapter.interfaces.VAdpLongClickEventListener
import com.gcode.vastadapter.interfaces.VastAdapterItem

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/2/16
 */
class BExample(
    val drawable:Int,
    var clickEventListener: VAapClickEventListener?,
    var longClickEventListener: VAdpLongClickEventListener?
) : VastAdapterItem {

    override fun setVAapClickEventListener(l: VAapClickEventListener?) {
        clickEventListener = l
    }

    override fun getVAapClickEventListener(): VAapClickEventListener? {
        return clickEventListener
    }

    override fun setVAdpLongClickEventListener(l: VAdpLongClickEventListener?) {
        longClickEventListener = l
    }

    override fun getVAdpLongClickEventListener(): VAdpLongClickEventListener? {
        return longClickEventListener
    }

    override fun getVAdpItemType(): String {
        return "picture"
    }
}