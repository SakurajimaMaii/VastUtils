package com.gcode.vastadapter.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastadapter.exception.VastAdapterNotFound

abstract class BaseVastAdapter(
    private val dataSource: MutableList<VastAdapterItem>,
    private val factories: MutableList<BaseVastAdapterVH.BVAdpVHFactory>
) : RecyclerView.Adapter<BaseVastAdapterVH>() {

    private val type2ItemType: MutableMap<String, Int> = HashMap()

    final override fun getItemViewType(position: Int): Int {
        val item: VastAdapterItem = dataSource[position]
        val type: String = item.getVAIType()
        if (type2ItemType[type] == null) {
            throw VastAdapterNotFound("Not found the itemType according to the position.")
        } else {
            return type2ItemType[type]!!
        }
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVastAdapterVH {
        val targetFactory: BaseVastAdapterVH.BVAdpVHFactory = factories[viewType]
        return targetFactory.onCreateViewHolder(parent, viewType)
    }

    final override fun onBindViewHolder(holder: BaseVastAdapterVH, position: Int) {
        val itemData: VastAdapterItem = dataSource[position]
        holder.onBindData(itemData)
        holder.itemView.setOnClickListener {
            itemData.vAdpClickEvent?.let {
                    clickEvent -> clickEvent(holder.itemView,itemData,position,holder.itemId)
            }
        }
        holder.itemView.setOnLongClickListener {
            itemData.vAdpLongClickEvent?.also {
                    clickEvent -> clickEvent(holder.itemView,itemData,position,holder.itemId)
            }
            return@setOnLongClickListener true
        }
    }

    final override fun getItemCount() = dataSource.size

    init {
        for (i in factories.indices) {
            val factory: BaseVastAdapterVH.BVAdpVHFactory = factories[i]
            val type: String = factory.getVAdpVHType()
            val itemType = type2ItemType[type]
            if (itemType != null) {
                val currentFactory: String = factory.javaClass.name
                val sameFactory: String = factories[itemType].javaClass.name
                throw RuntimeException("Same type found: $currentFactory and $sameFactory")
            }
            type2ItemType[type] = i
        }
    }
}