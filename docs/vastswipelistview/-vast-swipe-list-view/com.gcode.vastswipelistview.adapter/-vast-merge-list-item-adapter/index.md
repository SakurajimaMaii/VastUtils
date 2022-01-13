//[VastSwipeListView](../../../index.md)/[com.gcode.vastswipelistview.adapter](../index.md)/[VastMergeListItemAdapter](index.md)

# VastMergeListItemAdapter

[androidJvm]\
internal open class [VastMergeListItemAdapter](index.md)(**context**: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), **adapter**: [ListAdapter](https://developer.android.com/reference/kotlin/android/widget/ListAdapter.html), **swipeMenuMgr**: [VastSwipeMenuMgr](../../com.gcode.vastswipelistview/-vast-swipe-menu-mgr/index.md)) : [WrapperListAdapter](https://developer.android.com/reference/kotlin/android/widget/WrapperListAdapter.html)

Vast merge list item adapter

Merge the ContentView and the SwipeMenuLayout to display the menu

## Constructors

| | |
|---|---|
| [VastMergeListItemAdapter](-vast-merge-list-item-adapter.md) | [androidJvm]<br>fun [VastMergeListItemAdapter](-vast-merge-list-item-adapter.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), adapter: [ListAdapter](https://developer.android.com/reference/kotlin/android/widget/ListAdapter.html), swipeMenuMgr: [VastSwipeMenuMgr](../../com.gcode.vastswipelistview/-vast-swipe-menu-mgr/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [areAllItemsEnabled](are-all-items-enabled.md) | [androidJvm]<br>open override fun [areAllItemsEnabled](are-all-items-enabled.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getAutofillOptions](index.md#-1879190565%2FFunctions%2F1677453037) | [androidJvm]<br>open fun [getAutofillOptions](index.md#-1879190565%2FFunctions%2F1677453037)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<[CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)>? |
| [getCount](get-count.md) | [androidJvm]<br>open override fun [getCount](get-count.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getItem](get-item.md) | [androidJvm]<br>open override fun [getItem](get-item.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html) |
| [getItemId](get-item-id.md) | [androidJvm]<br>open override fun [getItemId](get-item-id.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [getItemViewType](get-item-view-type.md) | [androidJvm]<br>open override fun [getItemViewType](get-item-view-type.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getView](get-view.md) | [androidJvm]<br>open override fun [getView](get-view.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), convertView: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, parent: [ViewGroup](https://developer.android.com/reference/kotlin/android/view/ViewGroup.html)): [VastSwipeListItemLayout](../../com.gcode.vastswipelistview.view/-vast-swipe-list-item-layout/index.md)? |
| [getViewTypeCount](get-view-type-count.md) | [androidJvm]<br>open override fun [getViewTypeCount](get-view-type-count.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getWrappedAdapter](get-wrapped-adapter.md) | [androidJvm]<br>open override fun [getWrappedAdapter](get-wrapped-adapter.md)(): [ListAdapter](https://developer.android.com/reference/kotlin/android/widget/ListAdapter.html) |
| [hasStableIds](has-stable-ids.md) | [androidJvm]<br>open override fun [hasStableIds](has-stable-ids.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isEmpty](is-empty.md) | [androidJvm]<br>open override fun [isEmpty](is-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isEnabled](is-enabled.md) | [androidJvm]<br>open override fun [isEnabled](is-enabled.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [registerDataSetObserver](register-data-set-observer.md) | [androidJvm]<br>open override fun [registerDataSetObserver](register-data-set-observer.md)(observer: [DataSetObserver](https://developer.android.com/reference/kotlin/android/database/DataSetObserver.html)?) |
| [unregisterDataSetObserver](unregister-data-set-observer.md) | [androidJvm]<br>open override fun [unregisterDataSetObserver](unregister-data-set-observer.md)(observer: [DataSetObserver](https://developer.android.com/reference/kotlin/android/database/DataSetObserver.html)?) |

## Properties

| Name | Summary |
|---|---|
| [adapter](adapter.md) | [androidJvm]<br>private val [adapter](adapter.md): [ListAdapter](https://developer.android.com/reference/kotlin/android/widget/ListAdapter.html)<br>The adapter of the listView. |
| [context](context.md) | [androidJvm]<br>private val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)<br>Context. |
| [swipeMenuMgr](swipe-menu-mgr.md) | [androidJvm]<br>private val [swipeMenuMgr](swipe-menu-mgr.md): [VastSwipeMenuMgr](../../com.gcode.vastswipelistview/-vast-swipe-menu-mgr/index.md)<br>The swipe menu manager. |
