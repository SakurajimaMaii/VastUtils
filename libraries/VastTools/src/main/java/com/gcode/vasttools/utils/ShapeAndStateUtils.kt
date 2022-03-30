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

package com.gcode.vasttools.utils

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.GradientDrawable.*
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.IntDef
import androidx.annotation.IntRange

/**
 * @Author: Vast Gui
 * @Email: guihy2019@gmail.com
 * @Date: 2022/3/10 15:27
 * @Description: Help you to quickly build GradientDrawable.
 * @Documentation:
 */

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    LINEAR_GRADIENT,
    RADIAL_GRADIENT,
    SWEEP_GRADIENT
)
annotation class GradientType

@Retention(AnnotationRetention.SOURCE)
@IntDef(RECTANGLE, OVAL, LINE, RING)
annotation class DrawableShape

class ShapeAndStateUtils private constructor(){

    /**
     * Gradient type.
     */
    @setparam:GradientType
    var gradientType: Int = LINEAR_GRADIENT
        private set

    /**
     * Gradient orientation,please check [GradientDrawable.Orientation].
     */
    var gradientOrientation: Orientation = Orientation.TOP_BOTTOM
        private set

    /**
     * GradientDrawable Colors
     */
    var colors: IntArray = intArrayOf()
        private set

    /**
     * Shape of GradientDrawable.
     */
    @setparam:DrawableShape
    var shape: Int = RECTANGLE
        private set

    /**
     * Gradient radius
     *
     * When [gradientType] is set to [GradientDrawable.RADIAL_GRADIENT],you should set
     * [gradientRadius].Otherwise, **0** will be used as the default value
     */
    var gradientRadius: Float = 0f
        private set

    /**
     * Specifies the radius for the corners of the gradient. If this is > 0,
     * then the drawable is drawn in a round-rectangle, rather than a
     * rectangle. This property is honored only when the shape is of type
     * [GradientDrawable.RECTANGLE].
     */
    var radius: Float = 0f
        private set

    /**
     * Specifies radii for each of the 4 corners.
     */
    var radii: FloatArray = floatArrayOf(0f,0f,0f,0f,0f,0f,0f,0f)
        private set

    /**
     * Left top corner radius(in pixels).
     */
    var leftTopCornerRadius: Float = 0f
        private set
        get() = radii[0]

    /**
     * Left bottom corner radius(in pixels).
     */
    var leftBottomCornerRadius: Float = 0f
        private set
        get() = radii[6]

    /**
     * Right top corner radius(in pixels).
     */
    var rightTopCornerRadius: Float = 0f
        private set
        get() = radii[2]

    /**
     * Right bottom corner radius(in pixels).
     */
    var rightBottomCornerRadius: Float = 0f
        private set
        get() = radii[4]

    /**
     * Stroke width(in pixels).
     */
    var strokeWidth: Float = 0f
        private set

    /**
     * Stroke color(in 0xAARRGGBB)
     */
    var strokeColor: Int = 0x00000000
        private set

    /**
     * Dash width of stroke(in pixels).
     */
    var dashWidth: Float = 0f
        private set

    /**
     * Dash gap of stroke(in pixels).
     */
    var dashGap: Float = 0f
        private set

    /**
     * Used to define background colors in different states.
     */
    var bgColorStateList:ColorStateList? = null
        private set

    /**
     * Used to define stroke colors in different states.
     */
    var strokeColorStateList:ColorStateList? = null
        private set

    private val density = Resources.getSystem().displayMetrics.density

    /**
     * Set gradient type of GradientDrawable.
     *
     * @param type You can choose from [GradientDrawable.LINEAR_GRADIENT],[GradientDrawable.SWEEP_GRADIENT],[GradientDrawable.RADIAL_GRADIENT]
     */
    fun setGradientType(@GradientType type: Int): ShapeAndStateUtils {
        gradientType = type
        return this
    }

    /**
     * Set bk color
     */
    fun setBkColor(@ColorInt color: Int):ShapeAndStateUtils{
        return setGradient(0,color,color)
    }

    /**
     * Set gradient color and gradient angle.
     *
     * @param angle Must be an integer multiple of 45.
     * @param startColor Start gradient color.
     * @param endColor End gradient color.
     */
    fun setGradient(
        angle: Int,
        @ColorInt startColor: Int,
        @ColorInt endColor: Int
    ): ShapeAndStateUtils {
        val orientation = angle2Orientation(angle)
        return setGradient(orientation, startColor, endColor)
    }

    /**
     * Set gradient color and gradient orientation.
     *
     * @param orientation Gradient Orientation.
     * @param startColor Start gradient color.
     * @param endColor End gradient color.
     */
    fun setGradient(
        orientation: Orientation,
        @ColorInt startColor: Int,
        @ColorInt endColor: Int
    ): ShapeAndStateUtils {
        gradientOrientation = orientation
        colors = intArrayOf(startColor, endColor)
        return this
    }

    /**
     * Set gradient color and gradient angle.
     *
     * @param angle Must be an integer multiple of 45.
     * @param startColor Start gradient color.
     * @param centerColor Center gradient color.
     * @param endColor End gradient color.
     */
    fun setGradient(
        angle: Int,
        @ColorInt startColor: Int,
        @ColorInt centerColor: Int,
        @ColorInt endColor: Int
    ): ShapeAndStateUtils {
        val orientation = angle2Orientation(angle)
        return setGradient(orientation, startColor, centerColor, endColor)
    }

    /**
     * Set gradient color and gradient orientation.
     *
     * @param orientation Gradient Orientation.
     * @param startColor Start gradient color.
     * @param centerColor Center gradient color.
     * @param endColor End gradient color.
     */
    fun setGradient(
        orientation: Orientation,
        @ColorInt startColor: Int,
        @ColorInt centerColor: Int,
        @ColorInt endColor: Int
    ): ShapeAndStateUtils {
        gradientOrientation = orientation
        colors = intArrayOf(startColor, centerColor, endColor)
        return this
    }

    /**
     * Sets the type of shape used to draw the gradient.
     *
     * @param shape You can choose from [GradientDrawable.OVAL],[GradientDrawable.LINE],[GradientDrawable.RECTANGLE],[GradientDrawable.RING]
     */
    fun setShape(@DrawableShape shape: Int): ShapeAndStateUtils {
        this.shape = shape
        return this
    }

    /**
     * Set gradient radius when [gradientType] is [GradientDrawable.RADIAL_GRADIENT].
     */
    fun setGradientRadius(@FloatRange(from = 0.0) radius: Float): ShapeAndStateUtils {
        gradientRadius = radius
        return this
    }

    /**
     * Set specifies radii for corners.
     *
     * @param radius Must be >= 0
     */
    fun setRadius(@FloatRange(from = 0.0) radius: Float): ShapeAndStateUtils {
        this.radius = radius
        return setRadius(radius, radius, radius, radius)
    }

    /**
     * Set specifies radii for each of the 4 corners.
     */
    fun setRadius(
        @FloatRange(from = 0.0) topLeft: Float,
        @FloatRange(from = 0.0) topRight: Float,
        @FloatRange(from = 0.0) bottomLeft: Float,
        @FloatRange(from = 0.0) bottomRight: Float
    ): ShapeAndStateUtils {
        radii = floatArrayOf(
            topLeft,
            topLeft,
            topRight,
            topRight,
            bottomRight,
            bottomRight,
            bottomLeft,
            bottomLeft
        )
        return this
    }

    /**
     * Set stroke.
     *
     * @param width The width of the stroke.
     * @param color The color of the stroke.
     */
    fun setStroke(
        @FloatRange(from = 0.0) width: Float,
        @ColorInt color: Int
    ): ShapeAndStateUtils {
        strokeWidth = width
        strokeColor = color
        return this
    }

    /**
     * Set stroke.
     *
     * @param width The width of the stroke.
     * @param color The color of the stroke.
     * @param dashWidth The width of the dash.
     * @param dashGap The width of the dash.
     */
    fun setStroke(
        @IntRange(from = 0) width: Int,
        @ColorInt color: Int,
        @IntRange(from = 0) dashWidth: Int,
        @IntRange(from = 0) dashGap: Int
    ): ShapeAndStateUtils {
        strokeWidth = width * density
        strokeColor = color
        this.dashWidth = dashWidth * density
        this.dashGap = dashGap * density
        return this
    }

    /**
     * Creates a background ColorStateList that returns the specified mapping from
     * states to colors.
     */
    fun setBgColorStateList(state: Array<IntArray?>, colors:IntArray):ShapeAndStateUtils{
        bgColorStateList = ColorStateList(state, colors)
        return this
    }

    /**
     * Creates a stroke ColorStateList that returns the specified mapping from
     * states to colors.
     */
    fun setStrokeColorStateList(state: Array<IntArray?>, colors:IntArray):ShapeAndStateUtils{
        strokeColorStateList = ColorStateList(state, colors)
        return this
    }

    /**
     * Angle convert to orientation.
     *
     * @param angle Must be an integer multiple of 45.
     * @return Default value is [GradientDrawable.Orientation.LEFT_RIGHT]
     */
    private fun angle2Orientation(angle: Int): Orientation {
        return when (angle) {
            0 -> Orientation.LEFT_RIGHT
            45 -> Orientation.BL_TR
            90 -> Orientation.BOTTOM_TOP
            135 -> Orientation.BR_TL
            180 -> Orientation.RIGHT_LEFT
            225 -> Orientation.TR_BL
            270 -> Orientation.TOP_BOTTOM
            315 -> Orientation.TL_BR
            else -> Orientation.LEFT_RIGHT
        }
    }

    fun build(): GradientDrawable {
        val drawable = GradientDrawable()
        drawable.apply {
            gradientType = this@ShapeAndStateUtils.gradientType

            shape = this@ShapeAndStateUtils.shape

            gradientRadius = this@ShapeAndStateUtils.gradientRadius

            orientation = gradientOrientation

            if(null == bgColorStateList){
                colors = this@ShapeAndStateUtils.colors
            }else{
                color = bgColorStateList
            }

            cornerRadii = radii

            if(null == strokeColorStateList){
                this.setStroke(strokeWidth.toInt(),strokeColor,dashWidth,dashGap)
            }else{
                this.setStroke(strokeWidth.toInt(),strokeColorStateList,dashWidth,dashGap)
            }

        }
        return drawable
    }

    companion object{
        @JvmStatic
        fun create():ShapeAndStateUtils{
            return ShapeAndStateUtils()
        }
    }
}