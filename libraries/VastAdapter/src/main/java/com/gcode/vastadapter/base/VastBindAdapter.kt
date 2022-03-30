package com.gcode.vastadapter.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastadapter.exception.VastAdapterValueError
import com.gcode.vastadapter.interfaces.VastBindAdapterItem

/**
 * Base vast binding adapter
 *
 * @property dataSource List Data.
 */
abstract class VastBindAdapter constructor(
    private val dataSource: MutableList<VastBindAdapterItem>,
) : RecyclerView.Adapter<VastBindAdapter.BindingHolder>() {

    // Fix https://github.com/SakurajimaMaii/VastUtils/issues/35
    private var onItemClickListener: OnItemClickListener? = null
    private var onItemLongClickListener: OnItemLongClickListener? = null

    final override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val item = dataSource[position]
        holder.bindData(setVariableId(), item)
        holder.itemView.setOnClickListener {
            if (null != item.vbAdpClickEventListener) {
                item.vbAdpClickEventListener!!.vAapClickEvent(holder.itemView,position)
            } else {
                onItemClickListener?.onItemClick(holder.itemView, position)
            }
        }
        holder.itemView.setOnLongClickListener {
            val res = if (null != item.vbAdpLongClickEventListener) {
                item.vbAdpLongClickEventListener!!.vAdpLongClickEvent(holder.itemView, position)
            } else {
                onItemLongClickListener?.onItemLongClick(holder.itemView, position)
            }
            return@setOnLongClickListener res ?: false
        }
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )
        return BindingHolder(binding)
    }

    final override fun getItemCount() = dataSource.size

    final override fun getItemViewType(position: Int):Int {
        val viewType = dataSource[position].getVBAdpItemType()
        if (viewType == -1){
            throw VastAdapterValueError("Please check if you have set the correct layout id")
        }
        return viewType
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

    class BindingHolder(private var binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(variableId: Int, item: VastBindAdapterItem?) {
            binding.setVariable(variableId, item)
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener?) {
        this.onItemLongClickListener = onItemLongClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int): Boolean
    }
}