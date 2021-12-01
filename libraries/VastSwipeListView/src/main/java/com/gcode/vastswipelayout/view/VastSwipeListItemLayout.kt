package com.gcode.vastswipelayout.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
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
import com.gcode.vastswipelayout.annotation.VastSwipeListViewConstant.*

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
     * List content view
     */
    private lateinit var contentView: View
    /**
     * Swipe menu view
     */
    private lateinit var menuView: VastSwipeMenuLayout
    /**
     * The initial x coordinate every time you touch the screen.
     */
    private var downX = 0
    /**
     * Swipe menu state.
     *
     * @see STATE_OPEN
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

    constructor(
        contentView: View,
        menuView: VastSwipeMenuLayout,
        swipeMenuMgr: VastSwipeMenuMgr
    ) : this(contentView.context, null, 0) {
        this.closeInterpolator = swipeMenuMgr.closeInterpolator
        this.openInterpolator = swipeMenuMgr.openInterpolator
        this.contentView = contentView
        this.menuView = menuView
        this.swipeMenuMgr = swipeMenuMgr
        this.menuView.setLayout(this)
        init()
    }

    fun setPosition(position: Int) {
        this.position = position
        menuView.setPosition(position)
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
                if (e1.x - e2.x > minFling && velocityX < maxVelocityX) {
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
        menuView.layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.MATCH_PARENT
        )
        addView(contentView)
        addView(menuView)
    }

    /**
     * Get the [event] from the outermost ListView.
     */
    fun onSwipe(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x.toInt()
                isFling = false
            }
            MotionEvent.ACTION_MOVE -> {
                var dis = (downX - event.x).toInt()
                if (state == STATE_OPEN) {
                    dis += menuView.width
                }
                swipe(dis)
            }
            MotionEvent.ACTION_UP -> if (isFling || downX - event.x > menuView.width / 2) {
                // open
                smoothOpenMenu()
            } else {
                // close
                smoothCloseMenu()
                return false
            }
        }
        return true
    }

    /**
     * Get swipe menu state
     *
     * `true` if swipe menu is open, `false` otherwise.
     */
    fun isOpen() = state == STATE_OPEN

    /**
     * Swipe
     *
     * @param dis [dis] is calculated based on the
     *            distance of the finger sliding
     */
    private fun swipe(dis: Int) {
        var distance = dis
        if (dis > menuView.width) {
            distance = menuView.width
        }
        if (dis < 0) {
            distance = 0
        }
        contentView.layout(-distance, contentView.top, contentView.width - distance, measuredHeight)
        menuView.layout(
            contentView.width - distance,
            menuView.top,
            contentView.width + menuView.width - distance,
            menuView.bottom
        )
    }

    override fun computeScroll() {
        if (state == STATE_OPEN) {
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

    fun smoothCloseMenu() {
        state = STATE_CLOSE
        baseX = -contentView.left
        closeScroller.startScroll(0, 0, baseX, 0, swipeMenuMgr.swipeMenuCloseDuration)
        postInvalidate()
    }

    fun smoothOpenMenu() {
        state = STATE_OPEN
        openScroller.startScroll(-contentView.left, 0, menuView.width, 0, swipeMenuMgr.swipeMenuOpenDuration)
        postInvalidate()
    }

    fun closeMenu() {
        if (closeScroller.computeScrollOffset()) {
            closeScroller.abortAnimation()
        }
        if (state == STATE_OPEN) {
            state = STATE_CLOSE
            swipe(0)
        }
    }

    fun openMenu() {
        if (state == STATE_CLOSE) {
            state = STATE_OPEN
            swipe(menuView.width)
        }
    }

    fun getContentView(): View {
        return contentView
    }

    fun getMenuView(): VastSwipeMenuLayout {
        return menuView
    }

    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        menuView.measure(
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            MeasureSpec.makeMeasureSpec(measuredHeight, MeasureSpec.EXACTLY)
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        contentView.layout(0, 0, measuredWidth, contentView.measuredHeight)
        menuView.layout(
            measuredWidth,
            0,
            measuredWidth + menuView.measuredWidth,
            contentView.measuredHeight
        )
    }

    fun setMenuHeight(measuredHeight: Int) {
        val params = menuView.layoutParams as LayoutParams
        if (params.height != measuredHeight) {
            params.height = measuredHeight
            menuView.layoutParams = menuView.layoutParams
        }
    }
}
