//[VastSwipeListView](../../../index.md)/[com.gcode.vastswipelistview.model](../index.md)/[VastSwipeMenuItem](index.md)/[VastSwipeMenuItem](-vast-swipe-menu-item.md)

# VastSwipeMenuItem

[androidJvm]\

@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()

fun [VastSwipeMenuItem](-vast-swipe-menu-item.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = context.resources.getString(R.string.default_slide_item_title), icon: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)? = ContextCompat.getDrawable(context, R.drawable.ic_null), background: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)? = ContextCompat.getDrawable(context, R.drawable.default_menu_item_background), titleColor: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = ContextCompat.getColor(context, R.color.default_menu_item_title_color), clickEvent: ([VastSwipeMenuItem](index.md), [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null)

## Parameters

androidJvm

| | |
|---|---|
| title | menu title |
| icon | menu icon |
| background | default background is grey |
| titleColor | default color is black |
| clickEvent | default value is null |
