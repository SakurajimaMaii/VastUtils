package com.gcode.tools.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntRange
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
abstract class BaseAdapter @JvmOverloads constructor(private var items: MutableList<BaseItem>,private val tag:String = "BaseAdapter") : RecyclerView.Adapter<BaseAdapter.RecyclerViewHolder>() {

    private var mClickListener: OnItemClickListener? = null
    private var mLongClickListener: OnItemLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val holder = RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        )
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
     * @param pos Int
     * @return BaseItem
     */
    fun getItemByPos(@IntRange(from = 0) pos: Int): BaseItem {
        if (pos >= itemCount) {
            throw ArrayIndexOutOfBoundsException("The range of the parameter pos in the addItemByPos() method is wrong")
        }
        return items[pos]
    }

    /**
     * 在最后添加
     * @param item BaseItem
     * @return Boolean
     */
    fun addItem(item: BaseItem): Boolean {
        val flag = items.add(item)
        if (flag) {
            notifyItemInserted(this.itemCount - 1)
        }
        Log.d(tag, "The result of the addItem() is $flag")
        return flag
    }

    /**
     * 通过pos来添加item
     * @param item BaseItem
     * @param pos Int
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    fun addItemByPos(item: BaseItem,@IntRange(from = 0) pos: Int) {
        if(pos>items.size){
            throw ArrayIndexOutOfBoundsException("The range of the parameter pos in the addItemByPos() method is wrong")
        }
        items.add(pos, item)
        notifyItemInserted(pos)
    }

    /**
     * 通过对象来删除Item
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
     * 通过pos来删除对象
     * @param pos Int
     * @return BaseItem?
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

    /**
     * 清空列表全部元素
     */
    @SuppressLint("NotifyDataSetChanged")
    fun clearItem() {
        if(items.isNotEmpty()){
            items.clear()
            notifyDataSetChanged()
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
    abstract fun bindData(holder: RecyclerViewHolder, position: Int, item: BaseItem)

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

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun <T : View> findViewById(viewId: Int): T {
            return itemView.findViewById(viewId)
        }
    }
}