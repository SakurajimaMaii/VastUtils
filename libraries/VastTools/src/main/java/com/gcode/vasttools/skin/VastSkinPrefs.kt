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

package com.gcode.vasttools.skin

/**
 * @Author: Vast Gui
 * @Email: guihy2019@gmail.com
 * @Date: 2022/3/30 18:41
 * @Description:
 * @Documentation:
 */

internal const val THEME_FILE = "com.gcode.vastskin"
internal const val THEME_PATH = "com.gcode.vastskin.path"

internal const val CHANGEABLY_BACKGROUND = "background"
internal const val CHANGEABLY_SRC = "src"
internal const val CHANGEABLY_TEXT_COLOR = "textColor"
internal const val CHANGEABLY_DRAWABLE_LEFT = "drawableLeft"
internal const val CHANGEABLY_DRAWABLE_TOP = "drawableTop"
internal const val CHANGEABLY_DRAWABLE_RIGHT = "drawableRight"
internal const val CHANGEABLY_DRAWABLE_BOTTOM = "drawableBottom"

/**
 * Vast skin currently supports changeably resource types.
 */
internal val ChangeablyAttrs: MutableList<String> = ArrayList<String>().apply {
    add(CHANGEABLY_BACKGROUND)
    add(CHANGEABLY_SRC)
    add(CHANGEABLY_TEXT_COLOR)
    add(CHANGEABLY_DRAWABLE_LEFT)
    add(CHANGEABLY_DRAWABLE_TOP)
    add(CHANGEABLY_DRAWABLE_RIGHT)
    add(CHANGEABLY_DRAWABLE_BOTTOM)
}