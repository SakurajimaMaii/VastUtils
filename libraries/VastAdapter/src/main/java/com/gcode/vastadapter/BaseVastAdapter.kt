package com.gcode.vastadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView

/**
 * Base vast adapter
 *
 * @param obj The class of the list item.
 * @property items List Data.
 */
abstract class BaseVastAdapter<obj : BaseVastItem> constructor(
    private var items: MutableList<obj>,
) : RecyclerView.Adapter<BaseVastAdapter.RecyclerViewHolder>() {

    private var mClickListener: OnItemClickListener? = null
    private var mLongClickListener: OnItemLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val holder = RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        )
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
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        bindData(holder, position, items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].getItemViewType()

    /**
     * Returns `true` if the collection is empty (contains no elements), `false` otherwise.
     * @return Boolean
     */
    fun isItemEmpty() = items.isEmpty()

    /**
     * Return item by position
     * @param pos
     * @return item you get
     */
    fun getItemByPos(@IntRange(from = 0) pos: Int): obj {
        if (pos >= items.size) {
            throw ArrayIndexOutOfBoundsException("The parameter pos should be less than ${items.size}")
        }
        return items[pos]
    }

    /**
     * Adds the specified element to the end of this list.
     * If you want to add in other specified `pos`, please refer to [addItemByPos]
     * @param item Item you want to add.
     * @return The result `false` means adding failed or item is `null`
     */
    fun addItem(@Nullable item: obj?): Boolean {
        return if (item != null) {
            val flag = items.add(item)
            if (flag) {
                notifyItemInserted(this.itemCount - 1)
            }
            flag
        } else {
            false
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
     * Delete Item by object
     * @param item The object to be deleted
     * @return Return the result of the operation
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
     *
     * @return the element that has been removed.
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
     * @param startPos
     * @param endPos
     * @param includeEndPos `true` Indicates that the element pointed to by [endPos] is contained
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

    /**
     * Clear all elements in the list
     */
    @SuppressLint("NotifyDataSetChanged")
    fun clearItem() {
        if (items.isNotEmpty()) {
            items.clear()
            this.notifyDataSetChanged()
        }
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
    abstract fun bindData(holder: RecyclerViewHolder, position: Int, item: obj)

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

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun <T : View> findViewById(viewId: Int): T {
            return itemView.findViewById(viewId)
        }
    }
}