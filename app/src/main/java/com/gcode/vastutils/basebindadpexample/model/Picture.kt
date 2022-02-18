package com.gcode.vastutils.basebindadpexample.model

import com.gcode.vastadapter.base.VAapClickEvent
import com.gcode.vastadapter.base.VAdpLongClickEvent
import com.gcode.vastadapter.interfaces.VastBindAdapterItem
import com.gcode.vastutils.R

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/20
 */
data class Picture(
    val drawable: Int,
    override var vbAdpClickEvent: VAapClickEvent,
    override var vbAdpLongClickEvent: VAdpLongClickEvent,
) : VastBindAdapterItem {

    override fun getVBAdpItemType(): Int {
        return R.layout.item_bind_imageview
    }

}
