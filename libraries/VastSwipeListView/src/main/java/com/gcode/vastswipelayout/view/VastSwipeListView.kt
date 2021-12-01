package com.gcode.vastswipelayout.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.widget.ListAdapter
import android.widget.ListView
import com.gcode.vastswipelayout.VastSwipeMenuMgr
import com.gcode.vastswipelayout.adapter.VastMergeListItemAdapter
import com.gcode.vastswipelayout.annotation.VastSwipeListViewConstant.*
import kotlin.math.abs

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/29
 */
class VastSwipeListView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : ListView(context, attrs, defStyle) {
    /**
     * When the distance(in pixels) of the finger sliding in the y-axis
     * direction is greater than [maxY],the [touchOrientation] will be
     * set to the [TOUCH_STATE_Y]
     */
    private var maxY = dp2px(5)
    /**
     * When the distance(in pixels) of the finger sliding in the x-axis
     * direction is greater than [maxX],the [touchOrientation] will be
     * set to the [TOUCH_STATE_X]
     */
    private var maxX = dp2px(2)
    /**
     * The initial x coordinate every time you touch the screen.
     */
    private var downX = 0f
    /**
     * The initial x coordinate every time you touch the screen.
     */
    private var downY = 0f
    /**
     * The direction of the finger sliding,
     * default value is [TOUCH_STATE_NONE]
     */
    private var touchOrientation = TOUCH_STATE_NONE
    /**
     * The list item position which you touch.
     */
    private var touchPosition = 0
    /**
     * Swipe list item layout.
     *
     * See the definition of the [VastSwipeListItemLayout].
     *
     * It will be set when [MotionEvent.ACTION_DOWN]
     */
    private var swipeListItemLayout: VastSwipeListItemLayout? = null
    /**
     * Lets you to define the swipe events.
     */
    private var onSwipeListener: OnSwipeListener? = null
    /**
     * Swipe menu manager.
     */
    lateinit var swipeMenuMgr: VastSwipeMenuMgr
        private set

    var listAdapter: ListAdapter? = null

    @JvmOverloads
    constructor(
        listAdapter: ListAdapter,
        swipeMenuMgr: VastSwipeMenuMgr,
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
    ) : this(context, attrs, defStyle) {
        this.listAdapter = listAdapter
        this.swipeMenuMgr = swipeMenuMgr
    }

    /**
     * Override the baseAdapter method, set the [listAdapter] and [swipeMenuMgr]
     * into the [VastMergeListItemAdapter] to get the final itemView.
     */
    override fun setAdapter(adapter: ListAdapter) {
        listAdapter = adapter
        super.setAdapter(VastMergeListItemAdapter(context, adapter, swipeMenuMgr))
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return super.onInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action != MotionEvent.ACTION_DOWN && swipeListItemLayout == null)
            return super.onTouchEvent(ev)
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {

                val oldPos = touchPosition
                downX = ev.x
                downY = ev.y
                touchOrientation = TOUCH_STATE_NONE

                //Get the position of the list item which you touch.
                touchPosition = pointToPosition(ev.x.toInt(), ev.y.toInt())

                if (touchPosition == oldPos && swipeListItemLayout != null && swipeListItemLayout!!.isOpen()) {
                    touchOrientation = TOUCH_STATE_X
                    swipeListItemLayout!!.onSwipe(ev)
                    return true
                }
                //Get the view corresponding to the position you touch.
                val view = getChildAt(touchPosition - firstVisiblePosition)
                if (swipeListItemLayout != null && swipeListItemLayout!!.isOpen()) {
                    swipeListItemLayout!!.smoothCloseMenu()
                    swipeListItemLayout = null
                    return super.onTouchEvent(ev)
                }
                if (view is VastSwipeListItemLayout) {
                    swipeListItemLayout = view
                }
                swipeListItemLayout?.onSwipe(ev)
            }
            MotionEvent.ACTION_MOVE -> {
                val dy = abs(ev.y - downY)
                val dx = abs(ev.x - downX)
                if (touchOrientation == TOUCH_STATE_X) {
                    if (swipeListItemLayout != null) {
                        swipeListItemLayout!!.onSwipe(ev)
                    }
                    selector.state = intArrayOf(0)
                    ev.action = MotionEvent.ACTION_CANCEL
                    super.onTouchEvent(ev)
                    return true
                } else if (touchOrientation == TOUCH_STATE_NONE) {
                    if (dy > maxY) {
                        touchOrientation = TOUCH_STATE_Y
                    } else if (dx > maxX) {
                        touchOrientation = TOUCH_STATE_X
                        onSwipeListener?.onSwipeStart(touchPosition)
                    }
                }
            }
            MotionEvent.ACTION_UP -> if (touchOrientation == TOUCH_STATE_X) {
                if (swipeListItemLayout != null) {
                    swipeListItemLayout!!.onSwipe(ev)
                    if (!swipeListItemLayout!!.isOpen()) {
                        touchPosition = -1
                        swipeListItemLayout = null
                    }
                }
                onSwipeListener?.onSwipeEnd(touchPosition)
                ev.action = MotionEvent.ACTION_CANCEL
                super.onTouchEvent(ev)
                return true
            }
        }
        return super.onTouchEvent(ev)
    }

    fun smoothOpenMenu(position: Int) {
        if (position in firstVisiblePosition..lastVisiblePosition) {
            val view = getChildAt(position - firstVisiblePosition)
            if (view is VastSwipeListItemLayout) {
                touchPosition = position
                if (swipeListItemLayout != null && swipeListItemLayout!!.isOpen()) {
                    swipeListItemLayout!!.smoothCloseMenu()
                }
                swipeListItemLayout = view
                swipeListItemLayout!!.smoothOpenMenu()
            }
        }
    }

    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    /**
     * Set swipe menu manager.
     */
    fun setSwipeMenuMgr(swipeMenuMgr: VastSwipeMenuMgr) {
        this.swipeMenuMgr = swipeMenuMgr
    }

    interface OnSwipeListener {
        fun onSwipeStart(position: Int)
        fun onSwipeEnd(position: Int)
    }

}