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
// Date: 2022/1/17
// Description:
// Documentation:

open class VastAdapterVH(itemView: View): RecyclerView.ViewHolder(itemView) {
    open fun onBindData(item: VastAdapterItem){

    }

    interface BVAdpVHFactory{
        /**
         * Create the current ViewHolder instance.
         */
        fun onCreateViewHolder(parent: ViewGroup,viewType:Int):VastAdapterVH

        /**
         * @return A string which is same as the value you set in [VastAdapterItem.getVAdpItemType].
         *         In this way, the data item can be matched to the corresponding ViewHolder.
         */
        fun getVAdpVHType():String
    }
}