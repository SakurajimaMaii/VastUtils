package com.gcode.vastswipelayout.model

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.gcode.vastswipelayout.R

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/29
 */

/**
 * VastSwipeMenuItem
 *
 * @param title menu title
 * @param icon menu icon
 * @param background default background is grey
 * @param titleColor default color is black
 * @param titleSize default font size is 15sp
 * @param iconSize default icon size is 30dp
 * @param clickEvent default value is null
 */
class VastSwipeMenuItem @JvmOverloads constructor(
    private val context: Context,
    title: String = context.resources.getString(R.string.default_slide_item_title),
    icon: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_null),
    background: Drawable? = ContextCompat.getDrawable(context, R.drawable.default_menu_item_background),
    titleColor: Int = ContextCompat.getColor(context, R.color.default_menu_item_title_color),
    titleSize: Float = context.resources.getDimension(R.dimen.default_menu_item_title_size),
    iconSize: Float = context.resources.getDimension(R.dimen.default_menu_item_icon_size),
    clickEvent: ((VastSwipeMenuItem, Int) -> Unit)? = null
) {
    /**
     * Title
     */
    var title: String = title
        private set
    /**
     * Icon
     */
    var icon: Drawable? = icon
        private set
    /**
     * Background,default background is **grey**.
     */
    var background: Drawable? = background
        private set
    /**
     * Title color,default color is **black**.
     */
    var titleColor = titleColor
        private set
    /**
     * Title size,default font size is **15sp**.
     *
     * titleSize is temporarily managed by
     * [com.gcode.vastswipelayout.VastSwipeMenuMgr]
     *
     * If you want to set titleSize,you should check
     * [com.gcode.vastswipelayout.VastSwipeMenuMgr.titleSize]
     */
    internal var titleSize = titleSize
        private set
    /**
     * Icon size,default font size is **30dp**.
     *
     * iconSize is temporarily managed by
     * [com.gcode.vastswipelayout.VastSwipeMenuMgr]
     *
     * If you want to set iconSize,you should check
     * [com.gcode.vastswipelayout.VastSwipeMenuMgr.iconSize]
     */
    internal var iconSize = iconSize
        private set
    /**
     * Click event
     *
     * @param VastSwipeMenuItem The menu item.
     * @param Int Index of the swipe menu in the list.
     */
    var clickEvent: ((VastSwipeMenuItem, Int) -> Unit)? = clickEvent
        private set

    /**
     * Set menu title by [title].
     */
    fun setTitleByString(title: String){
        this.title = title
    }

    /**
     * Set menu title by [titleRes].
     */
    fun setTitleByResId(@StringRes titleRes: Int){
        title = context.resources.getString(titleRes)
    }

    /**
     * Set menu icon by [icon].
     */
    fun setIconByDrawable(icon: Drawable){
        this.icon = icon
    }

    /**
     * Set menu icon by [iconResId].
     */
    fun setIconByResId(@DrawableRes iconResId:Int){
        icon = ContextCompat.getDrawable(context, iconResId)
    }

    /**
     * Set menu background by [background].
     */
    fun setBackgroundByDrawable(background: Drawable){
        this.background = background
    }

    /**
     * Set background by [backgroundResId].
     */
    fun setBackgroundByResId(@DrawableRes backgroundResId:Int){
        background = ContextCompat.getDrawable(context, backgroundResId)
    }

    /**
     * Set solid color by [colorInt]
     */
    fun setBackgroundByColorInt(@ColorInt colorInt: Long){
        background = ColorDrawable(colorInt.toInt())
    }

    /**
     * Set titleColor by [colorResId].
     */
    fun setTitleColorByResId(@ColorRes colorResId:Int){
        titleColor = ContextCompat.getColor(context, colorResId)
    }

    /**
     * Set titleColor by [colorInt].
     */
    fun setTitleColorByColorInt(@ColorInt colorInt:Int){
        titleColor = colorInt
    }

    /**
     * Set titleSize by [titleSize].
     *
     * If you want to set the value of titleSize,you should call
     * [com.gcode.vastswipelayout.VastSwipeMenuMgr.setTitleSizeByFloat]
     */
    internal fun setTitleSizeByFloat(@FloatRange(from = 0.0) titleSize:Float){
        this.titleSize = titleSize
    }

    /**
     * Set titleSize by [titleSizeSp].
     *
     * If you want to set the value of titleSize,you should call
     * [com.gcode.vastswipelayout.VastSwipeMenuMgr.setTitleSizeBySp]
     */
    internal fun setTitleSizeBySp(@FloatRange(from = 0.0) titleSizeSp: Float){
        this.titleSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            titleSize,
            Resources.getSystem().displayMetrics
        )
    }

    /**
     * Set iconSize by [iconSize].
     *
     * If you want to set the value of iconSize,you should call
     * [com.gcode.vastswipelayout.VastSwipeMenuMgr.setIconSizeByFloat]
     */
    internal fun setIconSizeByFloat(@FloatRange(from = 0.0) iconSize:Float){
        this.iconSize = iconSize
    }

    /**
     * Set iconSize by [iconSizeDp].
     *
     * If you want to set the value of iconSize,you should call
     * [com.gcode.vastswipelayout.VastSwipeMenuMgr.setIconSizeByDp]
     */
    internal fun setIconSizeByDp(@FloatRange(from = 0.0) iconSizeDp:Float){
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            iconSizeDp,
            Resources.getSystem().displayMetrics
        )
    }

    /**
     * Set click event by [clickEvent]
     */
    fun setClickEvent(clickEvent:((VastSwipeMenuItem, Int) -> Unit)?){
        this.clickEvent = clickEvent
    }
}