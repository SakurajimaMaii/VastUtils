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

package com.gcode.vastutils.basebindadpexample

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.IntRange
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import com.gcode.vastadapter.base.VastBindAdapter
import com.gcode.vastadapter.interfaces.VastBindAdapterItem
import com.gcode.vastutils.BR

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/1/28
// Description:
// Documentation:

class BaseBindingAdapter(
    dataSource: MutableList<VastBindAdapterItem>,
    mContext: Context
) : VastBindAdapter(dataSource, mContext) {
    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun setImageUri(view: ImageView, imageUri: String?) {
            if (imageUri == null) {
                view.setImageURI(null)
            } else {
                view.setImageURI(Uri.parse(imageUri))
            }
        }

        @JvmStatic
        @BindingAdapter("android:src")
        fun setImageUri(view: ImageView, imageUri: Uri?) {
            view.setImageURI(imageUri)
        }

        @JvmStatic
        @BindingAdapter("android:src")
        fun setImageDrawable(view: ImageView, drawable: Drawable?) {
            view.setImageDrawable(drawable)
        }

        @JvmStatic
        @BindingAdapter("android:src")
        fun setImageResource(imageView: ImageView, resource: Int) {
            imageView.setImageResource(resource)
        }
    }

    /**
     * Returns `true` if the collection is empty (contains no elements), `false` otherwise.
     * @return Boolean
     */
    fun isItemEmpty() = dataSource.isEmpty()

    /**
     * Return item from list by position.
     * @param position Int
     * @return BaseItem?
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    fun getItemByPos(@IntRange(from = 0) position: Int): VastBindAdapterItem {
        if (position >= itemCount || position < 0) {
            throw ArrayIndexOutOfBoundsException("The parameter pos should be less than ${dataSource.size}")
        }
        return dataSource[position]
    }

    /**
     * Adds the specified item to the end of this list.
     * If you want to add in other position,please refer [addItemByPos]
     * @param item Item you add
     * @return The result `false` means adding failed or item is `null`
     */
    fun addItem(@Nullable item: VastBindAdapterItem?): Boolean {
        return if (item == null) {
            false
        } else {
            val flag = dataSource.add(item)
            if (flag) {
                notifyItemInserted(this.itemCount - 1)
            }
            flag
        }
    }

    /**
     * Inserts an element into the list at the specified [pos].
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    fun addItemByPos(item: VastBindAdapterItem, @IntRange(from = 0) pos: Int) {
        if (pos > dataSource.size) {
            throw ArrayIndexOutOfBoundsException("The range of the parameter pos in the addItemByPos() method is wrong")
        }
        dataSource.add(pos, item)
        notifyItemInserted(pos)
    }

    /**
     * Inserts all of the elements of the specified collection [addItems] into this list at the specified [pos].
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    fun addItemsByPos(addItems: MutableList<VastBindAdapterItem>, @IntRange(from = 0) pos: Int) {
        if (pos > dataSource.size) {
            throw ArrayIndexOutOfBoundsException("The parameter pos cannot be greater than ${dataSource.size}")
        }
        dataSource.addAll(pos, addItems)
        notifyItemRangeChanged(pos, addItems.size)
    }

    /**
     * Removes an element from the list by [item].
     */
    fun removeItemByObj(@Nullable item: VastBindAdapterItem?): Boolean {
        val pos: Int = dataSource.indexOf(item)
        if (pos >= 0 && pos < dataSource.size) {
            removeItemByPos(pos)
        }
        return pos >= 0 && pos < dataSource.size
    }

    /**
     * Removes an element at the specified [pos] from the list.
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    @Nullable
    fun removeItemByPos(@IntRange(from = 0) pos: Int): VastBindAdapterItem? {
        return if (dataSource.isEmpty())
            null
        else {
            if (pos >= dataSource.size || pos < 0) {
                throw ArrayIndexOutOfBoundsException("The range of the parameter pos should be between 0 and ${dataSource.size - 1}.")
            }
            val item: VastBindAdapterItem = dataSource.removeAt(pos)
            notifyItemRemoved(pos)
            item
        }
    }

    /**
     * Delete the elements in the range from [startPos] to [endPos]
     */
    @SuppressLint("NotifyDataSetChanged")
    fun removeItemsByPos(
        @IntRange(from = 0) startPos: Int,
        @IntRange(from = 0) endPos: Int,
        includeEndPos: Boolean = false
    ) {
        if (endPos - startPos >= dataSource.size) {
            throw ArrayIndexOutOfBoundsException("The deleted range is larger than the size of the array itself")
        } else if (startPos > endPos) {
            throw Exception("startPos should be smaller than endPos")
        } else if (startPos >= dataSource.size || endPos >= dataSource.size) {
            throw ArrayIndexOutOfBoundsException("The parameter startPos or endPos should be less than ${dataSource.size - 1}.")
        } else {
            // See https://www.kaelli.com/23.html
            if (includeEndPos) {
                dataSource.subList(startPos, endPos + 1).clear()
            } else {
                dataSource.subList(startPos, endPos).clear()
            }
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItem() {
        if (dataSource.isNotEmpty()) {
            dataSource.clear()
            notifyDataSetChanged()
        }
    }

    override fun setVariableId(): Int {
        return BR.item
    }
}