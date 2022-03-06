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

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.animation.Interpolator
import android.widget.FrameLayout
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import com.gcode.vastswiperecyclerview.VastSwipeRvMgr
import com.gcode.vastswiperecyclerview.annotation.STATE_CLOSE
import com.gcode.vastswiperecyclerview.annotation.STATE_INIT
import com.gcode.vastswiperecyclerview.annotation.SwipeMenuOrientation
import com.gcode.vastswipeview.R
import kotlin.math.abs

/**
 * @OriginalAuthor: Vast Gui @OriginalDate: @EditAuthor: Vast Gui
 * @EditDate: 2021/11/29
 */

/**
 * Vast swipe menu layout
 */
class VastSwipeMenuLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    /** The initial x coordinate every time you touch the screen. */
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
     * Detects various gestures and events using the [onSwipe] supplied
     * [MotionEvent]s.
     */
    private lateinit var gestureDetector: GestureDetectorCompat

    /**
     * Use to define the [SimpleOnGestureListener.onDown] and
     * [SimpleOnGestureListener.onFling].
     */
    private lateinit var gestureListener: GestureDetector.OnGestureListener

    /** Whether the finger is fling. */
    private var isFling = false

    /** The minimum distance of the finger fling. */
    private val minFling = dp2px(15)

    /**
     * The max velocity of this fling measured in pixels per second
     * along the x axis.
     */
    private val maxVelocityX = -dp2px(500)

    /** Base x distance. */
    private var baseX = 0

    /** The position of the item in the list. */
    var position = 0

    /** Open scroller. */
    private lateinit var openScroller: OverScroller

    /** Close scroller. */
    private lateinit var closeScroller: OverScroller

    /**
     * Close interpolator
     *
     * Get from [VastSwipeRvMgr.closeInterpolator].
     */
    private var closeInterpolator: Interpolator? = null

    /**
     * Open interpolator
     *
     * Get from [VastSwipeRvMgr.openInterpolator].
     */
    private var openInterpolator: Interpolator? = null

    /** Swipe menu manager. */
    private lateinit var manager: VastSwipeRvMgr

    /** Swipe orientation. */
    @setparam:SwipeMenuOrientation
    private var swipeOrientation: Int = STATE_INIT

    private val defaultViewId = 0

    private var mLeftViewId = defaultViewId
    private var mContentViewId = defaultViewId
    private var mRightViewId = defaultViewId

    var mContentView: View? = null
    var mSwipeLeftMenu: VastSwipeMenuView? = null
    var mSwipeRightMenu: VastSwipeMenuView? = null

    init {
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

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.VastSwipeMenuLayout)
        mLeftViewId =
            typedArray.getResourceId(R.styleable.VastSwipeMenuLayout_leftViewId, mLeftViewId)
        mContentViewId =
            typedArray.getResourceId(R.styleable.VastSwipeMenuLayout_contentViewId, mContentViewId)
        mRightViewId =
            typedArray.getResourceId(R.styleable.VastSwipeMenuLayout_rightViewId, mRightViewId)
        typedArray.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (defaultViewId != mLeftViewId) {
            mSwipeLeftMenu = findViewById(mLeftViewId)
        }
        if (defaultViewId != mRightViewId) {
            mSwipeRightMenu = findViewById(mRightViewId)
        }
        if (defaultViewId != mContentViewId) {
            mContentView = findViewById(mRightViewId)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        measureChild(getChildAt(1), widthMeasureSpec, heightMeasureSpec)
        val height = getChildAt(1).measuredHeight

        mSwipeLeftMenu?.measure(
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        )
        mContentView?.measure(
            MeasureSpec.makeMeasureSpec(measuredWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        )
        mSwipeRightMenu?.measure(
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        mSwipeLeftMenu?.layout(
            -mSwipeLeftMenu!!.measuredWidth,
            0,
            0,
            measuredHeight
        )
        mContentView?.layout(0, 0, measuredWidth, measuredHeight)
        mSwipeRightMenu?.layout(
            measuredWidth,
            0,
            measuredWidth + mSwipeRightMenu!!.measuredWidth,
            measuredHeight
        )
    }

    fun setManager(manager:VastSwipeRvMgr){
        this.manager = manager
    }

    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

}
