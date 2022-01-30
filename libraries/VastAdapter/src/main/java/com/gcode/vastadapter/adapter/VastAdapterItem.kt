package com.gcode.vastadapter.adapter

import android.view.View

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/19
 */
interface VastAdapterItem {
    var vAdpClickEvent:((itemView: View, item: VastAdapterItem, pos:Int, itemId:Long)->Unit)?

    var vAdpLongClickEvent:((itemView: View, item: VastAdapterItem, pos:Int, itemId:Long)->Boolean)?

    fun getVAIType():String
}