package com.gcode.tools.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kotlin.jvm.Throws

/**
 *作者:created by HP on 2021/4/2 14:43
 *邮箱:sakurajimamai2020@qq.com
 */
abstract class BaseBindingAdapter @JvmOverloads constructor(private val items: MutableList<BaseItem>,private val tag:String = "BaseBindingAdapter") :
        RecyclerView.Adapter<BaseBindingAdapter.BindingHolder>() {

    private var mClickListener: OnItemClickListener? = null
    private var mLongClickListener: OnItemLongClickListener? = null

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.bindData(setVariableId(), items[position])
        if(mClickListener != null){
            holder.itemView.setOnClickListener {
                mClickListener!!.onItemClick(holder.itemView, holder.layoutPosition)

                mClickListener!!.onItemClick(holder.itemView, holder.layoutPosition,holder.itemId)
            }
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener {
                mLongClickListener!!.onItemLongClick(holder.itemView, holder.layoutPosition)

                mLongClickListener!!.onItemLongClick(holder.itemView, holder.layoutPosition,holder.itemId)
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

    /**
     * 判断列表是否为空
     * @return Boolean
     */
    fun isItemEmpty() = items.isEmpty()

    /**
     * 借助pos获取对象
     * @param position Int
     * @return BaseItem?
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    fun getItemByPos(@IntRange(from = 0) position: Int): BaseItem {
        if (position >= itemCount || position < 0) {
            throw ArrayIndexOutOfBoundsException("The range of the parameter pos in the addItemByPos() method is wrong")
        }
        return items[position]
    }

    /**
     * 添加Item
     * @param item BaseItem
     * @return Boolean
     */
    fun addItem(item: BaseItem): Boolean {
        val flag = items.add(item)
        if (flag) {
            notifyItemInserted(this.itemCount - 1)
        }
        Log.i(tag, "The result of the insert operation is $flag")
        return flag
    }

    /**
     * 通过pos来添加item
     * @param pos Int
     * @param item BaseItem
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    fun addItemPyPos(item: BaseItem,@IntRange(from = 0) pos: Int) {
        if(pos>items.size || pos < 0){
            throw ArrayIndexOutOfBoundsException("The range of the parameter pos in the addItemByPos() method is wrong")
        }
        items.add(pos, item)
        notifyItemInserted(pos)
    }

    /**
     * 删除指定的Item对象
     * @param item BaseItem?
     * @return Boolean
     */
    fun removeItem(item: BaseItem?): Boolean {
        val pos: Int = items.indexOf(item)
        if(pos >= 0 && pos < items.size){
            removeItemByPos(pos)
        }
        return pos >= 0 && pos < items.size
    }

    /**
     * 通过pos删除指定对象
     * @param pos Int
     * @return BaseItem
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    fun removeItemByPos(@IntRange(from = 0) pos: Int): BaseItem {
        if (pos >= items.size || pos < 0) {
            throw ArrayIndexOutOfBoundsException("The range of the parameter pos in the removeItemByIndex() method is wrong")
        }
        val item: BaseItem = items.removeAt(pos)
        notifyItemRemoved(pos)
        return item
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItem() {
        if(items.isNotEmpty()){
            items.clear()
            notifyDataSetChanged()
        }
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

        fun onItemClick(itemView: View?, pos: Int, itemId:Long)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(itemView: View?, pos: Int): Boolean

        fun onItemLongClick(itemView: View?, pos: Int ,itemId: Long): Boolean
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