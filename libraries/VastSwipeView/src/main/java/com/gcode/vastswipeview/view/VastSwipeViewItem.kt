package com.gcode.vastswipeview.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.IntRange
import com.gcode.vastswipeview.VastSwipeViewMgr
import com.gcode.vastswipeview.exception.VastSwipeViewNotInit

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

    companion object{
        const val INVALID_POSITION = -1
    }

    private lateinit var manager: VastSwipeViewMgr

    private var position: Int = INVALID_POSITION

    fun setManager(manager: VastSwipeViewMgr){
        this.manager = manager
    }

    fun setPosition(@IntRange(from = 0) position: Int){
        this.position = position
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(position == INVALID_POSITION){
            throw VastSwipeViewNotInit("You haven't init the position.")
        }
        if(!::manager.isInitialized){
            throw VastSwipeViewNotInit("You haven't init the manager.",kotlin.UninitializedPropertyAccessException())
        }
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
        val width = 0 // The width of root view.
        val height = 0 // The height of root view.
    }
}