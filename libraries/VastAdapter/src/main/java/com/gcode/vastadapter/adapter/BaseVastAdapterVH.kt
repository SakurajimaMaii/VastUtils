package com.gcode.vastadapter.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/17
 */
open class BaseVastAdapterVH(itemView: View): RecyclerView.ViewHolder(itemView) {
    open fun onBindData(item: VastAdapterItem){

    }

    interface BVAdpVHFactory{
        /**
         * Create the current ViewHolder instance.
         */
        fun onCreateViewHolder(parent: ViewGroup,viewType:Int):BaseVastAdapterVH

        /**
         * Should be the same as the type returned by the corresponding item.
         */
        fun getVAdpVHType():String
    }
}