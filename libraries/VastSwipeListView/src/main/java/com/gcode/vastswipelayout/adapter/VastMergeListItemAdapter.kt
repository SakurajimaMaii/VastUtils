package com.gcode.vastswipelayout.adapter

import android.content.Context
import android.database.DataSetObserver
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.WrapperListAdapter
import com.gcode.vastswipelayout.VastSwipeMenuMgr
import com.gcode.vastswipelayout.view.VastSwipeLeftMenu
import com.gcode.vastswipelayout.view.VastSwipeListItemLayout
import com.gcode.vastswipelayout.view.VastSwipeRightMenu

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/29
 */

/**
 * Vast merge list item adapter
 *
 * Merge the ContentView and the SwipeMenuLayout to display the menu
 *
 * @property context Context.
 * @property adapter The adapter of the listView.
 * @property swipeMenuMgr The swipe menu manager.
 */
internal open class VastMergeListItemAdapter(
    private val context: Context,
    private val adapter: ListAdapter,
    private val swipeMenuMgr: VastSwipeMenuMgr
) : WrapperListAdapter {

    override fun getCount() = adapter.count

    override fun getItem(position: Int): Any = adapter.getItem(position)

    override fun getItemId(position: Int) = adapter.getItemId(position)

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        adapter.registerDataSetObserver(observer)
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        adapter.unregisterDataSetObserver(observer)
    }

    override fun areAllItemsEnabled() = adapter.areAllItemsEnabled()

    override fun isEnabled(position: Int) = adapter.isEnabled(position)

    override fun hasStableIds() = adapter.hasStableIds()

    override fun getItemViewType(position: Int) = adapter.getItemViewType(position)

    override fun getViewTypeCount() = adapter.viewTypeCount

    override fun isEmpty() = adapter.isEmpty

    override fun getWrappedAdapter() = adapter

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): VastSwipeListItemLayout? {
        return if (convertView == null) {
            val contentView = adapter.getView(position, null, parent)
            val leftMenuView = VastSwipeLeftMenu(swipeMenuMgr)
            val rightMenuView = VastSwipeRightMenu(swipeMenuMgr)
            VastSwipeListItemLayout(
                contentView, leftMenuView, rightMenuView, swipeMenuMgr
            ).apply {
                setPosition(position)
            }
        } else {
            (convertView as VastSwipeListItemLayout).apply {
                setPosition(position)
            }
        }
    }
}