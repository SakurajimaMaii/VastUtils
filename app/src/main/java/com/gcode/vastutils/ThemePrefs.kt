///*
// * MIT License
// *
// * Copyright (c) 2021 码上夏雨
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */
//
//package com.gcode.vastutils
//
//import com.gcode.vasttools.R
//import com.gcode.vastutils.ThemeKey.THEME_DARK_COLOR_BACKGROUND
//import com.gcode.vastutils.ThemeKey.THEME_DARK_COLOR_ERROR
//import com.gcode.vastutils.ThemeKey.THEME_DARK_COLOR_ON_BACKGROUND
//import com.gcode.vastutils.ThemeKey.THEME_DARK_COLOR_ON_ERROR
//import com.gcode.vastutils.ThemeKey.THEME_DARK_COLOR_ON_PRIMARY
//import com.gcode.vastutils.ThemeKey.THEME_DARK_COLOR_ON_SECONDARY
//import com.gcode.vastutils.ThemeKey.THEME_DARK_COLOR_ON_SURFACE
//import com.gcode.vastutils.ThemeKey.THEME_DARK_COLOR_PRIMARY
//import com.gcode.vastutils.ThemeKey.THEME_DARK_COLOR_PRIMARY_VARIANT
//import com.gcode.vastutils.ThemeKey.THEME_DARK_COLOR_SECONDARY_VARIANT
//import com.gcode.vastutils.ThemeKey.THEME_DARK_COLOR_SURFACE
//import com.gcode.vastutils.ThemeKey.THEME_LIGHT_COLOR_BACKGROUND
//import com.gcode.vastutils.ThemeKey.THEME_LIGHT_COLOR_ERROR
//import com.gcode.vastutils.ThemeKey.THEME_LIGHT_COLOR_ON_BACKGROUND
//import com.gcode.vastutils.ThemeKey.THEME_LIGHT_COLOR_ON_ERROR
//import com.gcode.vastutils.ThemeKey.THEME_LIGHT_COLOR_ON_PRIMARY
//import com.gcode.vastutils.ThemeKey.THEME_LIGHT_COLOR_ON_SECONDARY
//import com.gcode.vastutils.ThemeKey.THEME_LIGHT_COLOR_ON_SURFACE
//import com.gcode.vastutils.ThemeKey.THEME_LIGHT_COLOR_PRIMARY
//import com.gcode.vastutils.ThemeKey.THEME_LIGHT_COLOR_PRIMARY_VARIANT
//import com.gcode.vastutils.ThemeKey.THEME_LIGHT_COLOR_SECONDARY
//import com.gcode.vastutils.ThemeKey.THEME_LIGHT_COLOR_SECONDARY_VARIANT
//import com.gcode.vastutils.ThemeKey.THEME_LIGHT_COLOR_SURFACE
//
///**
// * @Author: Vast Gui @Email: guihy2019@gmail.com @Date: 2022/3/23 16:07
// * @Description: @Documentation:
// */
//
//internal object ThemeKey {
//    internal const val THEME_FILE = "com.gcode.vasttheme"
//    internal const val THEME_PATH = "com.gcode.vasttheme.path"
//
//    internal const val THEME_LIGHT = "LIGHT"
//    internal const val THEME_DARK = "DARK"
//
//    internal const val THEME_MODE = "theme_mode"
//    internal const val THEME_LIGHT_COLOR_PRIMARY = "theme_light_color_primary"
//    internal const val THEME_LIGHT_COLOR_PRIMARY_VARIANT = "theme_light_color_primary_variant"
//    internal const val THEME_LIGHT_COLOR_ON_PRIMARY = "theme_light_color_on_primary"
//    internal const val THEME_LIGHT_COLOR_SECONDARY = "theme_light_color_secondary"
//    internal const val THEME_LIGHT_COLOR_SECONDARY_VARIANT = "theme_light_color_secondary_variant"
//    internal const val THEME_LIGHT_COLOR_ON_SECONDARY = "theme_light_color_on_secondary"
//    internal const val THEME_LIGHT_COLOR_SURFACE = "theme_light_color_surface"
//    internal const val THEME_LIGHT_COLOR_ON_SURFACE = "theme_light_color_on_surface"
//    internal const val THEME_LIGHT_COLOR_BACKGROUND = "theme_light_color_background"
//    internal const val THEME_LIGHT_COLOR_ON_BACKGROUND = "theme_light_color_on_background"
//    internal const val THEME_LIGHT_COLOR_ERROR = "theme_light_color_error"
//    internal const val THEME_LIGHT_COLOR_ON_ERROR = "theme_light_color_on_error"
//
//    internal const val THEME_DARK_COLOR_PRIMARY = "theme_dark_color_primary"
//    internal const val THEME_DARK_COLOR_PRIMARY_VARIANT = "theme_dark_color_primary_variant"
//    internal const val THEME_DARK_COLOR_ON_PRIMARY = "theme_dark_color_on_primary"
//    internal const val THEME_DARK_COLOR_SECONDARY = "theme_dark_color_secondary"
//    internal const val THEME_DARK_COLOR_SECONDARY_VARIANT = "theme_dark_color_secondary_variant"
//    internal const val THEME_DARK_COLOR_ON_SECONDARY = "theme_dark_color_on_secondary"
//    internal const val THEME_DARK_COLOR_SURFACE = "theme_dark_color_surface"
//    internal const val THEME_DARK_COLOR_ON_SURFACE = "theme_dark_color_on_surface"
//    internal const val THEME_DARK_COLOR_BACKGROUND = "theme_dark_color_background"
//    internal const val THEME_DARK_COLOR_ON_BACKGROUND = "theme_dark_color_on_background"
//    internal const val THEME_DARK_COLOR_ERROR = "theme_dark_color_error"
//    internal const val THEME_DARK_COLOR_ON_ERROR = "theme_dark_color_on_error"
//}
//
//internal val colorIDs = intArrayOf(
//    R.color.vast_theme_light_primary,
//    R.color.vast_theme_light_on_primary,
//    R.color.vast_theme_light_primary_variant,
//    R.color.vast_theme_light_secondary,
//    R.color.vast_theme_light_on_secondary,
//    R.color.vast_theme_light_secondary_variant,
//    R.color.vast_theme_light_surface,
//    R.color.vast_theme_light_on_surface,
//    R.color.vast_theme_light_background,
//    R.color.vast_theme_light_on_background,
//    R.color.vast_theme_light_error,
//    R.color.vast_theme_light_on_error,
//    R.color.vast_theme_dark_primary,
//    R.color.vast_theme_dark_on_primary,
//    R.color.vast_theme_dark_primary_variant,
//    R.color.vast_theme_dark_secondary,
//    R.color.vast_theme_dark_on_secondary,
//    R.color.vast_theme_dark_secondary_variant,
//    R.color.vast_theme_dark_surface,
//    R.color.vast_theme_dark_on_surface,
//    R.color.vast_theme_dark_background,
//    R.color.vast_theme_dark_on_background,
//    R.color.vast_theme_dark_error,
//    R.color.vast_theme_dark_on_error
//)
//
//val colorID2KEY = HashMap<Int, String>().apply {
//    put(R.color.vast_theme_light_primary, THEME_LIGHT_COLOR_PRIMARY)
//    put(R.color.vast_theme_light_on_primary, THEME_LIGHT_COLOR_ON_PRIMARY)
//    put(R.color.vast_theme_light_primary_variant, THEME_LIGHT_COLOR_PRIMARY_VARIANT)
//    put(R.color.vast_theme_light_secondary, THEME_LIGHT_COLOR_SECONDARY)
//    put(R.color.vast_theme_light_on_secondary, THEME_LIGHT_COLOR_ON_SECONDARY)
//    put(R.color.vast_theme_light_secondary_variant, THEME_LIGHT_COLOR_SECONDARY_VARIANT)
//    put(R.color.vast_theme_light_surface, THEME_LIGHT_COLOR_SURFACE)
//    put(R.color.vast_theme_light_on_surface, THEME_LIGHT_COLOR_ON_SURFACE)
//    put(R.color.vast_theme_light_background, THEME_LIGHT_COLOR_BACKGROUND)
//    put(R.color.vast_theme_light_on_background, THEME_LIGHT_COLOR_ON_BACKGROUND)
//    put(R.color.vast_theme_light_error, THEME_LIGHT_COLOR_ERROR)
//    put(R.color.vast_theme_light_on_error, THEME_LIGHT_COLOR_ON_ERROR)
//    put(R.color.vast_theme_dark_primary, THEME_DARK_COLOR_PRIMARY)
//    put(R.color.vast_theme_dark_on_primary, THEME_DARK_COLOR_ON_PRIMARY)
//    put(R.color.vast_theme_dark_primary_variant, THEME_DARK_COLOR_PRIMARY_VARIANT)
//    put(R.color.vast_theme_dark_secondary, THEME_DARK_COLOR_PRIMARY_VARIANT)
//    put(R.color.vast_theme_dark_on_secondary, THEME_DARK_COLOR_ON_SECONDARY)
//    put(R.color.vast_theme_dark_secondary_variant, THEME_DARK_COLOR_SECONDARY_VARIANT)
//    put(R.color.vast_theme_dark_surface, THEME_DARK_COLOR_SURFACE)
//    put(R.color.vast_theme_dark_on_surface, THEME_DARK_COLOR_ON_SURFACE)
//    put(R.color.vast_theme_dark_background, THEME_DARK_COLOR_BACKGROUND)
//    put(R.color.vast_theme_dark_on_background, THEME_DARK_COLOR_ON_BACKGROUND)
//    put(R.color.vast_theme_dark_error, THEME_DARK_COLOR_ERROR)
//    put(R.color.vast_theme_dark_on_error, THEME_DARK_COLOR_ON_ERROR)
//}