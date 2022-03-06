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

@file:JvmName("VastSwipeRvConstant")

package com.gcode.vastswiperecyclerview.annotation

import android.util.TypedValue.*
import androidx.annotation.IntDef
import androidx.annotation.StringDef

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/29
 */
/**
 * Swipe in the x-axis direction
 */
const val TOUCH_STATE_X = 1

/**
 * Swipe in the y-axis direction
 */
const val TOUCH_STATE_Y = 2

/**
 * Default swipe direction.
 */
const val TOUCH_STATE_NONE = 0

/**
 * Just show title
 */
const val ONLY_TITLE = "ONLY_TITLE"

/**
 * Just show icon
 */
const val ONLY_ICON = "ONLY_ICON"

/**
 * show title and icon
 */
const val ICON_TITLE = "ICON_TITLE"

const val STATE_INIT = 0X01

/**
 * Swipe menu close state.
 */
const val STATE_CLOSE = 0X02

/**
 * Swipe menu right open state.
 *
 * It also means you swipe to the left.
 */
const val STATE_RIGHT_OPEN = 0X03

/**
 * Swipe menu left open state.
 *
 * It also means you swipe to the right.
 */
const val STATE_LEFT_OPEN = 0X04

/**
 * Not init
 */
const val NOT_INIT = -1

/**
 * Only right have menu.
 */
const val ONLY_RIGHT = 0

/**
 * Only left have menu.
 */
const val ONLY_LEFT = 1

/**
 * Left and right have menu.
 */
const val LEFT_RIGHT = 2

/**
 * Using when you want to set swipe menu content style.
 */
@Retention(AnnotationRetention.SOURCE)
@StringDef(ONLY_TITLE, ONLY_ICON, ICON_TITLE)
annotation class SwipeMenuContentStyle

/**
 * Use when set the swipe orientation
 */
@Retention(AnnotationRetention.SOURCE)
@IntDef(STATE_CLOSE, STATE_LEFT_OPEN, STATE_RIGHT_OPEN)
annotation class SwipeMenuOrientation

/**
 * Using when you want to set swipe menu style.
 */
@Retention(AnnotationRetention.SOURCE)
@IntDef(NOT_INIT, ONLY_LEFT, ONLY_RIGHT, LEFT_RIGHT)
annotation class MenuStyle

/**
 * Dimension
 */
@Retention(AnnotationRetention.SOURCE)
@IntDef(COMPLEX_UNIT_PX,COMPLEX_UNIT_DIP,COMPLEX_UNIT_SP,COMPLEX_UNIT_PT,COMPLEX_UNIT_IN,COMPLEX_UNIT_MM)
annotation class Dimension