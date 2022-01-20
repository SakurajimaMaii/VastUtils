package com.gcode.vastadapter.baseadapter

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
open class BaseVastAdapterVH(val itemView: View): RecyclerView.ViewHolder(itemView) {
    open fun onBindData(item:VastAdapterItem){

    }

    interface Factory{
        /**
         * Create the current ViewHolder instance.
         */
        fun onCreateViewHolder(parent: ViewGroup,viewType:Int):BaseVastAdapterVH

        /**
         * Should be the same as the type returned by the corresponding item.
         */
        fun getVastAdapterVHType():String
    }
}