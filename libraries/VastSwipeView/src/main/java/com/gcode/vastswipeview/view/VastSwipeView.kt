package com.gcode.vastswipeview.view

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
import com.gcode.vastswipeview.VastSwipeViewMgr
import com.gcode.vastswipeview.exception.VastSwipeViewNotFound
import com.gcode.vastswipeview.exception.VastSwipeViewTypeError
import kotlin.math.abs

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/10
 */
class VastSwipeView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context!!, attrs, defStyle) {
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
     * The x coordinate you touch on the screen last time.
     */
    private var lastX = 0f

    /**
     * The area of the screen you first touch.
     */
    private var firstX = 0f
    private var firstY = 0f

    /**
     * True if you swipe the item menu,otherwise False.
     */
    private var isSlide = false

    /**
     * The item you touch on the screen.
     */
    private lateinit var flingView: VastSwipeViewItem

    /**
     * The position of the item you touch on the screen.
     */
    private var position = -1

    /**
     * Right menu view width.
     */
    private var rightMenuViewWidth = 0

    /**
     * Left menu view width.
     */
    private var leftMenuViewWidth = 0

    /**
     * Manager,you can learn more by check [VastSwipeViewMgr]
     */
    private lateinit var manager: VastSwipeViewMgr

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        val x = e.x.toInt()
        val y = e.y.toInt()
        obtainVelocity(e)
        when (e.action) {
            // The ACTION_DOWN will be consumed in the onTouchEvent of the VastSwipeViewItem.
            MotionEvent.ACTION_DOWN -> {

                lastX = x.toFloat()
                firstX = lastX
                firstY = y.toFloat()

                // Get the position of the item you touch on the screen.
                position = pointToPosition(x, y)

                if (position != INVALID_POSITION) {
                    // If flingView isInitialized,it maybe not the item you touch now,so call you
                    // should to close the flingView.Then get the item by getChildAt you touch now.
                    if (::flingView.isInitialized && flingView.scrollX != 0) {
                        flingView.scrollTo(0, 0)
                    }

                    // Get the view you touch on the screen.
                    val view =
                        getChildAt(position - (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition())

                    if (view is VastSwipeViewItem) {
                        flingView = view

                        leftMenuViewWidth = flingView.getChildAt(0).width
                        rightMenuViewWidth = flingView.getChildAt(2).width
                    } else {
                        throw VastSwipeViewNotFound("Not found the swipeListItemLayout of the position you touch.")
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
                    isSlide = true
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
        if (isSlide && position != INVALID_POSITION) {
            val x = e.x
            obtainVelocity(e)
            // Firstly,item will be swiped with your finger.
            // Then,it will do the rest of the scrolling.
            when (e.action) {
                MotionEvent.ACTION_MOVE -> {
                    // Distance moved relative to last ACTION_MOVE.
                    val dx = lastX - x
                    if (dx >= 0) {
                        //right menu open
                        if (flingView.scrollX + dx <= rightMenuViewWidth && flingView.scrollX + dx > 0
                        ) {
                            flingView.scrollBy(dx.toInt(), 0)
                        }
                    } else {
                        //left menu open
                        if (abs(flingView.scrollX + dx) <= leftMenuViewWidth) {
                            flingView.scrollBy(dx.toInt(), 0)
                        }
                    }
                    lastX = x
                }
                MotionEvent.ACTION_UP -> {

                    val scrollX = flingView.scrollX
                    velocityTracker!!.computeCurrentVelocity(1000)

                    when {
                        velocityTracker!!.xVelocity < -SNAP_VELOCITY -> {
                            // right menu open
                            Log.d(TAG,"right menu open")
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
                            Log.d(TAG,"left menu open")
                            openScroller.startScroll(
                                scrollX,
                                0,
                                -leftMenuViewWidth - scrollX,
                                0,
                                manager.menuOpenDuration
                            )
                        }
                    }

                    isSlide = false
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
            flingView.scrollTo(openScroller.currX, openScroller.currY)
            invalidate()
        }

        if (closeScroller.computeScrollOffset()) {
            flingView.scrollTo(closeScroller.currX, closeScroller.currY)
            invalidate()
        }
    }

    override fun setLayoutManager(layout: LayoutManager?) {
        when (layout) {
            null -> {
                throw VastSwipeViewTypeError("You should set the LinearLayoutManager as the LayoutManager")
            }
            !is LinearLayoutManager -> {
                throw VastSwipeViewTypeError("You should set the LinearLayoutManager as the LayoutManager")
            }
            else -> {
                super.setLayoutManager(layout)
            }
        }
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
        closeScroller.startScroll(
            flingView.scrollX,
            0,
            -flingView.scrollX,
            0,
            manager.menuCloseDuration
        )
        postInvalidate()
    }

    fun setManager(manager: VastSwipeViewMgr) {
        this.manager = manager
        openScroller = OverScroller(context, manager.openInterpolator)
        closeScroller = OverScroller(context, manager.closeInterpolator)
    }

    companion object {
        private const val TAG = "VastSwipeView"
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
