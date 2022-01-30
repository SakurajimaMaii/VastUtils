package com.gcode.vastadapter.adapter

import android.view.View

interface VastBindingAdapterItem {
    var vbAdpClickEvent: ((itemView: View, item: VastBindingAdapterItem, pos: Int, itemId: Long) -> Unit)?

    var vbAdpLongClickEvent: ((itemView: View, item: VastBindingAdapterItem, pos: Int, itemId: Long) -> Boolean)?

    fun getVBAdpIType(): Int
}