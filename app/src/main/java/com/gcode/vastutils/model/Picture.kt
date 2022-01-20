package com.gcode.vastutils.model

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import com.gcode.vastadapter.interfaces.VastAdapterItem

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/20
 */
data class Picture(@DrawableRes val resId:Int):VastAdapterItem{
    override fun getVastAdapterItemType(): String {
        return "picture"
    }
}
