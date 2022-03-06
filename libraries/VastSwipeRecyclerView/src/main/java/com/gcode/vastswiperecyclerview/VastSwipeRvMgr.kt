/*
 * MIT License
 *
 * Copyright (c) 2021 码上夏雨
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.gcode.vastswiperecyclerview

import android.content.Context
import android.util.TypedValue
import android.view.animation.Interpolator
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import com.gcode.vastswiperecyclerview.annotation.*
import com.gcode.vastswiperecyclerview.interfaces.VastSwipeItemClickListener
import com.gcode.vastswiperecyclerview.interfaces.VastSwipeItemLongClickListener
import com.gcode.vastswiperecyclerview.interfaces.VastSwipeMenuCreator
import com.gcode.vastswiperecyclerview.view.VastSwipeRecyclerView
import com.gcode.vastswipeview.R

/**
 * @OriginalAuthor: Vast Gui @OriginalDate: @EditAuthor: Vast Gui
 * @EditDate: 2021/11/29
 */

/**
 * VastSwipeMenuMgr
 *
 * By using the [VastSwipeRvMgr],you can set the menu content style,
 * title size,icon size,leftMenuItems,rightMenuItems,menu open duration,
 * menu close duration,open distance threshold,close distance threshold,
 * open interpolator,close interpolator and eventListener.For example:
 *
 * ```kotlin
 * //Firstly you must set the swipeMenuStyle.It is most important.
 * vastSwipeMenuMgr.setSwipeMenuStyle(LEFT_RIGHT)
 *
 * //Secondly,you need to set the menu items;
 * //leftMenuItems is an array of the VastSwipeMenuItem
 * vastSwipeMenuMgr.setLeftMenuItems(leftMenuItems)
 *
 * //Thirdly,if you just want to show the icon,you can do like this
 * vastSwipeMenuMgr.setSwipeMenuContentStyle(ONLY_ICON)
 *
 * //You can define eventListener by this way
 * vastSwipeMenuMgr.setEventListener(object : VastSwipeMenuMgr.EventListener {
 *      override fun eventDownListener(position: Int, event: MotionEvent) {
 *          Log.d(tag,"eventDownListener")
 *      }
 *      override fun eventMoveListener(position: Int, event: MotionEvent) {
 *          Log.d(tag,"eventMoveListener")
 *      }
 *
 *      override fun eventUpListener(position: Int, event: MotionEvent) {
 *          Log.d(tag,"eventUpListener")
 *      }
 * })
 *
 * //Now VastSwipeMenuMgr is ready to be used,you should do this
 * vastSwipeListView.setSwipeMenuMgr(vastSwipeMenuMgr)
 * ```
 */
class VastSwipeRvMgr constructor(
    context: Context
) {
    var context: Context = context
        private set

    /**
     * Title size and unit,default font size is **15sp**.
     */
    var titleSize: TitleSize =
        TitleSize(context.resources.getDimension(R.dimen.default_menu_item_title_size))


    /** Icon size,default font size is **30dp**. */
    var iconSize = context.resources.getDimension(R.dimen.default_menu_item_icon_size)
        private set

    /**
     * Swipe menu content style.
     *
     * @see ONLY_TITLE
     * @see ONLY_ICON
     * @see ICON_TITLE
     */
    var swipeMenuContentStyle: String = ICON_TITLE
        private set

    /** Swipe menu item width. */
    var swipeMenuWidth = 100f
        private set

    /** Swipe menu open duration(default value is **300**). */
    var menuOpenDuration: Int = 300
        private set

    /** Swipe menu close duration(default value is **300**). */
    var menuCloseDuration: Int = 300
        private set

    /** Close interpolator. */
    var closeInterpolator: Interpolator? = null
        private set

    /** Open interpolator. */
    var openInterpolator: Interpolator? = null
        private set

    /**
     * When the distance(in pixels) you swipe to the right on the screen
     * is greater than the half of [leftMenuOpenDistanceThreshold],the
     * left menu will be opened.If [leftMenuOpenDistanceThreshold]
     * takes the default value of -1,The distance threshold value
     * will be set to the half of the contentView width.When you set
     * it,it should be set to a value between 0 and the screen width.
     */
    var leftMenuOpenDistanceThreshold: Int = -1
        private set

    /**
     * When the distance(in pixels) you swipe to the left on the screen
     * is greater than the half of [rightMenuOpenDistanceThreshold],the
     * right menu will be opened.If [rightMenuOpenDistanceThreshold]
     * takes the default value of -1,The distance threshold value
     * will be set to the half of the contentView width.When you set
     * it,it should be set to a value between 0 and the screen width.
     */
    var rightMenuOpenDistanceThreshold: Int = -1
        private set


    /**
     * Listener used to dispatch click events of the item view in
     * [VastSwipeRecyclerView].
     */
    var mSwipeItemClickListener: VastSwipeItemClickListener? = null
        private set


    /**
     * Listener used to dispatch long click events of the item view in
     * [VastSwipeRecyclerView].
     */
    var mSwipeItemLongClickListener: VastSwipeItemLongClickListener? = null
        private set

    /** Creator used to create the left and right menu. */
    var mSwipeMenuCreator: VastSwipeMenuCreator? = null
        private set

    /** Set [VastSwipeRvMgr.swipeMenuContentStyle]. */
    fun setSwipeMenuContentStyle(@SwipeMenuContentStyle swipeMenuContentStyle: String) {
        this.swipeMenuContentStyle = swipeMenuContentStyle
    }

    /** Set [VastSwipeRvMgr.menuOpenDuration]. */
    fun setMenuOpenDuration(@IntRange(from = 0) openDuration: Int) {
        menuOpenDuration = openDuration
    }

    /** Set [VastSwipeRvMgr.menuCloseDuration]. */
    fun setMenuCloseDuration(@IntRange(from = 0) closeDuration: Int) {
        menuCloseDuration = closeDuration
    }

    /**
     * Set the [titleSize] to the given value, interpreted as "scaled
     * pixel" units.
     */
    fun setTitleSize(@FloatRange(from = 0.0) size: Float) {
        titleSize = TitleSize(size)
    }

    fun setTitleSize(@FloatRange(from = 0.0) size: Float, @Dimension unit: Int) {
        titleSize = TitleSize(size, unit)
    }

    /** Set iconSize by [iconSize] (in Scaled Pixels). */
    fun setIconSize(@FloatRange(from = 0.0) iconSize: Float) {
        this.iconSize = iconSize
    }

    /** Set swipeMenuWidth by [menuWidth]. */
    fun setMenuWidth(@FloatRange(from = 0.0) menuWidth: Float) {
        this.swipeMenuWidth = menuWidth
    }

    /** Set close interpolator. */
    fun setCloseInterpolator(interpolator: Interpolator?) {
        closeInterpolator = interpolator
    }

    /** Set open interpolator. */
    fun setOpenInterpolator(interpolator: Interpolator?) {
        openInterpolator = interpolator
    }

    /** Set [leftMenuOpenDistanceThreshold] (in pixels) */
    fun setLeftMenuOpenDistanceThreshold(@IntRange(from = 0) distance: Int) {
        leftMenuOpenDistanceThreshold = distance
    }

    /** Set [rightMenuOpenDistanceThreshold] (in pixels) */
    fun setRightMenuOpenDistanceThreshold(@IntRange(from = 0) distance: Int) {
        rightMenuOpenDistanceThreshold = distance
    }

    /**
     * Register a callback to be invoked when this item view in
     * RecyclerView is clicked.
     *
     * @param mSwipeItemClickListener
     */
    fun setSwipeItemClickListener(mSwipeItemClickListener: VastSwipeItemClickListener) {
        this.mSwipeItemClickListener = mSwipeItemClickListener
    }

    /**
     * Register a callback to be invoked when this item view in
     * RecyclerView is clicked and held.
     *
     * @param mSwipeItemLongClickListener
     */
    fun setSwipeItemLongClickListener(mSwipeItemLongClickListener: VastSwipeItemLongClickListener) {
        this.mSwipeItemLongClickListener = mSwipeItemLongClickListener
    }

    /**
     * Register a callback to be invoked when create the left and right
     * menu.
     *
     * @param mSwipeMenuCreator
     */
    fun setSwipeMenuCreator(mSwipeMenuCreator: VastSwipeMenuCreator?) {
        this.mSwipeMenuCreator = mSwipeMenuCreator
    }
}

data class TitleSize(val size: Float, val unit: Int = TypedValue.COMPLEX_UNIT_SP)