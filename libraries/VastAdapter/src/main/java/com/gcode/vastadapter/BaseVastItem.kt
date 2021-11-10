package com.gcode.vastadapter

interface BaseVastItem {
    /**
     * Use when calling BaseAdapter
     * @return Int
     */
    fun getItemViewType():Int

    /**
     * Use when calling BaseBindingAdapter
     * @return Int
     */
    fun getItemBindViewType():Int
}