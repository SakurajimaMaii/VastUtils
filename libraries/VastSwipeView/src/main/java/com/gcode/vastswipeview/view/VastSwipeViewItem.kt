package com.gcode.vastswipeview.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.gcode.vastswipeview.VastSwipeViewMgr

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/29
 */

/**
 * Swipe Listview item layout
 *
 * Combining list item view and swipe menu.
 */
class VastSwipeViewItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    /**
     * List content view.
     */
    private lateinit var contentView: View

    /**
     * Left Swipe menu view.
     */
    private lateinit var leftMenuView: VastSwipeLeftMenu

    /**
     * Right Swipe menu view.
     */
    private lateinit var rightMenuView: VastSwipeRightMenu

    /**
     * The position of the item in the list.
     */
    private var position = 0

    /**
     * Swipe menu manager.
     */
    private lateinit var manager: VastSwipeViewMgr

    constructor(
        contentView: View,
        manager: VastSwipeViewMgr
    ) : this(contentView.context, null, 0) {
        this.contentView = contentView
        this.leftMenuView = VastSwipeLeftMenu(manager)
        this.rightMenuView = VastSwipeRightMenu(manager)
        this.manager = manager
        init()
    }

    fun setPosition(position: Int) {
        this.position = position
        rightMenuView.setPosition(position)
    }

    @SuppressLint("ResourceType")
    private fun init() {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        contentView.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT
        )
        leftMenuView.layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.MATCH_PARENT
        )
        rightMenuView.layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.MATCH_PARENT
        )
        addView(leftMenuView)
        addView(contentView)
        addView(rightMenuView)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                manager.eventListener?.eventDownListener(position, event)
            }
            MotionEvent.ACTION_MOVE -> {
                manager.eventListener?.eventMoveListener(position, event)
            }
            MotionEvent.ACTION_UP -> {
                manager.eventListener?.eventDownListener(position, event)
            }
            MotionEvent.ACTION_CANCEL -> {
                manager.eventListener?.eventCancelListener(position, event)
            }
        }
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        leftMenuView.measure(
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            MeasureSpec.makeMeasureSpec(measuredHeight, MeasureSpec.EXACTLY)
        )
        rightMenuView.measure(
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            MeasureSpec.makeMeasureSpec(measuredHeight, MeasureSpec.EXACTLY)
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (changed) {
            leftMenuView.layout(
                -leftMenuView.measuredWidth, 0, 0, leftMenuView.measuredHeight
            )
            contentView.layout(
                0, 0, measuredWidth, contentView.measuredHeight
            )
            rightMenuView.layout(
                measuredWidth,
                0,
                measuredWidth + rightMenuView.measuredWidth,
                rightMenuView.measuredHeight
            )
        }
    }

}
