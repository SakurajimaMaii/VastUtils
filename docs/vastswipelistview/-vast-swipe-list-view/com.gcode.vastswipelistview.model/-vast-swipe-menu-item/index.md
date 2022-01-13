//[VastSwipeListView](../../../index.md)/[com.gcode.vastswipelistview.model](../index.md)/[VastSwipeMenuItem](index.md)

# VastSwipeMenuItem

[androidJvm]\
class [VastSwipeMenuItem](index.md)@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()constructor(**context**: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), **title**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **icon**: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?, **background**: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?, **titleColor**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **clickEvent**: ([VastSwipeMenuItem](index.md), [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?)

VastSwipeMenuItem

## Parameters

androidJvm

| | |
|---|---|
| title | menu title |
| icon | menu icon |
| background | default background is grey |
| titleColor | default color is black |
| clickEvent | default value is null |

## Constructors

| | |
|---|---|
| [VastSwipeMenuItem](-vast-swipe-menu-item.md) | [androidJvm]<br>@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()<br>fun [VastSwipeMenuItem](-vast-swipe-menu-item.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = context.resources.getString(R.string.default_slide_item_title), icon: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)? = ContextCompat.getDrawable(context, R.drawable.ic_null), background: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)? = ContextCompat.getDrawable(context, R.drawable.default_menu_item_background), titleColor: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = ContextCompat.getColor(context, R.color.default_menu_item_title_color), clickEvent: ([VastSwipeMenuItem](index.md), [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null)<br>menu title |

## Functions

| Name | Summary |
|---|---|
| [setBackgroundByColorInt](set-background-by-color-int.md) | [androidJvm]<br>fun [setBackgroundByColorInt](set-background-by-color-int.md)(@[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html)()colorInt: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html))<br>Set solid color by [colorInt](set-background-by-color-int.md) |
| [setBackgroundByDrawable](set-background-by-drawable.md) | [androidJvm]<br>fun [setBackgroundByDrawable](set-background-by-drawable.md)(background: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html))<br>Set menu background by [background](set-background-by-drawable.md). |
| [setBackgroundByResId](set-background-by-res-id.md) | [androidJvm]<br>fun [setBackgroundByResId](set-background-by-res-id.md)(@[DrawableRes](https://developer.android.com/reference/kotlin/androidx/annotation/DrawableRes.html)()backgroundResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Set background by [backgroundResId](set-background-by-res-id.md). |
| [setClickEvent](set-click-event.md) | [androidJvm]<br>fun [setClickEvent](set-click-event.md)(clickEvent: ([VastSwipeMenuItem](index.md), [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?)<br>Set click event by [clickEvent](set-click-event.md) |
| [setIconByDrawable](set-icon-by-drawable.md) | [androidJvm]<br>fun [setIconByDrawable](set-icon-by-drawable.md)(icon: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html))<br>Set menu icon by [icon](set-icon-by-drawable.md). |
| [setIconByResId](set-icon-by-res-id.md) | [androidJvm]<br>fun [setIconByResId](set-icon-by-res-id.md)(@[DrawableRes](https://developer.android.com/reference/kotlin/androidx/annotation/DrawableRes.html)()iconResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Set menu icon by [iconResId](set-icon-by-res-id.md). |
| [setIconSizeByDp](set-icon-size-by-dp.md) | [androidJvm]<br>internal fun [setIconSizeByDp](set-icon-size-by-dp.md)(@[FloatRange](https://developer.android.com/reference/kotlin/androidx/annotation/FloatRange.html)(from = 0.0.toDouble())iconSizeDp: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html))<br>Set iconSize by [iconSizeDp](set-icon-size-by-dp.md). |
| [setTitleByResId](set-title-by-res-id.md) | [androidJvm]<br>fun [setTitleByResId](set-title-by-res-id.md)(@[StringRes](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)()titleRes: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Set menu title by [titleRes](set-title-by-res-id.md). |
| [setTitleByString](set-title-by-string.md) | [androidJvm]<br>fun [setTitleByString](set-title-by-string.md)(title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Set menu title by [title](set-title-by-string.md). |
| [setTitleColorByColorInt](set-title-color-by-color-int.md) | [androidJvm]<br>fun [setTitleColorByColorInt](set-title-color-by-color-int.md)(@[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html)()colorInt: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Set titleColor by [colorInt](set-title-color-by-color-int.md). |
| [setTitleColorByResId](set-title-color-by-res-id.md) | [androidJvm]<br>fun [setTitleColorByResId](set-title-color-by-res-id.md)(@[ColorRes](https://developer.android.com/reference/kotlin/androidx/annotation/ColorRes.html)()colorResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Set titleColor by [colorResId](set-title-color-by-res-id.md). |

## Properties

| Name | Summary |
|---|---|
| [background](background.md) | [androidJvm]<br>var [background](background.md): [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?<br>Background,default background is **grey**. |
| [clickEvent](click-event.md) | [androidJvm]<br>var [clickEvent](click-event.md): ([VastSwipeMenuItem](index.md), [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?<br>Click event |
| [context](context.md) | [androidJvm]<br>private val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html) |
| [icon](icon.md) | [androidJvm]<br>var [icon](icon.md): [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?<br>Icon |
| [title](title.md) | [androidJvm]<br>var [title](title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Title |
| [titleColor](title-color.md) | [androidJvm]<br>var [titleColor](title-color.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Title color,default color is **black**. |
