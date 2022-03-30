package com.gcode.vastutils.baseadpexample.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gcode.vastadapter.base.VastAdapterVH
import com.gcode.vastadapter.interfaces.VastAdapterItem
import com.gcode.vastutils.R
import com.gcode.vastutils.baseadpexample.model.AExample

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/19
 */
class AViewHolder(itemView: View): VastAdapterVH(itemView) {
    private val tv:TextView

    override fun onBindData(item: VastAdapterItem) {
        super.onBindData(item)
        tv.text = (item as AExample).data
    }

    class Factory:BVAdpVHFactory{
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VastAdapterVH {
            return AViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_textview,parent,false))
        }

        override fun getVAdpVHType(): String {
            return "person"
        }
    }

    init {
        tv = itemView.findViewById(R.id.text)
    }
}