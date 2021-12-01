//[VastTools](../../../index.md)/[com.gcode.vasttools.utils](../index.md)/[ScreenSizeUtils](index.md)/[isAllScreenDeviceApi30](is-all-screen-device-api30.md)

# isAllScreenDeviceApi30

[androidJvm]\

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)

internal fun [isAllScreenDeviceApi30](is-all-screen-device-api30.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Determine whether it is a full screen(in Api 30).

Get the aspectRatio from the [AspectRatioDeviceList](-aspect-ratio-device-list.md) by the value of [SystemUtils.deviceBrand](../-system-utils/device-brand.md), if not found, use **1.97** as the reference value.

#### Return

true if your device is full screen,false if your device is not full screen.

## Parameters

androidJvm

| | |
|---|---|
| context | Context for the transform. |
