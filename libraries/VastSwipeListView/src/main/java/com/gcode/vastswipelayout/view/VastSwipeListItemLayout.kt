package com.gcode.vastswipelayout.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.animation.Interpolator
import android.widget.LinearLayout
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import com.gcode.vastswipelayout.VastSwipeMenuMgr
import com.gcode.vastswipelayout.annotation.VastSwipeListViewConstant
import com.gcode.vastswipelayout.annotation.VastSwipeListViewConstant.*
import kotlin.math.abs

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
class VastSwipeListItemLayout @JvmOverloads constructor(
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
     * The initial x coordinate every time you touch the screen.
     */
    private var downX = 0

    /**
     * Swipe menu state.
     *
     * @see STATE_LEFT_OPEN
     * @see STATE_RIGHT_OPEN
     * @see STATE_CLOSE
     */
    private var state = STATE_CLOSE

    /**
     * Detects various gestures and events using the [onSwipe] supplied [MotionEvent]s.
     */
    private lateinit var gestureDetector: GestureDetectorCompat

    /**
     * Use to define the [SimpleOnGestureListener.onDown] and
     * [SimpleOnGestureListener.onFling]
     */
    private lateinit var gestureListener: GestureDetector.OnGestureListener

    /**
     * Whether the finger is fling.
     */
    private var isFling = false

    /**
     * The minimum distance of the finger fling.
     */
    private val minFling = dp2px(15)

    /**
     * The max velocity of this fling measured in pixels per second along the x axis.
     */
    private val maxVelocityX = -dp2px(500)

    /**
     * Base x distance
     */
    private var baseX = 0

    /**
     * The position of the item in the list.
     *
     * The value is set by [com.gcode.vastswipelayout.adapter.VastMergeListItemAdapter]
     */
    var position = 0
        private set

    /**
     * Open scroller
     */
    private lateinit var openScroller: OverScroller

    /**
     * Close scroller
     */
    private lateinit var closeScroller: OverScroller

    /**
     * Close interpolator
     *
     * Get from [VastSwipeMenuMgr.closeInterpolator].
     */
    private var closeInterpolator: Interpolator? = null

    /**
     * Open interpolator
     *
     * Get from [VastSwipeMenuMgr.openInterpolator].
     */
    private var openInterpolator: Interpolator? = null

    /**
     * Swipe menu manager.
     */
    private lateinit var swipeMenuMgr: VastSwipeMenuMgr

    /**
     * Swipe orientation
     */
    @setparam:VastSwipeListViewConstant.SwipeMenuOrientation
    private var swipeOrientation: Int = SWIPE_NONE

    constructor(
        contentView: View,
        leftMenu: VastSwipeLeftMenu,
        rightMenu: VastSwipeRightMenu,
        swipeMenuMgr: VastSwipeMenuMgr
    ) : this(contentView.context, null, 0) {
        this.closeInterpolator = swipeMenuMgr.closeInterpolator
        this.openInterpolator = swipeMenuMgr.openInterpolator
        this.contentView = contentView
        this.leftMenuView = leftMenu
        this.rightMenuView = rightMenu
        this.swipeMenuMgr = swipeMenuMgr
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
        gestureListener = object : SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent): Boolean {
                isFling = false
                return true
            }

            override fun onFling(
                e1: MotionEvent, e2: MotionEvent,
                velocityX: Float, velocityY: Float
            ): Boolean {
                if (abs(e1.x - e2.x) > minFling && velocityX < maxVelocityX) {
                    isFling = true
                }
                return super.onFling(e1, e2, velocityX, velocityY)
            }
        }
        gestureDetector = GestureDetectorCompat(
            context,
            gestureListener
        )
        closeScroller = OverScroller(
            context,
            closeInterpolator
        )
        openScroller = OverScroller(
            context,
            openInterpolator
        )
        val contentParams = LayoutParams(
            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT
        )
        contentView.layoutParams = contentParams
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

    /**
     * Get the [event] from the outermost ListView.
     */
    fun onSwipe(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x.toInt()
                Log.d("vasttest","${event.x} ${event.rawX}")
                isFling = false
            }
            MotionEvent.ACTION_MOVE -> {
                var dis = (downX - event.x).toInt()
                swipeOrientation = if (dis >= 0) SWIPE_LEFT else SWIPE_RIGHT
                if (state == STATE_LEFT_OPEN || state == STATE_RIGHT_OPEN) {
                    smoothCloseMenu(state)
                    when (swipeOrientation) {
                        SWIPE_LEFT ->
                            dis += rightMenuView.width
                        SWIPE_RIGHT ->
                            dis -= leftMenuView.width
                    }
                }
                swipe(dis)
            }
            MotionEvent.ACTION_UP -> {
                Log.d("Hello","ok the value is ${downX - event.x}")
                when (swipeOrientation) {
                    SWIPE_RIGHT -> {
                        if (isFling || abs(downX - event.x) > leftMenuView.width / 2) {
                            // open
                            smoothOpenMenu(SWIPE_RIGHT)
                        } else {
                            // close
                            smoothCloseMenu(SWIPE_RIGHT)
                            return false
                        }
                    }
                    SWIPE_LEFT -> {
                        if (isFling || downX - event.x > rightMenuView.width / 2) {
                            // open
                            smoothOpenMenu(SWIPE_LEFT)
                        } else {
                            // close
                            smoothCloseMenu(SWIPE_LEFT)
                            return false
                        }
                    }
                }
            }
        }
        return true
    }

    /**
     * Get swipe menu state
     */
    fun getSwipeMenuState() = state

    /**
     * Swipe
     *
     * @param dis [dis] is calculated based on the
     *            distance of the finger sliding
     */
    private fun swipe(dis: Int) {
        var distance = dis
        when (swipeOrientation) {
            SWIPE_LEFT -> {
                if (dis > rightMenuView.width) {
                    distance = rightMenuView.width
                }
                if (dis < 0) {
                    distance = 0
                }
            }
            SWIPE_RIGHT -> {
                if (dis < -leftMenuView.width) {
                    distance = -leftMenuView.width
                }
                if (dis > 0) {
                    distance = 0
                }
            }
        }
        leftMenuView.layout(
            -leftMenuView.width - distance,
            leftMenuView.top,
            -distance,
            leftMenuView.bottom
        )
        contentView.layout(
            -distance,
            contentView.top,
            contentView.width - distance,
            measuredHeight
        )
        rightMenuView.layout(
            contentView.width - distance,
            rightMenuView.top,
            contentView.width + rightMenuView.width - distance,
            measuredHeight
        )
    }

    override fun computeScroll() {
        if (state == STATE_LEFT_OPEN || state == STATE_RIGHT_OPEN) {
            if (openScroller.computeScrollOffset()) {
                swipe(openScroller.currX)
                postInvalidate()
            }
        } else {
            if (closeScroller.computeScrollOffset()) {
                swipe(baseX - closeScroller.currX)
                postInvalidate()
            }
        }
    }

    fun smoothCloseMenu(swipeMenuOrientation: Int) {
        state = STATE_CLOSE
        when (swipeMenuOrientation) {
            SWIPE_LEFT -> {
                baseX = -contentView.left
                closeScroller.startScroll(0, 0, baseX, 0, swipeMenuMgr.swipeMenuCloseDuration)
            }
            SWIPE_RIGHT -> {
                baseX = contentView.left
                closeScroller.startScroll(0, 0, baseX, 0, swipeMenuMgr.swipeMenuCloseDuration)
            }
        }
        postInvalidate()
    }

    private fun smoothOpenMenu(swipeMenuOrientation: Int) {
        when (swipeMenuOrientation) {
            SWIPE_LEFT -> {
                state = STATE_RIGHT_OPEN
                Log.i("Hello", "${contentView.left}")
                openScroller.startScroll(
                    -contentView.left,
                    0,
                    rightMenuView.width,
                    0,
                    swipeMenuMgr.swipeMenuOpenDuration
                )
            }
            SWIPE_RIGHT -> {
                state = STATE_LEFT_OPEN
                Log.i("Hello", "AAA ${contentView.left}")
                openScroller.startScroll(
                    -contentView.left,
                    0,
                    -leftMenuView.width,
                    0,
                    swipeMenuMgr.swipeMenuOpenDuration
                )
            }
        }
        postInvalidate()
    }

    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
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
            Log.d("vasttest","Hello")
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
