/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gcode.vasttools.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.FloatRange
import com.gcode.vasttools.R
import com.gcode.vasttools.utils.LogUtils
import com.gcode.vasttools.utils.colorHex2Int
import kotlin.math.cos
import kotlin.math.sin

// Author: Vast Gui 
// Email: guihy2019@gmail.com
// Date: 2022/4/17 19:55
// Description:
// Documentation:

/**
 * @since 0.0.8
 */
class DownloadCircleView : View {

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initAttr(attrs, context)
        initPaint()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttr(attrs, context)
        initPaint()
    }

    constructor(context: Context) : super(context) {
        initAttr(null, context)
        initPaint()
    }

    private lateinit var mProgressBackgroundPaint: Paint
    private lateinit var mProgressPaint: Paint
    private lateinit var mTextCirclePaint: Paint
    private lateinit var mTextPaint: Paint

    /**
     * The rect containing the progress arc.
     *
     * @since 0.0.8
     */
    private val oval = RectF()

    /**
     * Progress text size.
     *
     * @since 0.0.8
     */
    var progressTextSize = 0f
        private set

    /**
     * Radius of the download circle.
     *
     * @since 0.0.8
     */
    var circleRadius = 0f
        private set

    /**
     * Width of the download circle progress.
     *
     * @since 0.0.8
     */
    var progressBackgroundWidth = 0f
        private set

    /**
     * Download progress.Range from 0.0 to 1.0.
     *
     * @since 0.0.8
     */
    var progress = 0f
        private set

    /**
     * Progress background color int.
     *
     * @since 0.0.8
     */
    var progressBackgroundColorInt: Int = Color.GRAY
        private set

    /**
     * Progress color int.
     *
     * @since 0.0.8
     */
    var progressColorInt: Int = colorHex2Int("#3B4463")
        private set

    /**
     * Progress end color int.
     *
     * @since 0.0.8
     */
    var progressEndColorInt: Int = colorHex2Int("#f0932b")
        private set

    /**
     * Progress text color int.
     *
     * @since 0.0.8
     */
    var progressTextColorInt: Int = Color.WHITE
        private set

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val width = if (widthMode == MeasureSpec.EXACTLY) {
            widthSize
        } else {
            (2 * circleRadius + progressBackgroundWidth).toInt()
        }
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val height = if (heightMode == MeasureSpec.EXACTLY) {
            heightSize
        } else {
            (2 * circleRadius + progressBackgroundWidth).toInt()
        }
        setMeasuredDimension(width, height)
    }

    private fun initAttr(attrs: AttributeSet?, context: Context) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.DownloadCircleView)
        circleRadius = ta.getDimension(R.styleable.DownloadCircleView_circle_radius, 0f)
        progressBackgroundWidth =
            ta.getDimension(R.styleable.DownloadCircleView_progress_background_width, 0f)
        progressTextSize = ta.getDimension(R.styleable.DownloadCircleView_progress_text_size, 0f)
        progressBackgroundColorInt =
            ta.getColor(R.styleable.DownloadCircleView_progress_background_color, Color.GRAY)
        progressColorInt =
            ta.getColor(R.styleable.DownloadCircleView_progress_color, colorHex2Int("#3B4463"))
        progressEndColorInt =
            ta.getColor(R.styleable.DownloadCircleView_progress_end_color, colorHex2Int("#f0932b"))
        progressTextColorInt =
            ta.getColor(R.styleable.DownloadCircleView_progress_text_color, Color.WHITE)
        LogUtils.i("test", "$circleRadius and $progressBackgroundWidth")
        ta.recycle()
    }

    /**
     * init paints.
     *
     * @since 0.0.8
     */
    private fun initPaint() {
        mProgressBackgroundPaint = Paint().apply {
            strokeWidth = progressBackgroundWidth
            color = progressBackgroundColorInt
            isAntiAlias = true
            style = Paint.Style.STROKE
        }
        mProgressPaint = Paint().apply {
            strokeWidth = progressBackgroundWidth
            color = progressColorInt
            isAntiAlias = true
            style = Paint.Style.STROKE
        }
        mTextCirclePaint = Paint().apply {
            color = progressEndColorInt
            isAntiAlias = true
            style = Paint.Style.FILL
        }
        mTextPaint = Paint().apply {
            textSize = progressTextSize
            color = progressTextColorInt
            isAntiAlias = true
            style = Paint.Style.FILL
        }
    }

    /**
     * Set download progress.
     *
     * @param progress currently completed progress,range from 0.0 to 1.0.
     *
     * @since 0.0.8
     */
    fun setProgress(@FloatRange(from = 0.0, to = 1.0) progress: Float) {
        this.progress = progress
        invalidate()
    }

    /**
     * Set download progress.
     *
     * @param currentProgress currently completed progress.
     * @param totalProgress total progress.
     * @throws RuntimeException when currentProgress is greater than the totalProgress.
     *
     * @since 0.0.8
     */
    @Throws(RuntimeException::class)
    fun setProgress(
        @FloatRange(from = 0.0) currentProgress: Float,
        @FloatRange(from = 0.0) totalProgress: Float
    ) {
        if (currentProgress > totalProgress) {
            throw RuntimeException("currentProgress must less then totalProgress!")
        }
        progress = currentProgress / totalProgress
        invalidate()
    }

    /**
     * Reset progress.
     *
     * @since 0.0.8
     */
    fun resetProgress() {
        progress = 0f
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw progress background.
        val circlePoint = (width / 2).toFloat()
        canvas.drawCircle(
            circlePoint,
            circlePoint,
            circlePoint - progressBackgroundWidth / 2,
            mProgressBackgroundPaint
        )
        // Draw progress.
        oval.left = progressBackgroundWidth / 2
        oval.top = progressBackgroundWidth / 2
        oval.right = width - progressBackgroundWidth / 2
        oval.bottom = width - progressBackgroundWidth / 2
        val range = 360 * progress
        canvas.drawArc(oval, -90f, range, false, mProgressPaint)
        // Draw progress end.
        val radius = circlePoint - progressBackgroundWidth / 2
        val x1 = circlePoint - radius * cos((range + 90) * 3.14 / 180)
        val y1 = circlePoint - radius * sin((range + 90) * 3.14 / 180)
        canvas.drawCircle(x1.toFloat(), y1.toFloat(), progressBackgroundWidth / 2, mTextCirclePaint)
        val txt = "${progress * 100}%"
        val stringWidth = mTextPaint.measureText(txt)
        canvas.drawText(
            txt,
            x1.toFloat() - stringWidth / 2,
            y1.toFloat() + progressTextSize / 2,
            mTextPaint
        )
    }
}