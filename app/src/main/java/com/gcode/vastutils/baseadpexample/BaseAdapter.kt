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

package com.gcode.vastutils.baseadpexample

import android.annotation.SuppressLint
import androidx.annotation.IntRange
import androidx.annotation.Nullable
import com.gcode.vastadapter.base.VastAdapter
import com.gcode.vastadapter.base.VastAdapterVH
import com.gcode.vastadapter.interfaces.VastAdapterItem

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/19
 */
class BaseAdapter(
    private val items: MutableList<VastAdapterItem>,
    factories: MutableList<VastAdapterVH.BVAdpVHFactory>
) : VastAdapter(items, factories) {

    /**
     * Returns `true` if the collection is empty (contains no elements), `false` otherwise.
     * @return Boolean
     */
    fun isItemEmpty() = items.isEmpty()

    /**
     * Return item by position
     * @param pos
     * @return item you get
     */
    fun getItemByPos(@IntRange(from = 0) pos: Int): VastAdapterItem {
        if (pos >= items.size) {
            throw ArrayIndexOutOfBoundsException("The parameter pos should be less than ${items.size}")
        }
        return items[pos]
    }

    /**
     * Adds the specified element to the end of this list.
     * If you want to add in other specified `pos`, please refer to [addItemByPos]
     * @param item Item you want to add.
     * @return The result `false` means adding failed or item is `null`
     */
    fun addItem(item: VastAdapterItem?): Boolean {
        return if (item != null) {
            val flag = items.add(item)
            if (flag) {
                notifyItemInserted(this.itemCount - 1)
            }
            flag
        } else {
            false
        }
    }

    /**
     * Inserts an element into the list at the specified [pos].
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    fun addItemByPos(item: VastAdapterItem, @IntRange(from = 0) pos: Int) {
        if (pos > items.size) {
            throw ArrayIndexOutOfBoundsException("The range of the parameter pos in the addItemByPos() method is wrong")
        }
        items.add(pos, item)
        notifyItemInserted(pos)
    }

    /**
     * Inserts all of the elements of the specified collection [addItems] into this list at the specified [pos].
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    fun addItemsByPos(addItems: MutableList<VastAdapterItem>, @IntRange(from = 0) pos: Int) {
        if (pos > items.size) {
            throw ArrayIndexOutOfBoundsException("The parameter pos cannot be greater than ${items.size}")
        }
        items.addAll(pos, addItems)
        notifyItemRangeChanged(pos, addItems.size)
    }

    /**
     * Delete Item by object
     * @param item The object to be deleted
     * @return Return the result of the operation
     */
    fun removeItemByObj(@Nullable item: VastAdapterItem?): Boolean {
        val pos: Int = items.indexOf(item)
        if (pos >= 0 && pos < items.size) {
            removeItemByPos(pos)
        }
        return pos >= 0 && pos < items.size
    }

    /**
     * Removes an element at the specified [pos] from the list.
     *
     * @return the element that has been removed.
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    @Nullable
    fun removeItemByPos(@IntRange(from = 0) pos: Int): VastAdapterItem? {
        return if (items.isEmpty())
            null
        else {
            if (pos >= items.size || pos < 0) {
                throw ArrayIndexOutOfBoundsException("The range of the parameter pos should be between 0 and ${items.size - 1}.")
            }
            val item: VastAdapterItem = items.removeAt(pos)
            notifyItemRemoved(pos)
            item
        }
    }

    /**
     * Delete the elements in the range from [startPos] to [endPos]
     * @param startPos
     * @param endPos
     * @param includeEndPos `true` Indicates that the element pointed to by [endPos] is contained
     */
    @SuppressLint("NotifyDataSetChanged")
    fun removeItemsByPos(
        @IntRange(from = 0) startPos: Int,
        @IntRange(from = 0) endPos: Int,
        includeEndPos: Boolean = false
    ) {
        if (endPos - startPos >= items.size) {
            throw ArrayIndexOutOfBoundsException("The deleted range is larger than the size of the array itself")
        } else if (startPos > endPos) {
            throw Exception("startPos should be smaller than endPos")
        } else if (startPos >= items.size || endPos >= items.size) {
            throw ArrayIndexOutOfBoundsException("The parameter startPos or endPos should be less than ${items.size - 1}.")
        } else {
            // See https://www.kaelli.com/23.html
            if (includeEndPos) {
                items.subList(startPos, endPos + 1).clear()
            } else {
                items.subList(startPos, endPos).clear()
            }
            notifyDataSetChanged()
        }
    }

    /**
     * Clear all elements in the list
     */
    @SuppressLint("NotifyDataSetChanged")
    fun clearItem() {
        if (items.isNotEmpty()) {
            items.clear()
            this.notifyDataSetChanged()
        }
    }
}