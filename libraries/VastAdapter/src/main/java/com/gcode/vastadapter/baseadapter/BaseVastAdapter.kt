package com.gcode.vastadapter.baseadapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastadapter.exception.VastAdapterNotFound
import com.gcode.vastadapter.interfaces.VastAdapterItem

open class BaseVastAdapter(
    private val dataSource: MutableList<VastAdapterItem>,
    private val factories: MutableList<BaseVastAdapterVH.Factory>
) : RecyclerView.Adapter<BaseVastAdapterVH>() {

    private var mClickListener: OnItemClickListener? = null
    private var mLongClickListener: OnItemLongClickListener? = null

    private val type2ItemType: MutableMap<String, Int> = HashMap()

    final override fun getItemViewType(position: Int): Int {
        val item: VastAdapterItem = dataSource[position]
        val type: String = item.getVastAdapterItemType()
        if(type2ItemType[type] == null){
            throw VastAdapterNotFound("Not found the itemType according to the position.")
        }else{
            return type2ItemType[type]!!
        }
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVastAdapterVH {
        val targetFactory: BaseVastAdapterVH.Factory = factories[viewType]
        val holder = targetFactory.onCreateViewHolder(parent, viewType)
        if (mClickListener != null) {
            holder.itemView.setOnClickListener {
                mClickListener!!.onItemClick(holder.itemView, holder.layoutPosition, holder.itemId)
            }
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener {
                mLongClickListener!!.onItemLongClick(holder.itemView, holder.layoutPosition, holder.itemId)
            }
        }
        return holder
    }

    final override fun onBindViewHolder(holder: BaseVastAdapterVH, position: Int) {
        val itemData: VastAdapterItem = dataSource[position]
        holder.onBindData(itemData)
    }

    final override fun getItemCount() = dataSource.size

    init {
        for (i in factories.indices) {
            val factory: BaseVastAdapterVH.Factory = factories[i]
            val type: String = factory.getVastAdapterVHType()
            val itemType = type2ItemType[type]
            if (itemType != null) {
                val currentFactory: String = factory.javaClass.name
                val sameFactory: String = factories[itemType].javaClass.name
                throw RuntimeException("Same type found: $currentFactory and $sameFactory")
            }
            type2ItemType[type] = i
        }
    }

    interface OnItemClickListener {
        fun onItemClick(itemView: View?, pos: Int, itemId: Long)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(itemView: View?, pos: Int, itemId: Long): Boolean
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener) {
        mLongClickListener = onItemLongClickListener
    }
}