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
         *
         * For example,If the VH corresponding to person is pVH, then the
         * getVAdpItemType method of person returns **"per"**, and the return
         * value of getVAdpVHType corresponding to pVH should also be **"per"**.
         */
        fun getVAdpVHType():String
    }
}