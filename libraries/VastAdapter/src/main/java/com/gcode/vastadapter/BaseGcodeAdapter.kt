package com.gcode.vastadapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import kotlin.jvm.Throws

/**
 *作者:created by HP on 2021/4/2 12:17
 *邮箱:sakurajimamai2020@qq.com
 */
/**
 * Adapter基类
 * @property items MutableList<BaseItem>
 * @property tag String 用来打印日志的时候使用
 * @property mClickListener OnItemClickListener?
 * @property mLongClickListener OnItemLongClickListener?
 * @constructor
 */
abstract class BaseGcodeAdapter<obj : BaseGcodeItem> @JvmOverloads constructor(
    private var items: MutableList<obj>,
    private val tag: String = "BaseAdapter"
) : RecyclerView.Adapter<BaseGcodeAdapter.RecyclerViewHolder>() {

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
     * 判断列表是否为空
     * @return Boolean
     */
    fun isItemEmpty() = items.isEmpty()

    /**
     * 借助pos来获取item
     * @param pos
     * @return 获取到的对象
     */
    fun getItemByPos(@IntRange(from = 0) pos: Int): obj {
        if (pos >= items.size) {
            throw ArrayIndexOutOfBoundsException("The parameter pos should be less than ${items.size}")
        }
        return items[pos]
    }

    /**
     * 在数据集最后添加
     * 如果你想在其他位置添加,请参考 [addItemByPos]
     * @param item 添加的对象
     * @return 操作的结果 [false] 表示添加失败或者item为 [null]
     */
    fun addItem(@Nullable item: obj?): Boolean {
        return if (item != null) {
            val flag = items.add(item)
            if (flag) {
                notifyItemInserted(this.itemCount - 1)
            }
            Log.d(tag, "The result of the addItem() is $flag")
            flag
        } else {
            false
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

    // 清空列表全部元素
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