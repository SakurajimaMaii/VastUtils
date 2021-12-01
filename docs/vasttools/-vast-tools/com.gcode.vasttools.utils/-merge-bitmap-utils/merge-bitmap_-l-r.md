//[VastTools](../../../index.md)/[com.gcode.vasttools.utils](../index.md)/[MergeBitmapUtils](index.md)/[mergeBitmap_LR](merge-bitmap_-l-r.md)

# mergeBitmap_LR

[androidJvm]\
fun [mergeBitmap_LR](merge-bitmap_-l-r.md)(leftBitmap: [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html), rightBitmap: [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html), isBaseMax: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)?

Merge two bitmaps into one bitmap, splicing left and right.

#### Return

null if one of the bitmaps is recycled.

## Parameters

androidJvm

| | |
|---|---|
| leftBitmap | Bitmap shown on the left. |
| rightBitmap | Bitmap shown on the right. |
| isBaseMax | Whether to take the bitmap with large width as the standard,     true means that the small image is stretched proportionally,     false means that the larger image is compressed proportionally. |
