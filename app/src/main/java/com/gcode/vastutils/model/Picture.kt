package com.gcode.vastutils.model

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import com.gcode.vastadapter.base.VAapClickEvent
import com.gcode.vastadapter.base.VAdpLongClickEvent
import com.gcode.vastadapter.interfaces.VastAdapterItem
import com.gcode.vastadapter.interfaces.VastBindingAdapterItem
import com.gcode.vastutils.R

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/20
 */
data class Picture(val drawable:Drawable,
                   override var vAdpClickEvent: VAapClickEvent,
                   override var vAdpLongClickEvent: VAdpLongClickEvent,
                   override var vbAdpClickEvent: VAapClickEvent,
                   override var vbAdpLongClickEvent: VAdpLongClickEvent,
): VastAdapterItem, VastBindingAdapterItem {
    override fun getVAdpItemType(): String {
        return "picture"
    }

    override fun getVBAdpItemType(): Int {
        return R.layout.item_bind_imageview
    }
}
