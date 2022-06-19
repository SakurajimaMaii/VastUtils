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

package com.gcode.vastutils.activity.baselvadpexample.viewHolder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gcode.vasttools.adapter.BaseListViewItem
import com.gcode.vasttools.adapter.BaseListViewVH
import com.gcode.vastutils.R
import com.gcode.vastutils.activity.baselvadpexample.model.LVIA


// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/6/19
// Description: 
// Documentation:

class LVIAVH(context: Context, convert: View?, parent: ViewGroup?, resource: Int):
    BaseListViewVH(context, convert, parent, resource) {

    private val tv: TextView = itemView.findViewById(R.id.lvia_content)

    override fun bindData(item: BaseListViewItem) {
        tv.text = (item as LVIA).string
    }

    class Factory:BaseFactory{

        override fun getViewHolder(
            context: Context,
            convertView: View?,
            parent: ViewGroup?,
            resource: Int
        ): BaseListViewVH {
            return LVIAVH(context,convertView,parent,resource)
        }

        override fun getLayoutId(): Int {
            return R.layout.lv_item_lvia
        }

    }

}