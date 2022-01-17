package com.gcode.vastswipeview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastswipeview.VastSwipeViewMgr
import com.gcode.vastswipeview.exception.VastSwipeViewNotFound
import com.gcode.vastswipeview.exception.VastSwipeViewTypeError
import com.gcode.vastswipeview.interfaces.VastSwipeContentItem
import com.gcode.vastswipeview.view.VastSwipeViewItem

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/12/30
 */
abstract class VastSwipeViewAdapter(
    private val sourceData:MutableList<out VastSwipeContentItem>,
    private val manager:VastSwipeViewMgr
): RecyclerView.Adapter<VastSwipeViewAdapter.AdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent,false)
        return AdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        bindData(holder,position,sourceData[position])
        if(holder.itemView !is VastSwipeViewItem){
            throw VastSwipeViewTypeError("The root layout of your item is not the type of VastSwipeViewItem.")
        }else{
            (holder.itemView as VastSwipeViewItem).setPosition(position)
            (holder.itemView as VastSwipeViewItem).setManager(manager)
        }
    }

    override fun getItemCount() = sourceData.size

    override fun getItemViewType(position: Int) = sourceData[position].getType()

    override fun onViewDetachedFromWindow(holder: AdapterViewHolder) {
        // In order to reset itemView.
        holder.itemView.scrollTo(0,0)
    }

    class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun <T : View> findViewById(viewId: Int): T {
            return itemView.findViewById(viewId)
        }
    }

    abstract fun bindData(holder: AdapterViewHolder, position: Int, item: VastSwipeContentItem)
}