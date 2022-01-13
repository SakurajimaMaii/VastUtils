package com.gcode.vastadapter.baseadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastadapter.exception.ViewTypeError

/**
 * Base vast binding adapter
 * @param obj The class of the list item.
 * @property items List Data.
 */
abstract class BaseVastBindingAdapter<obj : BaseVastItem> constructor(
    private val items: MutableList<obj>,
) : RecyclerView.Adapter<BaseVastBindingAdapter.BindingHolder>() {

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

    override fun getItemViewType(position: Int):Int {
        val viewType = items[position].getItemBindViewType()
        if (viewType == -1){
            throw ViewTypeError("Please check if you have set the correct layout id")
        }
        return viewType
    }

    /**
     * Returns `true` if the collection is empty (contains no elements), `false` otherwise.
     * @return Boolean
     */
    fun isItemEmpty() = items.isEmpty()

    /**
     * Return item from list by position.
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
     * Adds the specified item to the end of this list.
     * If you want to add in other position,please refer [addItemByPos]
     * @param item Item you add
     * @return The result `false` means adding failed or item is `null`
     */
    fun addItem(@Nullable item: obj?): Boolean {
        return if (item == null) {
            false
        } else {
            val flag = items.add(item)
            if (flag) {
                notifyItemInserted(this.itemCount - 1)
            }
            flag
        }
    }

    /**
     * Inserts an element into the list at the specified [pos].
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
     * Inserts all of the elements of the specified collection [addItems] into this list at the specified [pos].
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
     * Removes an element from the list by [obj].
     */
    fun removeItemByObj(@Nullable item: obj?): Boolean {
        val pos: Int = items.indexOf(item)
        if (pos >= 0 && pos < items.size) {
            removeItemByPos(pos)
        }
        return pos >= 0 && pos < items.size
    }

    /**
     * Removes an element at the specified [pos] from the list.
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
     * Delete the elements in the range from [startPos] to [endPos]
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
        fun bindData(variableId: Int, item: BaseVastItem?) {
            binding.setVariable(variableId, item)
        }
    }
}