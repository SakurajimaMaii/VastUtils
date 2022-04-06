package com.gcode.vastadapter.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastadapter.interfaces.VastAdapterItem

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/17
 */
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