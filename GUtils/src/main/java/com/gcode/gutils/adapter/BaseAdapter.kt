package com.gcode.gutils.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 *作者:created by HP on 2021/4/2 12:17
 *邮箱:sakurajimamai2020@qq.com
 */
abstract class BaseAdapter(private var items: MutableList<BaseItem>) :
    RecyclerView.Adapter<BaseAdapter.RecyclerViewHolder>() {

    private var mClickListener: OnItemClickListener? = null
    private var mLongClickListener: OnItemLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val holder = RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        )
        holder.itemView.setOnClickListener {
            mClickListener?.onItemClick(holder.itemView, holder.layoutPosition)
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener {
                mLongClickListener!!.onItemLongClick(holder.itemView, holder.layoutPosition)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        bindData(holder, position, items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].getItemViewType()

    fun isEmpty() = items.isEmpty()

    fun getItem(position: Int): BaseItem? {
        return if (position >= this.itemCount || position < 0) {
            Log.i(
                "CommonAdapter",
                "The input position is not in the range of 0 to ${itemCount - 1}"
            )
            null
        } else
            items[position]
    }

    fun add(item: BaseItem): Boolean {
        val flag = items.add(item)
        if (flag) {
            notifyItemInserted(this.itemCount - 1)
        }
        Log.i("CommonAdapter", "The result of the insert operation is $flag")
        return flag
    }

    fun add(pos: Int, item: BaseItem) {
        items.add(pos, item)
        notifyItemInserted(pos)
    }

    fun remove(item: BaseItem?): Boolean {
        val index: Int = items.indexOf(item)
        var flag = index >= 0 && index < items.size
        if (flag) {
            flag = remove(index) != null
        }
        return flag
    }

    fun remove(index: Int): BaseItem? {
        if (index < 0 || index >= items.size) {
            return null
        }
        val item: BaseItem = items.removeAt(index)
        notifyItemRemoved(index)
        return item
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    /**
     * With the help of the holder.findViewById() , assign values to the children of RecyclerView.
     * E.g holder.findViewById<TextView>(R.id.firstName).text = (item as Person).firstName
     * For more information, please refer to the example given in the link below
     * https://github.com/SakurajimaMaii/GStyleUtils
     * @param holder RecyclerViewHolder
     * @param position Int
     * @param item BaseItem
     */
    abstract fun bindData(holder: RecyclerViewHolder, position: Int, item: BaseItem)

    interface OnItemClickListener {
        fun onItemClick(itemView: View?, pos: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(itemView: View?, pos: Int): Boolean
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener){
        mLongClickListener = onItemLongClickListener
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun <T : View> findViewById(viewId: Int): T {
            return itemView.findViewById(viewId)
        }
    }
}