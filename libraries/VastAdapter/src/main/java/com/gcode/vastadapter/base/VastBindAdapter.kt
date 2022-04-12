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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastadapter.interfaces.VastBindAdapterItem

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2021/4/2
// Description:
// Documentation:

abstract class VastBindAdapter constructor(
    private val dataSource: MutableList<VastBindAdapterItem>,
) : RecyclerView.Adapter<VastBindAdapter.BindingHolder>() {

    // Fix https://github.com/SakurajimaMaii/VastUtils/issues/36
    private var onItemClickListener: OnItemClickListener? = null
    private var onItemLongClickListener: OnItemLongClickListener? = null

    final override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val item = dataSource[position]
        holder.bindData(setVariableId(), item)
        holder.itemView.setOnClickListener {
            if (null != item.getVBAapClickEventListener()) {
                item.getVBAapClickEventListener()!!.vAapClickEvent(holder.itemView,position)
            } else {
                onItemClickListener?.onItemClick(holder.itemView, position)
            }
        }
        holder.itemView.setOnLongClickListener {
            val res = if (null != item.getVBAdpLongClickEventListener()) {
                item.getVBAdpLongClickEventListener()!!.vAdpLongClickEvent(holder.itemView, position)
            } else {
                onItemLongClickListener?.onItemLongClick(holder.itemView, position)
            }
            return@setOnLongClickListener res ?: false
        }
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )
        return BindingHolder(binding)
    }

    final override fun getItemCount() = dataSource.size

    final override fun getItemViewType(position: Int):Int {
        val viewType = dataSource[position].getVBAdpItemType()
        if (viewType == -1){
            throw RuntimeException("Please check if you have set the correct layout id")
        }
        return viewType
    }

    /**
     * Set VariableId value
     * For example, in the layout file
     * <data>
     *     <variable
     *     name="item"
     *     type="com.gcode.gutilssampledemo.Person" />
     * </data>
     *
     * Then the implementation of the function should be
     * override fun setVariableId(): Int {
     *      return BR.item
     * }
     * For more information, please refer to the example given in the link below
     * https://github.com/SakurajimaMaii/GStyleUtils
     * @return Int
     */
    abstract fun setVariableId(): Int

    class BindingHolder(private var binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(variableId: Int, item: VastBindAdapterItem?) {
            binding.setVariable(variableId, item)
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