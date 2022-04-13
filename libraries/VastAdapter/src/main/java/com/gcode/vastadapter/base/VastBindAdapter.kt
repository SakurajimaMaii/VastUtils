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

package com.gcode.vastadapter.base

import android.content.Context
import android.content.res.Resources
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
    protected var dataSource: MutableList<VastBindAdapterItem>,
    protected var mContext: Context
) : RecyclerView.Adapter<VastBindAdapter.BindingHolder>() {

    // Fix https://github.com/SakurajimaMaii/VastUtils/issues/36
    private var onItemClickListener: OnItemClickListener? = null
    private var onItemLongClickListener: OnItemLongClickListener? = null

    final override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val item = dataSource[position]
        holder.bindData(setVariableId(), item)
        holder.itemView.setOnClickListener {
            if (null != item.vbAapClickEventListener) {
                item.vbAapClickEventListener!!.vAapClickEvent(holder.itemView,position)
            } else {
                onItemClickListener?.onItemClick(holder.itemView, position)
            }
        }
        holder.itemView.setOnLongClickListener {
            val res = if (null != item.vbAdpLongClickEventListener) {
                item.vbAdpLongClickEventListener!!.vAdpLongClickEvent(holder.itemView, position)
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
        try {
            // Identify whether there is a resource file for the resource id.
            mContext.resources.getLayout(viewType)
        }catch(e:Resources.NotFoundException){
            throw RuntimeException("Please check if the return value of the getVBAdpItemType method in ${dataSource[position].javaClass.simpleName}.kt is correct.")
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