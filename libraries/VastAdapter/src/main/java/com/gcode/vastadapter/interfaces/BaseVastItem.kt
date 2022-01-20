package com.gcode.vastadapter.interfaces

interface BaseVastItem {
    /**
     * Use when calling [BaseVastAdapter]
     * @return The name of the ViewHolder corresponding to the data item.
     */
    fun itemViewType():String

    /**
     * Use when calling BaseBindingAdapter
     * @return Int
     */
    fun itemBindViewType():Int{
        return -1
    }
}