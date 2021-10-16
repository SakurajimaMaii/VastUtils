package com.gcode.tools.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kotlin.jvm.Throws

/**
 *作者:created by HP on 2021/4/2 14:43
 *邮箱:sakurajimamai2020@qq.com
 */
abstract class BaseGcodeBindingAdapter<obj : BaseGcodeItem> @JvmOverloads constructor(
    private val items: MutableList<obj>,
    private val tag: String = "BaseBindingAdapter"
) :
    RecyclerView.Adapter<BaseGcodeBindingAdapter.BindingHolder>() {

    private var mClickListener: OnItemClickListener? = null
    private var mLongClickListener: OnItemLongClickListener? = null

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.bindData(setVariableId(), items[position])
        if (mClickListener != null) {
            holder.itemView.setOnClickListener {
                mClickListener!!.onItemClick(holder.itemView, holder.layoutPosition, holder.itemId)
            }
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener {
                mLongClickListener!!.onItemLongClick(
                    holder.itemView,
                    holder.layoutPosition,
                    holder.itemId
                )
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
    fun getItemByPos(@IntRange(from = 0) position: Int): obj {
        if (position >= itemCount || position < 0) {
            throw ArrayIndexOutOfBoundsException("The parameter pos should be less than ${items.size}")
        }
        return items[position]
    }

    /**
     * 在数据集最后添加
     * 如果你想在其他位置添加,请参考 [addItemByPos]
     * @param item 添加的对象
     * @return 操作的结果 [false] 表示添加失败或者item为 [null]
     */
    fun addItem(@Nullable item: obj?): Boolean {
        return if (item == null) {
            false
        } else {
            val flag = items.add(item)
            if (flag) {
                notifyItemInserted(this.itemCount - 1)
            }
            Log.i(tag, "The result of the insert operation is $flag")
            flag
        }
    }

    /**
     * 通过pos来添加item
     * @param item 添加的对象
     * @param pos 添加的位置
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    fun addItemByPos(item: obj, @IntRange(from = 0) pos: Int) {
        if (pos > items.size) {
            throw ArrayIndexOutOfBoundsException("The range of the parameter pos in the addItemByPos() method is wrong")
        }
        items.add(pos, item)
        notifyItemInserted(pos)
    }

    /**
     * 通过pos来批量添加item
     * @param addItems 添加的数据集
     * @param pos 添加的位置
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    fun addItemsByPos(addItems: MutableList<obj>, @IntRange(from = 0) pos: Int) {
        if (pos > items.size) {
            throw ArrayIndexOutOfBoundsException("The parameter pos cannot be greater than ${items.size}")
        }
        items.addAll(pos, addItems)
        notifyItemRangeChanged(pos, addItems.size)
    }

    /**
     * 通过对象来删除Item
     * @param item 要删除的对象
     * @return 返回操作的结果
     */
    fun removeItemByObj(@Nullable item: obj?): Boolean {
        val pos: Int = items.indexOf(item)
        if (pos >= 0 && pos < items.size) {
            removeItemByPos(pos)
        }
        return pos >= 0 && pos < items.size
    }

    /**
     * 通过pos来删除对象
     * @param pos 删除对象的索引
     * @return 如果items为空，则返回 [null] 否则返回删除的对象
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    @Nullable
    fun removeItemByPos(@IntRange(from = 0) pos: Int): obj? {
        return if (items.isEmpty())
            null
        else {
            if (pos >= items.size || pos < 0) {
                throw ArrayIndexOutOfBoundsException("The range of the parameter pos should be between 0 and ${items.size - 1}.")
            }
            val item: obj = items.removeAt(pos)
            notifyItemRemoved(pos)
            item
        }
    }

    /**
     * 删除[startPos]到[endPos]范围内的元素
     * @param startPos
     * @param endPos
     * @param includeEndPos [true] 表示包含endPos指向的元素
     */
    @SuppressLint("NotifyDataSetChanged")
    fun removeItemsByPos(
        @IntRange(from = 0) startPos: Int,
        @IntRange(from = 0) endPos: Int,
        includeEndPos: Boolean = false
    ) {
        if (endPos - startPos >= items.size) {
            throw ArrayIndexOutOfBoundsException("The deleted range is larger than the size of the array itself")
        } else if (startPos > endPos) {
            throw Exception("startPos should be smaller than endPos")
        } else if (startPos >= items.size || endPos >= items.size) {
            throw ArrayIndexOutOfBoundsException("The parameter startPos or endPos should be less than ${items.size - 1}.")
        } else {
            // See https://www.kaelli.com/23.html
            if (includeEndPos) {
                items.subList(startPos, endPos + 1).clear()
            } else {
                items.subList(startPos, endPos).clear()
            }
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItem() {
        if (items.isNotEmpty()) {
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

    class BindingHolder(private var binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(variableId: Int, item: BaseGcodeItem?) {
            binding.setVariable(variableId, item)
        }
    }
}