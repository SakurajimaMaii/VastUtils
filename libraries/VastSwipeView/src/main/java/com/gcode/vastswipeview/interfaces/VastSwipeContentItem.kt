package com.gcode.vastswipeview.interfaces

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/12/31
 */

/**
 * Vast swipe content item
 *
 * You should make your list item model
 * implement [VastSwipeContentItem] and
 * override the [getType]
 */
interface VastSwipeContentItem{
    fun getType():String
}