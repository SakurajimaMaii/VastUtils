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

package com.gcode.vastadapter.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastadapter.interfaces.VastAdapterItem

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2021/4/2
// Description:
// Documentation:

abstract class VastAdapter(
    private val dataSource: MutableList<VastAdapterItem>,
    private val factories: MutableList<VastAdapterVH.BVAdpVHFactory>
) : RecyclerView.Adapter<VastAdapterVH>() {

    private val type2ItemType: MutableMap<String, Int> = HashMap()

    // Fix https://github.com/SakurajimaMaii/VastUtils/issues/36
    private var onItemClickListener: OnItemClickListener? = null
    private var onItemLongClickListener: OnItemLongClickListener? = null

    final override fun getItemViewType(position: Int): Int {
        val item: VastAdapterItem = dataSource[position]
        val type: String = item.getVAdpItemType()
        if (type2ItemType[type] == null) {
            throw RuntimeException("Not found the itemType according to the position.")
        } else {
            return type2ItemType[type]!!
        }
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VastAdapterVH {
        val targetFactory: VastAdapterVH.BVAdpVHFactory = factories[viewType]
        return targetFactory.onCreateViewHolder(parent, viewType)
    }

    final override fun onBindViewHolder(holder: VastAdapterVH, position: Int) {
        val itemData: VastAdapterItem = dataSource[position]
        holder.onBindData(itemData)
        holder.itemView.setOnClickListener {
            if (null != itemData.getVAapClickEventListener()) {
                itemData.getVAapClickEventListener()!!.vAapClickEvent(holder.itemView,position)
            } else {
                onItemClickListener?.onItemClick(holder.itemView, position)
            }
        }
        holder.itemView.setOnLongClickListener {
            val res = if (null != itemData.getVAdpLongClickEventListener()) {
                itemData.getVAdpLongClickEventListener()!!.vAdpLongClickEvent(holder.itemView, position)
            } else {
                onItemLongClickListener?.onItemLongClick(holder.itemView, position)
            }
            return@setOnLongClickListener res ?: false
        }
    }

    final override fun getItemCount() = dataSource.size

    init {
        for (i in factories.indices) {
            val factory: VastAdapterVH.BVAdpVHFactory = factories[i]
            val type: String = factory.getVAdpVHType()
            val itemType = type2ItemType[type]
            if (itemType != null) {
                val currentFactory: String = factory.javaClass.name
                val sameFactory: String = factories[itemType].javaClass.name
                throw RuntimeException("Same type found: $currentFactory and $sameFactory")
            }
            type2ItemType[type] = i
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener?) {
        this.onItemLongClickListener = onItemLongClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int): Boolean
    }

}