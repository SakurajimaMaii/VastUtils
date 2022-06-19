/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gcode.vasttools.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.annotation.LayoutRes


// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/6/19
// Description: 
// Documentation:

/**
 * A base adapter for [ListView].
 *
 * @property context context.
 * @property dataSources the source data.
 * @since 0.0.9
 */
abstract class BaseListViewAdapter(
    private val context: Context,
    protected val dataSources: ArrayList<BaseListViewItem>,
    protected val factories: MutableList<BaseListViewVH.BaseFactory>
) : BaseAdapter() {

    private val layoutId2FactoriesIndex: MutableMap<Int, Int> = HashMap()

    init {
        for (i in factories.indices) {
            val factory = factories[i]
            val layoutId = factory.getLayoutId()
            if (null != layoutId2FactoriesIndex[layoutId]) {
                throw RuntimeException("Same type found in factories")
            }
            layoutId2FactoriesIndex[layoutId] = i
        }
    }

    override fun getCount() = dataSources.size

    override fun getItem(position: Int) = dataSources[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val index = layoutId2FactoriesIndex[dataSources[position].getLayoutId()]
            ?: throw RuntimeException("Can't found the index in layoutId2FactoriesIndex.")
        val factory = factories[index]
        val viewHolder =
            factory.getViewHolder(context, convertView, parent, dataSources[position].getLayoutId())
        viewHolder.bindData(dataSources[position])
        return viewHolder.getConvertView()
    }

}

/**
 * The base ViewHolder of the [BaseListViewVH].
 *
 * @since 0.0.9
 */
open class BaseListViewVH(
    private val context: Context,
    private val convertView: View?,
    private val parent: ViewGroup?,
    private val resource: Int
) {
    protected val itemView: View =
        convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

    interface BaseFactory {

        fun getViewHolder(
            context: Context,
            convertView: View?,
            parent: ViewGroup?,
            resource: Int
        ): BaseListViewVH

        fun getLayoutId(): Int

    }

    fun getConvertView(): View {
        return itemView
    }

    open fun bindData(item: BaseListViewItem) {

    }

}

/**
 * Make sure your [ListView] item implements this interface so that the
 * [BaseListViewAdapter] can know the layout corresponding to the item.
 *
 * @since 0.0.9
 */
interface BaseListViewItem {
    /**
     * @return the layout resource id of the item.
     * @since 0.0.9
     */
    fun getLayoutId(): Int
}