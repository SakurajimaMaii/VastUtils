//[VastTools](../../../index.md)/[com.gcode.vasttools.utils](../index.md)/[MergeBitmapUtils](index.md)/[mergeBitmap_TB](merge-bitmap_-t-b.md)

# mergeBitmap_TB

[androidJvm]\
fun [mergeBitmap_TB](merge-bitmap_-t-b.md)(topBitmap: [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html), bottomBitmap: [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html), isBaseMax: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)?

Merge two bitmaps into one bitmap, splicing up and down.

#### Return

null if one of the bitmaps is recycled.

## Parameters

androidJvm

| | |
|---|---|
| topBitmap | Bitmap shown on the top. |
| bottomBitmap | Bitmap shown on the bottom. |
| isBaseMax | Whether to take the bitmap with a large height as the standard,     true means that the small image is stretched proportionally,     false means that the larger image is compressed proportionally |
