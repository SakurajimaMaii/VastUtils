package com.gcode.vastadapter.baseadapter

interface BaseVastItem {
    /**
     * Use when calling BaseAdapter
     * @return Int
     */
    fun getItemViewType():Int{
        return -1
    }

    /**
     * Use when calling BaseBindingAdapter
     * @return Int
     */
    fun getItemBindViewType():Int{
        return -1
    }
}