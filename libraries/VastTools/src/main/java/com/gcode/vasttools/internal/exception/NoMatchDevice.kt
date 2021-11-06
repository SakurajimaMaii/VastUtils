package com.gcode.vasttools.internal.exception

import com.gcode.vasttools.internal.annotation.UnderTest

/**
 * This will be thrown when **NO** aspect ratio that matches the device was found
 *
 * @author: Vast Gui
 * @date: 2021/11/6
 */
@UnderTest
class NoMatchAspectRatio(override val message: String):Throwable() {}