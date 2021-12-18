package com.gcode.vastswipelistview.model

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.gcode.vastswipelistview.R

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
 * @param clickEvent default value is null
 */
class VastSwipeMenuItem @JvmOverloads constructor(
    private val context: Context,
    title: String = context.resources.getString(R.string.default_slide_item_title),
    icon: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_null),
    background: Drawable? = ContextCompat.getDrawable(context, R.drawable.default_menu_item_background),
    titleColor: Int = ContextCompat.getColor(context, R.color.default_menu_item_title_color),
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
     * Click event
     *
     * @param VastSwipeMenuItem The menu item.
     * @param Int Index of the VastSwipeListItemLayout in the list.
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
     * Set iconSize by [iconSizeDp].
     *
     * If you want to set the value of iconSize,you should call
     * [com.gcode.vastswipelistview.VastSwipeMenuMgr.setIconSizeByDp]
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