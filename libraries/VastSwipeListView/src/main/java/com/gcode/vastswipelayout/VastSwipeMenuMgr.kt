package com.gcode.vastswipelayout

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.animation.Interpolator
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import com.gcode.vastswipelayout.annotation.VastSwipeListViewConstant
import com.gcode.vastswipelayout.annotation.VastSwipeListViewConstant.ICON_TITLE
import com.gcode.vastswipelayout.model.VastSwipeMenuItem

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
class VastSwipeMenuMgr(context: Context, items: ArrayList<VastSwipeMenuItem> = ArrayList()){
    var context:Context = context
        private set

    var items:ArrayList<VastSwipeMenuItem> = items
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

    var closeInterpolator: Interpolator? = null
        private set
    var openInterpolator: Interpolator? = null
        private set

    fun setItems(items:ArrayList<VastSwipeMenuItem>){
        this.items = items
    }

    fun addMenuItem(item: VastSwipeMenuItem) {
        items.add(item)
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

    fun setCloseInterpolator(interpolator: Interpolator?) {
        closeInterpolator = interpolator
    }

    fun setOpenInterpolator(interpolator: Interpolator?) {
        openInterpolator = interpolator
    }
}