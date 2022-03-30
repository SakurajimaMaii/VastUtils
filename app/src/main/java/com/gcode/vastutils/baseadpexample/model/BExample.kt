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
    override var vAdpClickEventListener: VAapClickEventListener?,
    override var vAdpLongClickEventListener: VAdpLongClickEventListener?
) : VastAdapterItem {
    override fun getVAdpItemType(): String {
        return "picture"
    }
}