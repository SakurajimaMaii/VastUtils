package com.gcode.vastswipeview.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastswipeview.VastSwipeViewMgr
import com.gcode.vastswipeview.interfaces.VastSwipeContentItem


/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/12/30
 */
open class VastSwipeViewMenuVH (
    itemView: View,
    private val context: Context = itemView.context
): RecyclerView.ViewHolder(itemView) {

    /**
     * Assign values to content layout
     */
    open fun onBindData(item: VastSwipeContentItem){}

    interface Factory{

        /**
         * Combine the left and right menu layout and content layout
         */
        fun onCreateViewHolder(parent: ViewGroup, viewType: Int,manager: VastSwipeViewMgr):VastSwipeViewMenuVH

        /**
         * Returns the ItemType string corresponding to the current Factory
         */
        fun getType(): String
    }
}