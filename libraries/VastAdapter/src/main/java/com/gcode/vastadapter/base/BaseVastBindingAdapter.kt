package com.gcode.vastadapter.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastadapter.exception.VastAdapterValueError
import com.gcode.vastadapter.interfaces.VastBindingAdapterItem

/**
 * Base vast binding adapter
 *
 * @property dataSource List Data.
 */
abstract class BaseVastBindingAdapter constructor(
    private val dataSource: MutableList<VastBindingAdapterItem>,
) : RecyclerView.Adapter<BaseVastBindingAdapter.BindingHolder>() {

    final override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val item = dataSource[position]
        holder.bindData(setVariableId(), item)
        holder.itemView.setOnClickListener {
            item.vbAdpClickEvent?.let{
                clickEvent -> clickEvent(holder.itemView,position)
            }
        }
        holder.itemView.setOnLongClickListener {
            item.vbAdpLongClickEvent?.also {
                longClickEvent -> longClickEvent(holder.itemView,position)
            }
            return@setOnLongClickListener true
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
        fun bindData(variableId: Int, item: VastBindingAdapterItem?) {
            binding.setVariable(variableId, item)
        }
    }
}