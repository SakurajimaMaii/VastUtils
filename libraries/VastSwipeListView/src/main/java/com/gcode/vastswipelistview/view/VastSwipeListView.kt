package com.gcode.vastswipelistview.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ListAdapter
import android.widget.ListView
import com.gcode.vastswipelistview.VastSwipeMenuMgr
import com.gcode.vastswipelistview.adapter.VastMergeListItemAdapter
import com.gcode.vastswipelistview.exception.ViewNotFound

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
     * The initial x coordinate every time you touch the screen.
     */
    private var downX = 0f

    /**
     * The initial x coordinate every time you touch the screen.
     */
    private var downY = 0f

    /**
     * The list item position which you touch.Default value is -1
     */
    private var touchPosition = -1

    /**
     * Swipe list item layout.
     *
     * See the definition of the [VastSwipeListItemLayout].
     *
     * It will be set when [MotionEvent.ACTION_DOWN]
     */
    private lateinit var swipeListItemLayout: VastSwipeListItemLayout

    /**
     * Swipe menu manager.
     */
    lateinit var swipeMenuMgr: VastSwipeMenuMgr
        private set

    var listAdapter: ListAdapter? = null
        private set

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
        super.onTouchEvent(ev)
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                //Get the x and y you touch.
                val oldPos = touchPosition
                downX = ev.x
                downY = ev.y

                //Get the position of the list item which you touch.
                touchPosition = pointToPosition(ev.x.toInt(), ev.y.toInt())

                swipeMenuMgr.eventListener?.eventDownListener(touchPosition,ev)

                //Get the position of the swipeListItemLayout
                return when (touchPosition) {
                    oldPos -> {
                        swipeListItemLayout.onSwipe(ev)
                        true
                    }
                    else -> {
                        /**
                         * If [swipeListItemLayout] isInitialized,it maybe not the item you touch now,so
                         * call [VastSwipeListItemLayout.onSwipe] to close the [swipeListItemLayout].Then
                         * get the item by [getChildAt] you touch now.
                         */
                        if(::swipeListItemLayout.isInitialized){
                            swipeListItemLayout.onSwipe(ev)
                        }

                        //Get the view corresponding to the position you touch.
                        val view = getChildAt(touchPosition - firstVisiblePosition)

                        if (view is VastSwipeListItemLayout) {
                            swipeListItemLayout = view
                            swipeListItemLayout.onSwipe(ev)
                        }else{
                            throw ViewNotFound("Not found the swipeListItemLayout of the position you touch.")
                        }
                        true
                    }
                }
            }
            MotionEvent.ACTION_MOVE -> {
                swipeListItemLayout.onSwipe(ev)
                swipeMenuMgr.eventListener?.eventMoveListener(touchPosition,ev)
                ev.action = MotionEvent.ACTION_CANCEL
                return true
            }
            MotionEvent.ACTION_UP -> {
                swipeListItemLayout.onSwipe(ev)
                swipeMenuMgr.eventListener?.eventUpListener(touchPosition,ev)
                ev.action = MotionEvent.ACTION_CANCEL
                return true
            }
        }
        return super.onTouchEvent(ev)
    }

    /**
     * Set swipe menu manager.
     */
    fun setSwipeMenuMgr(swipeMenuMgr: VastSwipeMenuMgr) {
        this.swipeMenuMgr = swipeMenuMgr
    }
}