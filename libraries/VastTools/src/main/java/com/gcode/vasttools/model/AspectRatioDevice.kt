package com.gcode.vasttools.model

import com.gcode.vasttools.annotation.UnderTest

/**
 * Aspect ratio device
 *
 * When you want to add the aspect ratio of the mobile device screen,
 * you need use [AspectRatioDevice]
 *
 * @property tag Your device tag.
 * @property aspectRatio Device aspect ratio that matches.
 *
 * @author Vast Gui
 * @date 2021/11/6
 */
@UnderTest
data class AspectRatioDevice(val tag:String,val aspectRatio: Float)