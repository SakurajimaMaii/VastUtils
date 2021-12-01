//[VastTools](../../../index.md)/[com.gcode.vasttools.utils](../index.md)/[ScreenSizeUtils](index.md)

# ScreenSizeUtils

[androidJvm]\
object [ScreenSizeUtils](index.md)

Screen size utils

Get your device screen size

## Functions

| Name | Summary |
|---|---|
| [addDevice](add-device.md) | [androidJvm]<br>fun [addDevice](add-device.md)(vararg devices: [AspectRatioDevice](../../com.gcode.vasttools.model/-aspect-ratio-device/index.md)) |
| [getMobileScreenHeight](get-mobile-screen-height.md) | [androidJvm]<br>fun [getMobileScreenHeight](get-mobile-screen-height.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get mobile screen height. |
| [getMobileScreenHeightApi30](get-mobile-screen-height-api30.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>internal fun [getMobileScreenHeightApi30](get-mobile-screen-height-api30.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get mobile screen height Api 30 Above |
| [getMobileScreenHeightApi30Down](get-mobile-screen-height-api30-down.md) | [androidJvm]<br>internal fun [getMobileScreenHeightApi30Down](get-mobile-screen-height-api30-down.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get mobile screen height Api 30 Down |
| [getMobileScreenHeightApi31](get-mobile-screen-height-api31.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 31)<br>internal fun [getMobileScreenHeightApi31](get-mobile-screen-height-api31.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get mobile screen height Api 30 Above |
| [getMobileScreenWidth](get-mobile-screen-width.md) | [androidJvm]<br>fun [getMobileScreenWidth](get-mobile-screen-width.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get mobile screen width |
| [getScreenHeight](get-screen-height.md) | [androidJvm]<br>private fun [getScreenHeight](get-screen-height.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get screen height Read the heightPixels parameter of DisplayMetrics |
| [getScreenRealHeightApi30](get-screen-real-height-api30.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>private fun [getScreenRealHeightApi30](get-screen-real-height-api30.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get screen real height(in Api 30) |
| [getScreenRealHeightApi30Down](get-screen-real-height-api30-down.md) | [androidJvm]<br>private fun [getScreenRealHeightApi30Down](get-screen-real-height-api30-down.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get screen real height Api 30 Down |
| [getScreenRealHeightApi31](get-screen-real-height-api31.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 31)<br>private fun [getScreenRealHeightApi31](get-screen-real-height-api31.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get screen real height(in Api 31) |
| [isAllScreenDevice](is-all-screen-device.md) | [androidJvm]<br>fun [isAllScreenDevice](is-all-screen-device.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Determine whether it is a full screen. |
| [isAllScreenDeviceApi30](is-all-screen-device-api30.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>internal fun [isAllScreenDeviceApi30](is-all-screen-device-api30.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Determine whether it is a full screen(in Api 30). |
| [isAllScreenDeviceApi30Down](is-all-screen-device-api30-down.md) | [androidJvm]<br>internal fun [isAllScreenDeviceApi30Down](is-all-screen-device-api30-down.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Determine whether it is a full screen(in Api 30 Down). |
| [isAllScreenDeviceApi31](is-all-screen-device-api31.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 31)<br>internal fun [isAllScreenDeviceApi31](is-all-screen-device-api31.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Determine whether it is a full screen(in Api 31). |
| [removeDevice](remove-device.md) | [androidJvm]<br>fun [removeDevice](remove-device.md)(device: [AspectRatioDevice](../../com.gcode.vasttools.model/-aspect-ratio-device/index.md))<br>Remove device by [device](remove-device.md) |
| [resetDeviceList](reset-device-list.md) | [androidJvm]<br>fun [resetDeviceList](reset-device-list.md)()<br>Reset [AspectRatioDeviceList](-aspect-ratio-device-list.md) |

## Properties

| Name | Summary |
|---|---|
| [AspectRatioDeviceList](-aspect-ratio-device-list.md) | [androidJvm]<br>private var [AspectRatioDeviceList](-aspect-ratio-device-list.md): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)<[AspectRatioDevice](../../com.gcode.vasttools.model/-aspect-ratio-device/index.md)><br>Aspect ratio mapIn order to fit the aspect ratio of more mobile devices |
| [DefaultDevice](-default-device.md) | [androidJvm]<br>private val [DefaultDevice](-default-device.md): [AspectRatioDevice](../../com.gcode.vasttools.model/-aspect-ratio-device/index.md) |
| [sRealSizes](s-real-sizes.md) | [androidJvm]<br>@[Volatile](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-volatile/index.html)()<br>private var [sRealSizes](s-real-sizes.md): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<[Point](https://developer.android.com/reference/kotlin/android/graphics/Point.html)?><br>S real sizes Read the defaultDisplay parameter in windowManager |
