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
import android.view.View
import android.widget.FrameLayout
import com.gcode.vastswipeview.R

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

    private val defaultViewId = 0

    private var mLeftViewId = defaultViewId
    private var mContentViewId = defaultViewId
    private var mRightViewId = defaultViewId

    var mContentView: View? = null
    var mSwipeLeftMenu: VastSwipeMenuView? = null
    var mSwipeRightMenu: VastSwipeMenuView? = null

    init {
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

}
