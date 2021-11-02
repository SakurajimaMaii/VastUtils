package com.gcode.vastadapter

/**
 *作者:created by HP on 2021/4/2 14:37
 *邮箱:sakurajimamai2020@qq.com
 */
interface BaseGcodeItem {
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