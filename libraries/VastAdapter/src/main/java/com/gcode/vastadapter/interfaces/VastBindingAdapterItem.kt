package com.gcode.vastadapter.interfaces

import com.gcode.vastadapter.base.VAapClickEvent
import com.gcode.vastadapter.base.VAdpLongClickEvent

interface VastBindingAdapterItem {
    var vbAdpClickEvent: VAapClickEvent

    var vbAdpLongClickEvent: VAdpLongClickEvent

    fun getVBAdpItemType(): Int
}