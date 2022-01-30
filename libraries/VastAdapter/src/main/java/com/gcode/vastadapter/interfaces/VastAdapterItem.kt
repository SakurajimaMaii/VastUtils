package com.gcode.vastadapter.interfaces

import android.view.View
import com.gcode.vastadapter.base.VAapClickEvent
import com.gcode.vastadapter.base.VAdpLongClickEvent

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/19
 */
interface VastAdapterItem {
    var vAdpClickEvent: VAapClickEvent

    var vAdpLongClickEvent: VAdpLongClickEvent

    fun getVAdpItemType(): String
}