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

package com.gcode.vastutils.baseadpexample.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.gcode.vastadapter.base.VastAdapterVH
import com.gcode.vastadapter.interfaces.VastAdapterItem
import com.gcode.vastutils.R
import com.gcode.vastutils.baseadpexample.model.AExample
import com.gcode.vastutils.baseadpexample.model.BExample

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/1/19
// Description:
// Documentation:

class BViewHolder(itemView: View) : VastAdapterVH(itemView) {

    private val iv: ImageView

    override fun onBindData(item: VastAdapterItem) {
        super.onBindData(item)
        iv.setImageResource((item as BExample).drawable)
    }

    class Factory : BVAdpVHFactory {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): VastAdapterVH {
            val inflater = LayoutInflater.from(parent.context)
            val itemView: View = inflater.inflate(R.layout.item_imageview, parent, false)
            return BViewHolder(itemView)
        }

        override fun getVAdpVHType(): String {
            return BExample::class.java.simpleName
        }

    }

    init {
        iv = itemView.findViewById(R.id.item_image)
    }
}