package com.gcode.vastswipeview.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastswipeview.VastSwipeViewMgr
import com.gcode.vastswipeview.exception.VastSwipeViewNotFound
import com.gcode.vastswipeview.interfaces.VastSwipeContentItem
import com.gcode.vastswipeview.view.VastSwipeViewItem

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/12/30
 */
open class VastSwipeViewAdapter(
    private val sourceData:MutableList<out VastSwipeContentItem>,
    private val holderContext: Context,
    private val factories: MutableList<out VastSwipeViewMenuVH.Factory>,
    private val manager: VastSwipeViewMgr,
): RecyclerView.Adapter<VastSwipeViewMenuVH>() {

    private val type2ItemType = HashMap<String, Int>()

    init {
        for(index in factories.indices){
            val type = factories[index].getType()
            val itemType = type2ItemType[type]
            if(itemType != null){
                val currentFactory: String = factories[index].javaClass.name
                val sameFactory = factories[itemType].javaClass.name
                throw RuntimeException("Same type found: $currentFactory and $sameFactory")
            }
            type2ItemType[type] = index
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VastSwipeViewMenuVH {
        val targetFactory = factories[viewType]
        return targetFactory.onCreateViewHolder(parent, viewType,manager)
    }

    override fun onBindViewHolder(holder: VastSwipeViewMenuVH, position: Int) {
        val item = sourceData[position]
        holder.onBindData(item)
        (holder.itemView as VastSwipeViewItem).setPosition(position)
    }

    override fun getItemCount() = sourceData.size

    override fun getItemViewType(position: Int): Int {
        val item = sourceData[position]
        val type = item.getType()
        if(type2ItemType[type] == null){
            throw VastSwipeViewNotFound("Not found the itemViewType of the position $position")
        }
        return type2ItemType[type]!!
    }

    override fun onViewDetachedFromWindow(holder: VastSwipeViewMenuVH) {
        // In order to reset itemView.
        holder.itemView.scrollTo(0,0)
    }
}