//[VastTools](../../../index.md)/[com.gcode.vasttools.utils](../index.md)/[ScreenSizeUtils](index.md)/[isAllScreenDevice](is-all-screen-device.md)

# isAllScreenDevice

[androidJvm]\
fun [isAllScreenDevice](is-all-screen-device.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Determine whether it is a full screen.

Get the aspectRatio from the [AspectRatioDeviceList](-aspect-ratio-device-list.md) by the value of [SystemUtils.deviceBrand](../-system-utils/device-brand.md), if not found, use **1.97** as the reference value.

#### Return

true if your device is full screen,false if your device is not full screen.

## Parameters

androidJvm

| | |
|---|---|
| context | Context for the transform. |
