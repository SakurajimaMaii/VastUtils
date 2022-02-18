package com.gcode.vastutils.baseadpexample.model

import com.gcode.vastadapter.base.VAapClickEvent
import com.gcode.vastadapter.base.VAdpLongClickEvent
import com.gcode.vastadapter.interfaces.VastAdapterItem

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/2/16
 */
class AExample(
    val data: String,
    override var vAdpClickEvent: VAapClickEvent,
    override var vAdpLongClickEvent: VAdpLongClickEvent
):VastAdapterItem {
    override fun getVAdpItemType(): String {
        return "person"
    }
}