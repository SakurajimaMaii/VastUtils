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

package com.gcode.vastswiperecyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastswiperecyclerview.R
import com.gcode.vastswiperecyclerview.VastSwipeRvMgr
import com.gcode.vastswiperecyclerview.model.VastSwipeMenu
import com.gcode.vastswiperecyclerview.view.VastSwipeMenuLayout
import com.gcode.vastswiperecyclerview.view.VastSwipeMenuView
import java.lang.reflect.Field


/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/12/30
 */
internal class VastSwipeWrapperAdapter(
    private val manager: VastSwipeRvMgr,
    private val mAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        val viewHolder = mAdapter.onCreateViewHolder(parent, viewType)

        if(null != manager.mSwipeItemClickListener){
            viewHolder.itemView.setOnClickListener {
                manager.mSwipeItemClickListener!!.onClickEvent(it,viewHolder.bindingAdapterPosition)
            }
        }

        if(null != manager.mSwipeItemLongClickListener){
            viewHolder.itemView.setOnLongClickListener {
                manager.mSwipeItemLongClickListener!!.onLongClickEvent(it,viewHolder.bindingAdapterPosition)
            }
        }

        val contentView = LayoutInflater.from(parent.context).inflate(R.layout.item_vast_swipe_menu, parent,false)
        val viewGroup = contentView.findViewById<FrameLayout>(R.id.swipe_content)
        viewGroup.addView(viewHolder.itemView)

        try {
            val itemView: Field = getSuperClass(viewHolder.javaClass).getDeclaredField("itemView")
            if (!itemView.isAccessible) itemView.isAccessible = true
            itemView[viewHolder] = contentView
        } catch (ignored: Exception) {

        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemView = holder.itemView as VastSwipeMenuLayout
        val leftMenuList:MutableList<VastSwipeMenu> = ArrayList()
        val rightMenuList:MutableList<VastSwipeMenu> = ArrayList()
        manager.mSwipeMenuCreator?.onCreateMenu(leftMenuList, rightMenuList, position)
        val leftMenu:VastSwipeMenuView = (itemView.getChildAt(0) as VastSwipeMenuView).apply {
            setManager(manager)
            setPosition(position)
            setMenu(leftMenuList)
        }
        val rightMenu:VastSwipeMenuView = (itemView.getChildAt(2) as VastSwipeMenuView).apply {
            setManager(manager)
            setPosition(position)
            setMenu(rightMenuList)
        }

        mAdapter.onBindViewHolder(holder,position)

    }

    override fun getItemCount() = mAdapter.itemCount

    override fun getItemViewType(position: Int) = mAdapter.getItemViewType(position)

    private fun getSuperClass(aClass: Class<*>): Class<*> {
        val superClass = aClass.superclass
        return if (superClass != null && superClass != Any::class.java) {
            getSuperClass(superClass)
        } else aClass
    }

}