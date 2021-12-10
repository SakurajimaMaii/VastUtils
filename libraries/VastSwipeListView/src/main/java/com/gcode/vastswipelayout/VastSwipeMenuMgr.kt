package com.gcode.vastswipelayout

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.animation.Interpolator
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import com.gcode.vastswipelayout.annotation.VastSwipeListViewConstant
import com.gcode.vastswipelayout.annotation.VastSwipeListViewConstant.*
import com.gcode.vastswipelayout.model.VastSwipeMenuItem
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
 * You can set swipe menu item or layout by [VastSwipeMenuMgr]
 */
class VastSwipeMenuMgr @JvmOverloads constructor(
    context: Context,
    leftMenuItems: ArrayList<VastSwipeMenuItem> = ArrayList(),
    rightMenuItems: ArrayList<VastSwipeMenuItem> = ArrayList()
){
    var context:Context = context
        private set

    /**
     * An array of [VastSwipeMenuItem] item on the left.
     */
    var leftMenuItems:ArrayList<VastSwipeMenuItem> = leftMenuItems
        private set

    /**
     * An array of [VastSwipeMenuItem] item on the right.
     */
    var rightMenuItems:ArrayList<VastSwipeMenuItem> = rightMenuItems
        private set

    /**
     * Title size,default font size is **15sp**.
     */
    var titleSize = context.resources.getDimension(R.dimen.default_menu_item_title_size)
        private set

    /**
     * Icon size,default font size is **30dp**.
     */
    var iconSize = context.resources.getDimension(R.dimen.default_menu_item_icon_size)
        private set

    /**
     * Menu content style
     *
     * @see VastSwipeListViewConstant.ONLY_ICON
     * @see VastSwipeListViewConstant.ONLY_TITLE
     * @see VastSwipeListViewConstant.ICON_TITLE
     */
    var swipeMenuContentStyle:String = ICON_TITLE
        private set

    /**
     * Swipe menu open duration(default value is **300**).
     */
    var swipeMenuOpenDuration:Int = 300
        private set

    /**
     * Swipe menu close duration(default value is **300**).
     */
    var swipeMenuCloseDuration:Int = 300
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
     * Menu style.Default value is [ONLY_RIGHT]
     *
     * @see VastSwipeListViewConstant.NOT_INIT
     * @see VastSwipeListViewConstant.ONLY_RIGHT
     * @see VastSwipeListViewConstant.ONLY_LEFT
     * @see VastSwipeListViewConstant.LEFT_RIGHT
     */
    var swipeMenuStyle:Int = NOT_INIT
        private set

    /**
     * Set [leftMenuItems] and [rightMenuItems] by [swipeMenuStyle].
     *
     * When you call [setItems],if [swipeMenuStyle] is [ONLY_LEFT],
     * you can not set [rightMenuItems],or [swipeMenuStyle] is [ONLY_RIGHT]
     * you can not set [leftMenuItems].
     *
     * @throws Throwable When [swipeMenuStyle] is equals to [NOT_INIT],
     *                   then throw an exception.
     */
    @JvmOverloads
    @Throws(Throwable::class)
    fun setItems(
        leftItems:ArrayList<VastSwipeMenuItem> = ArrayList(),
        rightItems:ArrayList<VastSwipeMenuItem> = ArrayList(),
    ){
        when(swipeMenuStyle){
            NOT_INIT->{
                throw Throwable("You should set swipeMenuStyle")
            }
            ONLY_RIGHT -> {
                leftMenuItems.clear()
                rightMenuItems = rightItems
            }
            ONLY_LEFT ->{
                leftMenuItems = leftItems
                rightMenuItems.clear()
            }
            LEFT_RIGHT -> {
                leftMenuItems = leftItems
                rightMenuItems = rightItems
            }
        }
    }

    /**
     * Add [item] to the end of [leftMenuItems]
     * when [swipeMenuStyle] is not [ONLY_RIGHT].
     *
     * @throws Throwable When [swipeMenuStyle] is equals to [NOT_INIT],
     *                   then throw an exception.
     */
    @Throws(Throwable::class)
    fun addLeftMenuItem(item: VastSwipeMenuItem) {
        when(swipeMenuStyle){
            NOT_INIT->{
                throw Throwable("You should set swipeMenuStyle")
            }
            ONLY_RIGHT->{
                return
            }
            else ->{
                leftMenuItems.add(item)
            }
        }
    }

    /**
     * Add [item] to the end of [rightMenuItems]
     * when [swipeMenuStyle] is not [ONLY_LEFT].
     *
     * @throws Throwable When [swipeMenuStyle] is equals to [NOT_INIT],
     *                   then throw an exception.
     */
    @Throws(Throwable::class)
    fun addRightMenuItem(item: VastSwipeMenuItem){
        when(swipeMenuStyle){
            NOT_INIT->{
                throw Throwable("You should set swipeMenuStyle")
            }
            ONLY_LEFT->{
                return
            }
            else ->{
                rightMenuItems.add(item)
            }
        }
    }

    /**
     * Set [VastSwipeMenuMgr.swipeMenuContentStyle].
     */
    fun setSwipeMenuContentStyle(@VastSwipeListViewConstant.SwipeMenuContentStyle swipeMenuContentStyle:String){
        this.swipeMenuContentStyle = swipeMenuContentStyle
    }

    /**
     * Set [VastSwipeMenuMgr.swipeMenuOpenDuration]
     */
    fun setSwipeMenuOpenDuration(@IntRange(from = 0) openDuration:Int){
        swipeMenuOpenDuration = openDuration
    }

    /**
     * Set [VastSwipeMenuMgr.swipeMenuCloseDuration]
     */
    fun setSwipeMenuCloseDuration(@IntRange(from = 0) closeDuration:Int){
        swipeMenuCloseDuration = closeDuration
    }

    /**
     * Set titleSize by [titleSize].
     */
    fun setTitleSizeByFloat(@FloatRange(from = 0.0) titleSize:Float){
        this.titleSize = titleSize
    }

    /**
     * Set titleSize by [titleSizeSp].
     */
    fun setTitleSizeBySp(@FloatRange(from = 0.0) titleSizeSp: Float){
        this.titleSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            titleSize,
            Resources.getSystem().displayMetrics
        )
    }

    /**
     * Set iconSize by [iconSize].
     */
    fun setIconSizeByFloat(@FloatRange(from = 0.0) iconSize:Float){
        this.iconSize = iconSize
    }

    /**
     * Set iconSize by [iconSizeDp].
     */
    fun setIconSizeByDp(@FloatRange(from = 0.0) iconSizeDp:Float){
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            iconSizeDp,
            Resources.getSystem().displayMetrics
        )
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
     * Set [swipeMenuStyle].
     */
    fun setSwipeMenuStyle(@VastSwipeListViewConstant.SwipeMenuStyle menuStyle: Int){
        swipeMenuStyle = menuStyle
    }
}