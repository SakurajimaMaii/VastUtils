//[VastTools](../../../index.md)/[com.gcode.vasttools.utils](../index.md)/[CameraUtils](index.md)

# CameraUtils

[androidJvm]\
object [CameraUtils](index.md)

Camera utils

## Functions

| Name | Summary |
|---|---|
| [getImageBitMapApi29Above](get-image-bit-map-api29-above.md) | [androidJvm]<br>fun [getImageBitMapApi29Above](get-image-bit-map-api29-above.md)(data: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html), context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)?): [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)?<br>Get the picture you want to showThis is more recommended when your application Api version is **greater** than **29** |
| [getImageBitMapApi29Down](get-image-bit-map-api29-down.md) | [androidJvm]<br>fun [getImageBitMapApi29Down](get-image-bit-map-api29-down.md)(data: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html), context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)?): [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)?<br>Get the picture you want to showThis is more recommended when your application Api version is **less** than **29** |
| [getImagePathApi29Down](get-image-path-api29-down.md) | [androidJvm]<br>private fun [getImagePathApi29Down](get-image-path-api29-down.md)(data: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html), context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)?): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>Read pictures from album |
| [getImageSpecifiedPathApi29Down](get-image-specified-path-api29-down.md) | [androidJvm]<br>private fun [getImageSpecifiedPathApi29Down](get-image-specified-path-api29-down.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)?, selection: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)?): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>Query whether there is a picture with a specified path in the gallery |
