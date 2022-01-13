//[VastSwipeListView](../../../index.md)/[com.gcode.vastswipelistview](../index.md)/[VastSwipeMenuMgr](index.md)/[setItems](set-items.md)

# setItems

[androidJvm]\

@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()

fun [setItems](set-items.md)(leftItems: [ArrayList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)<[VastSwipeMenuItem](../../com.gcode.vastswipelistview.model/-vast-swipe-menu-item/index.md)> = ArrayList(), rightItems: [ArrayList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)<[VastSwipeMenuItem](../../com.gcode.vastswipelistview.model/-vast-swipe-menu-item/index.md)> = ArrayList())

Set [leftMenuItems](left-menu-items.md) and [rightMenuItems](right-menu-items.md) by [menuStyle](menu-style.md).

When you call [setItems](set-items.md),if [menuStyle](menu-style.md) is [ONLY_LEFT](../../com.gcode.vastswipelistview.annotation/-vast-swipe-list-view-constant/-o-n-l-y_-l-e-f-t.md), you can not set [rightMenuItems](right-menu-items.md),or [menuStyle](menu-style.md) is [ONLY_RIGHT](../../com.gcode.vastswipelistview.annotation/-vast-swipe-list-view-constant/-o-n-l-y_-r-i-g-h-t.md) you can not set [leftMenuItems](left-menu-items.md).

#### Throws

| | |
|---|---|
| [kotlin.Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) | When [menuStyle](menu-style.md) is equals to [NOT_INIT](../../com.gcode.vastswipelistview.annotation/-vast-swipe-list-view-constant/-n-o-t_-i-n-i-t.md),     then throw an exception. |
