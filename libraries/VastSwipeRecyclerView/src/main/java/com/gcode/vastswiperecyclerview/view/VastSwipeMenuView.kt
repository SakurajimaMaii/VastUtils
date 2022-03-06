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
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.IntRange
import androidx.core.view.setMargins
import com.gcode.vastswiperecyclerview.VastSwipeRvMgr
import com.gcode.vastswiperecyclerview.annotation.ICON_TITLE
import com.gcode.vastswiperecyclerview.annotation.ONLY_ICON
import com.gcode.vastswiperecyclerview.annotation.ONLY_TITLE
import com.gcode.vastswiperecyclerview.model.VastSwipeMenu


/**
 * @OriginalAuthor: Vast Gui @OriginalDate: @EditAuthor: Vast Gui
 * @EditDate: 2021/11/29
 */

/** Vast swipe menu view. */
class VastSwipeMenuView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    /** See the definition of the [VastSwipeRvMgr]. */
    private lateinit var manager: VastSwipeRvMgr

    /** The swipe menu position in the list. */
    var position: Int = -1
        private set

    /** Set the position of the menu. */
    fun setPosition(@IntRange(from = 0) position: Int) {
        this.position = position
    }

    fun setMenu(menuList:MutableList<VastSwipeMenu>){
        removeAllViews()

        for(menu in menuList){
            addItem(menu)
        }
    }

    fun setManager(manager: VastSwipeRvMgr) {
        this.manager = manager
    }

    private fun createIcon(item: VastSwipeMenu): ImageView {
        return ImageView(context).apply {
            setImageDrawable(item.icon)
            layoutParams = LayoutParams(manager.iconSize.toInt(), manager.iconSize.toInt()).apply {
                setMargins(10)
            }
        }
    }

    private fun createTitle(item: VastSwipeMenu): TextView {
        return TextView(context).apply {
            text = item.title
            gravity = Gravity.CENTER
            val (size,unit) = manager.titleSize
            setTextSize(unit,size)
            layoutParams =
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    setMargins(10)
                }
            setTextColor(item.titleColor)
        }
    }

    private fun addItem(item: VastSwipeMenu) {
        val parent = LinearLayout(context)
        parent.apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            minimumWidth = manager.swipeMenuWidth.toInt()
            gravity = Gravity.CENTER
            orientation = VERTICAL
            background = item.background
            setOnClickListener {
                item.clickListener?.onClickEvent(item, position)
            }
            setOnLongClickListener {
                return@setOnLongClickListener item.clickListener?.onLongClickEvent(
                    item,
                    position
                ) == true
            }
            when (manager.swipeMenuContentStyle) {
                ONLY_ICON -> {
                    if (item.icon != null) {
                        parent.addView(createIcon(item))
                    }
                }
                ONLY_TITLE -> {
                    if (!TextUtils.isEmpty(item.title)) {
                        parent.addView(createTitle(item))
                    }
                }
                ICON_TITLE -> {
                    if (item.icon != null) {
                        parent.addView(createIcon(item))
                    }

                    if (!TextUtils.isEmpty(item.title)) {
                        parent.addView(createTitle(item))
                    }
                }
            }
        }
        addView(parent)
    }

}