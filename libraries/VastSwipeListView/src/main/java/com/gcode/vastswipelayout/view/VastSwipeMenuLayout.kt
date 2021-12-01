package com.gcode.vastswipelayout.view

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setMargins
import com.gcode.vastswipelayout.R
import com.gcode.vastswipelayout.VastSwipeMenuMgr
import com.gcode.vastswipelayout.annotation.VastSwipeListViewConstant.*
import com.gcode.vastswipelayout.model.VastSwipeMenuItem
import java.util.ArrayList


/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/29
 */

/**
 * Vast slide layout
 *
 * Used to show the swipe menu
 */
open class VastSwipeMenuLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr:Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    constructor(swipeMenuMgr: VastSwipeMenuMgr,items:ArrayList<VastSwipeMenuItem>) : this(swipeMenuMgr.context,null,0) {
        this.swipeMenuMgr = swipeMenuMgr
        this.items = items
        for(item in items){
            addItem(item)
        }
    }

    private lateinit var swipeMenuMgr: VastSwipeMenuMgr

    private lateinit var items:ArrayList<VastSwipeMenuItem>

    /**
     * The swipe menu position in the list
     */
    var position:Int = -1
        private set

    fun setPosition(position:Int){
        this.position = position
    }

    private fun createIcon(item: VastSwipeMenuItem): ImageView {
        return ImageView(context).apply {
            setImageDrawable(item.icon)
            layoutParams = LayoutParams(swipeMenuMgr.iconSize.toInt(),swipeMenuMgr.iconSize.toInt()).apply {
                setMargins(10)
            }
        }
    }

    private fun createTitle(item: VastSwipeMenuItem): TextView {
        return TextView(context).apply {
            text = item.title
            gravity = Gravity.CENTER
            textSize = swipeMenuMgr.titleSize
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                setMargins(10)
            }
            setTextColor(item.titleColor)
        }
    }

    private fun addItem(item: VastSwipeMenuItem) {
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
        val parent = LinearLayout(context)
        parent.apply {
            minimumWidth = context.resources.getDimension(R.dimen.default_menu_item_width_size).toInt()
            gravity = Gravity.CENTER
            orientation = VERTICAL
            layoutParams = params
            background = item.background
            setOnClickListener {
                item.clickEvent?.let { it1 -> it1(item,position) }
            }
            when(swipeMenuMgr.swipeMenuContentStyle){
                ONLY_ICON->{
                    if (item.icon != null) {
                        parent.addView(createIcon(item))
                    }
                }
                ONLY_TITLE->{
                    if (!TextUtils.isEmpty(item.title)) {
                        parent.addView(createTitle(item))
                    }
                }
                ICON_TITLE->{
                    if (item.icon != null) {
                        parent.addView(createIcon(item))
                    }

                    if (!TextUtils.isEmpty(item.title)) {
                        parent.addView(createTitle(item))
                    }
                }
            }
        }

        addView(parent)
    }

}

class VastSwipeLeftMenu: VastSwipeMenuLayout{
    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr:Int = 0):super(context, attrs, defStyleAttr)

    constructor(vastSwipeMenuMgr: VastSwipeMenuMgr):super(vastSwipeMenuMgr,vastSwipeMenuMgr.leftMenuItems)
}

class VastSwipeRightMenu: VastSwipeMenuLayout{
    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr:Int = 0):super(context, attrs, defStyleAttr)

    constructor(vastSwipeMenuMgr: VastSwipeMenuMgr):super(vastSwipeMenuMgr,vastSwipeMenuMgr.rightMenuItems)
}