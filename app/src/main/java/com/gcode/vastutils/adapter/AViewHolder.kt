package com.gcode.vastutils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gcode.vastadapter.adapter.BaseVastAdapterVH
import com.gcode.vastadapter.adapter.VastAdapterItem
import com.gcode.vastutils.R
import com.gcode.vastutils.model.Person

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/19
 */
class AViewHolder(itemView: View): BaseVastAdapterVH(itemView) {
    private val first:TextView
    private val last: TextView

    override fun onBindData(item: VastAdapterItem) {
        super.onBindData(item)
        first.text = (item as Person).firstName
        last.text = (item as Person).lastName
    }

    class Factory:BaseVastAdapterVH.BVAdpVHFactory{
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVastAdapterVH {
            return AViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_textview,parent,false))
        }

        override fun getVAdpVHType(): String {
            return "person"
        }
    }

    init {
        first = itemView.findViewById(R.id.firstName)
        last = itemView.findViewById(R.id.lastName)
    }
}