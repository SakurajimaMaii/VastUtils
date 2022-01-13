//[VastSwipeListView](../../../index.md)/[com.gcode.vastswipelistview](../index.md)/[VastSwipeMenuMgr](index.md)/[addRightMenuItems](add-right-menu-items.md)

# addRightMenuItems

[androidJvm]\
fun [addRightMenuItems](add-right-menu-items.md)(rightMenuItems: [ArrayList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)<[VastSwipeMenuItem](../../com.gcode.vastswipelistview.model/-vast-swipe-menu-item/index.md)>)

Set [VastSwipeMenuMgr.rightMenuItems](right-menu-items.md) when [menuStyle](menu-style.md) is not [ONLY_LEFT](../../com.gcode.vastswipelistview.annotation/-vast-swipe-list-view-constant/-o-n-l-y_-l-e-f-t.md).

**Note:** That this will clear the previous settings

#### Throws

| | |
|---|---|
| [com.gcode.vastswipelistview.exception.NotInit](../../com.gcode.vastswipelistview.exception/-not-init/index.md) | When [menuStyle](menu-style.md) is equals to [NOT_INIT](../../com.gcode.vastswipelistview.annotation/-vast-swipe-list-view-constant/-n-o-t_-i-n-i-t.md),     then throw an exception. |
