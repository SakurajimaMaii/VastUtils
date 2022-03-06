/*
 * MIT License
 *
 * Copyright (c) 2021 码上夏雨
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.gcode.vastswiperecyclerview.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.widget.OverScroller
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastswiperecyclerview.VastSwipeRvMgr
import com.gcode.vastswiperecyclerview.adapter.VastSwipeWrapperAdapter
import kotlin.math.abs

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/10
 */
class VastSwipeRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    /**
     * velocity tracker.
     */
    private var velocityTracker: VelocityTracker? = null

    /**
     * The minimum distance considered to be swiping
     * (generally provided by the system).
     */
    private val touchSlop: Int

    /**
     * The scope of recyclerview item view.
     */
    private var touchFrame: Rect? = null

    private lateinit var openScroller: OverScroller

    private lateinit var closeScroller: OverScroller

    /**
     * The x coordinate of the start of each swipe.
     *
     * The [startX] will first get the starting x coordinate of each swipe in
     * the [onInterceptTouchEvent] method, and then continue to update it in
     * the [onTouchEvent] when detect the action is [MotionEvent.ACTION_MOVE].
     */
    private var startX = 0f

    /**
     * The x coordinate of the screen you first touch.
     *
     * Used to determine which item in the [VastSwipeRecyclerView]
     * you are touching at this time.
     */
    private var firstX = 0f

    /**
     * The y coordinate of the screen you first touch.
     *
     * Used to determine which item in the [VastSwipeRecyclerView]
     * you are touching at this time.
     */
    private var firstY = 0f

    /**
     * True if you swipe the item menu,otherwise False.
     */
    private var isSwipe = false

    /**
     * The item you touch on the screen.
     */
    private lateinit var swipeView: VastSwipeMenuLayout

    /**
     * The position of which item you touch in
     * the [VastSwipeRecyclerView] at this time.
     */
    private var position = -1

    /**
     * Right swipe menu view width.
     */
    private var rightMenuViewWidth = 0

    /**
     * Left swipe menu view width.
     */
    private var leftMenuViewWidth = 0

    /**
     * Manager,you can learn more by check [VastSwipeRvMgr]
     */
    private lateinit var manager: VastSwipeRvMgr

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        val x = e.x.toInt()
        val y = e.y.toInt()
        obtainVelocity(e)
        when (e.action) {
            // The ACTION_DOWN will be consumed in the onTouchEvent of the VastSwipeViewItem.
            MotionEvent.ACTION_DOWN -> {

                startX = x.toFloat()
                firstX = startX
                firstY = y.toFloat()

                // Get the position of the item you touch on the screen.
                position = pointToPosition(x, y)

                if (position != INVALID_POSITION) {
                    // If flingView isInitialized,it maybe not the item you touch now,so call you
                    // should to close the flingView.Then get the item by getChildAt you touch now.
                    if (::swipeView.isInitialized && swipeView.scrollX != 0) {
                        Log.d("test","${swipeView.scrollX}")
                        closeScroller.startScroll(
                            0,
                            0,
                            -swipeView.scrollX,
                            0,
                            manager.menuCloseDuration
                        )
                    }

                    // Get the view you touch on the screen.
                    val view =
                        getChildAt(position - (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition())

                    if (null != view) {
                        swipeView = view as VastSwipeMenuLayout

                        leftMenuViewWidth = swipeView.mSwipeLeftMenu?.width?: 0
                        rightMenuViewWidth = swipeView.mSwipeRightMenu?.width?: 0

                    } else {
                        throw RuntimeException("The item of the position you touch is not the type of VastSwipeMenuLayout.")
                    }
                }
            }
            // The ACTION_MOVE will be intercepted if satisfying one of them is considered to be sideslip.
            // Or it will be be consumed in the onTouchEvent of the VastSwipeViewItem.
            MotionEvent.ACTION_MOVE -> {
                velocityTracker!!.computeCurrentVelocity(1000)
                // There are two judgments here, satisfying one of them is considered to be sideslip:
                // 1.If the speed in the x coordinate is greater than the speed in the y coordinate, and is greater than the minimum speed limit;
                // 2.If the sideslip distance in the x coordinate is greater than the sliding distance in the y coordinate, and the x coordinate
                // reaches the minimum sliding distance;
                val xVelocity = velocityTracker!!.xVelocity
                val yVelocity = velocityTracker!!.yVelocity

                if (
                    (abs(xVelocity) > SNAP_VELOCITY && abs(xVelocity) > abs(yVelocity)) ||
                    (abs(x - firstX) >= touchSlop && abs(x - firstX) > abs(y - firstY))
                ) {
                    isSwipe = true
                    return true
                }
            }
            MotionEvent.ACTION_UP -> {
                releaseVelocity()
            }
        }
        return super.onInterceptTouchEvent(e)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(e: MotionEvent): Boolean {
        if (isSwipe && position != INVALID_POSITION) {
            val x = e.x
            obtainVelocity(e)
            // Firstly,item will be swiped with your finger.
            // Then,it will do the rest of the scrolling.
            when (e.action) {
                MotionEvent.ACTION_MOVE -> {
                    // Distance moved relative to last ACTION_MOVE.
                    val dx = startX - x
                    if (dx >= 0) {
                        //right menu open
                        if (swipeView.scrollX + dx <= rightMenuViewWidth && swipeView.scrollX + dx > 0
                        ) {
                            swipeView.scrollBy(dx.toInt(), 0)
                        }
                    } else {
                        //left menu open
                        if (abs(swipeView.scrollX + dx) <= leftMenuViewWidth) {
                            swipeView.scrollBy(dx.toInt(), 0)
                        }
                    }
                    startX = x
                }
                MotionEvent.ACTION_UP -> {

                    val scrollX = swipeView.scrollX
                    velocityTracker!!.computeCurrentVelocity(1000)

                    when {
                        velocityTracker!!.xVelocity < -SNAP_VELOCITY -> {
                            // right menu open
                            openScroller.startScroll(
                                scrollX,
                                0,
                                rightMenuViewWidth - scrollX,
                                0,
                                manager.menuOpenDuration
                            )
                        }
                        velocityTracker!!.xVelocity > SNAP_VELOCITY -> {
                            // left menu open
                            openScroller.startScroll(
                                scrollX,
                                0,
                                -leftMenuViewWidth - scrollX,
                                0,
                                manager.menuOpenDuration
                            )
                        }
                    }

                    isSwipe = false
                    position = INVALID_POSITION
                    // The reason why it is called here is because if it is intercepted before,
                    // ACTION_UP will not be executed, and the tracking needs to be released here.
                    releaseVelocity()
                }
            }
            return true
        } else {
            smoothCloseMenu()
            releaseVelocity()
        }
        return super.onTouchEvent(e)
    }

    override fun computeScroll() {
        if (openScroller.computeScrollOffset()) {
            swipeView.scrollTo(openScroller.currX, openScroller.currY)
            invalidate()
        }

        if (closeScroller.computeScrollOffset()) {
            swipeView.scrollTo(closeScroller.currX, closeScroller.currY)
            invalidate()
        }
    }

    override fun setLayoutManager(layout: LayoutManager?) {
        when (layout) {
            null -> {
                throw RuntimeException("You should set the LinearLayoutManager as the LayoutManager")
            }
            !is LinearLayoutManager -> {
                throw RuntimeException("You should set the LinearLayoutManager as the LayoutManager")
            }
            else -> {
                super.setLayoutManager(layout)
            }
        }
    }

    override fun setAdapter(adapter: Adapter<ViewHolder>?) {

        var wrapperAdapter:VastSwipeWrapperAdapter? = null

        if(null != adapter){
            wrapperAdapter = VastSwipeWrapperAdapter(manager, adapter)
        }

        super.setAdapter(wrapperAdapter)
    }

    private fun releaseVelocity() {
        if (velocityTracker != null) {
            velocityTracker!!.clear()
            velocityTracker!!.recycle()
            velocityTracker = null
        }
    }

    private fun obtainVelocity(event: MotionEvent) {
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain()
        }
        velocityTracker!!.addMovement(event)
    }

    private fun pointToPosition(x: Int, y: Int): Int {
        val firstPosition = (layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
        var frame = touchFrame
        if (frame == null) {
            touchFrame = Rect()
            frame = touchFrame
        }
        val count = childCount
        for (i in count - 1 downTo 0) {
            val child = getChildAt(i)
            if (child.visibility == VISIBLE) {
                child.getHitRect(frame)
                if (frame!!.contains(x, y)) {
                    return firstPosition + i
                }
            }
        }
        return INVALID_POSITION
    }

    private fun smoothCloseMenu() {
        if (::swipeView.isInitialized) {
            closeScroller.startScroll(
                swipeView.scrollX,
                0,
                -swipeView.scrollX,
                0,
                manager.menuCloseDuration
            )
            postInvalidate()
        }
    }

    fun setManager(manager: VastSwipeRvMgr) {
        this.manager = manager
        openScroller = OverScroller(context, manager.openInterpolator)
        closeScroller = OverScroller(context, manager.closeInterpolator)
    }



    companion object {
        /**
         * The point you touch on the screen is not within the scope of the RecyclerView's subviews.
         */
        private const val INVALID_POSITION = -1

        /**
         * The minimum swipe velocity.
         */
        private const val SNAP_VELOCITY = 600
    }

    init {
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }
}
