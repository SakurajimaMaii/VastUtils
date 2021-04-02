package com.gcode.gutils.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 *作者:created by HP on 2021/4/2 14:43
 *邮箱:sakurajimamai2020@qq.com
 */
abstract class BaseBindingAdapter(private val items: MutableList<BaseItem>) :
        RecyclerView.Adapter<BaseBindingAdapter.BindingHolder>() {

    private var mClickListener: OnItemClickListener? = null
    private var mLongClickListener: OnItemLongClickListener? = null

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.bindData(setVariableId(), items[position])
        holder.itemView.setOnClickListener {
            mClickListener?.onItemClick(holder.itemView, position)
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener {
                mLongClickListener.onItemLongClick(holder.itemView, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
        )
        return BindingHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].getItemBindViewType()

    fun isEmpty() = items.isEmpty()

    fun getItem(position: Int): BaseItem? {
        return if (position >= this.itemCount || position < 0) {
            Log.i("CommonAdapter", "The input position is not in the range of 0 to ${itemCount - 1}")
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
     * Set VariableId value
     * For example, in the layout file
     * <data>
     *     <variable
     *     name="item"
     *     type="com.gcode.gutilssampledemo.Person" />
     * </data>
     *
     * Then the implementation of the function should be
     * override fun setVariableId(): Int {
     *      return BR.item
     * }
     * For more information, please refer to the example given in the link below
     * https://github.com/SakurajimaMaii/GStyleUtils
     * @return Int
     */
    abstract fun setVariableId(): Int

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

    class BindingHolder(private var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(variableId: Int, item: BaseItem?) {
            binding.setVariable(variableId, item)
        }
    }
}