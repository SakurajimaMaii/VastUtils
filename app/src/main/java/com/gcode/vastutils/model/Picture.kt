package com.gcode.vastutils.model

import android.view.View
import androidx.annotation.DrawableRes
import com.gcode.vastadapter.adapter.VastAdapterItem
import com.gcode.vastadapter.adapter.VastBindingAdapterItem
import com.gcode.vastutils.R

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/20
 */
data class Picture(@DrawableRes val resId:Int,
                   override var vAdpClickEvent: ((itemView: View, item: VastAdapterItem, pos: Int, itemId: Long) -> Unit)?,
                   override var vAdpLongClickEvent: ((itemView: View, item: VastAdapterItem, pos: Int, itemId: Long) -> Boolean)?,
                   override var vbAdpClickEvent: ((itemView: View, item: VastBindingAdapterItem, pos: Int, itemId: Long) -> Unit)?,
                   override var vbAdpLongClickEvent: ((itemView: View, item: VastBindingAdapterItem, pos: Int, itemId: Long) -> Boolean)?
): VastAdapterItem,VastBindingAdapterItem {
    override fun getVAIType(): String {
        return "picture"
    }

    override fun getVBAdpIType(): Int {
        return R.layout.item_bind_imageview
    }
}
