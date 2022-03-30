package com.gcode.vastadapter.interfaces

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/19
 */
interface VastAdapterItem {
    var vAdpClickEventListener: VAapClickEventListener?

    var vAdpLongClickEventListener: VAdpLongClickEventListener?

    fun getVAdpItemType(): String
}