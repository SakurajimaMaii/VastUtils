package com.gcode.vastswipeview

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.MotionEvent
import android.view.animation.Interpolator
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import com.gcode.vastswipeview.annotation.VastSwipeViewConstant
import com.gcode.vastswipeview.annotation.VastSwipeViewConstant.*
import com.gcode.vastswipeview.exception.VastSwipeViewNotInit
import kotlin.jvm.Throws

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/29
 */

/**
 * VastSwipeMenuMgr
 *
 * By using the [VastSwipeViewMgr],you can set the menu content style,
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
class VastSwipeViewMgr @JvmOverloads constructor(
    context: Context
){
    var context:Context = context
        private set

    /**
     * Swipe menu open duration(default value is **300**).
     */
    var menuOpenDuration:Int = 300
        private set

    /**
     * Swipe menu close duration(default value is **300**).
     */
    var menuCloseDuration:Int = 300
        private set

    /**
     * Close interpolator.
     */
    var closeInterpolator: Interpolator? = null
        private set

    /**
     * Open interpolator.
     */
    var openInterpolator: Interpolator? = null
        private set

    /**
     * Lets you to define the swipe events.
     */
    var eventListener: EventListener? = null
        private set

    /**
     * When the distance(in pixels) you swipe to the right on the screen
     * is greater than the half of [leftMenuOpenDistanceThreshold],the
     * left menu will be opened.If [leftMenuOpenDistanceThreshold] takes
     * the default value of -1,The distance threshold value will be set
     * to the half of the contentView width.When you set it,it should be
     * set to a value between 0 and the screen width.
     */
    var leftMenuOpenDistanceThreshold:Int = -1
        private set

    /**
     * When the distance(in pixels) you swipe to the left on the screen
     * is greater than the half of [rightMenuOpenDistanceThreshold],the
     * right menu will be opened.If [rightMenuOpenDistanceThreshold] takes
     * the default value of -1,The distance threshold value will be set
     * to the half of the contentView width.When you set it,it should be
     * set to a value between 0 and the screen width.
     */
    var rightMenuOpenDistanceThreshold:Int = -1
        private set

    /**
     * Menu style,you can learn more by check [VastSwipeViewConstant.MenuStyle]
     */
    var menuStyle: Int = NOT_INIT
        private set

    /**
     * Set [VastSwipeViewMgr.menuOpenDuration]
     */
    fun setMenuOpenDuration(@IntRange(from = 0) openDuration:Int){
        menuOpenDuration = openDuration
    }

    /**
     * Set [VastSwipeViewMgr.menuCloseDuration]
     */
    fun setMenuCloseDuration(@IntRange(from = 0) closeDuration:Int){
        menuCloseDuration = closeDuration
    }

    /**
     * Set close interpolator.
     */
    fun setCloseInterpolator(interpolator: Interpolator?) {
        closeInterpolator = interpolator
    }

    /**
     * Set open interpolator.
     */
    fun setOpenInterpolator(interpolator: Interpolator?) {
        openInterpolator = interpolator
    }

    /**
     * Set [VastSwipeViewMgr.eventListener]
     */
    fun setEventListener(eventListener: EventListener){
        this.eventListener = eventListener
    }

    /**
     * Set [leftMenuOpenDistanceThreshold] (in pixels)
     */
    fun setLeftMenuOpenDistanceThreshold(@IntRange(from = 0) distance: Int){
        leftMenuOpenDistanceThreshold = distance
    }

    /**
     * Set [rightMenuOpenDistanceThreshold] (in pixels)
     */
    fun setRightMenuOpenDistanceThreshold(@IntRange(from = 0) distance: Int){
        rightMenuOpenDistanceThreshold = distance
    }

    /**
     * Set [menuStyle].This will cause an error if the
     * [menuStyle] does not match your actual layout file.
     */
    fun setMenuStyle(@VastSwipeViewConstant.MenuStyle style: Int){
        menuStyle = style
    }

    interface EventListener {
        /**
         * @param position The position of the list item you touch.
         */
        fun eventDownListener(position: Int,event: MotionEvent)

        /**
         * @param position The position of the list item you touch.
         */
        fun eventMoveListener(position: Int,event: MotionEvent)

        /**
         * @param position The position of the list item you touch.
         */
        fun eventUpListener(position: Int,event: MotionEvent)

        /**
         * @param position The position of the list item you touch.
         */
        fun eventCancelListener(position: Int,event: MotionEvent)
    }
}