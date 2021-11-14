package com.gcode.vasttools.utils

import android.os.Build
import java.util.*

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate: 2021/11/9
 */
object SystemUtils {
    /**
     * Returns the language code of this Locale.
     *
     * For example: the current setting is "Chinese-China",
     * then "zh" will be returned
     */
    val systemLanguage: String
        get() = Locale.getDefault().language

    /**
     * Returns an array of all installed locales.
     */
    val systemLanguageList: Array<out Locale>
        get() = Locale.getAvailableLocales()

    /**
     * Get the current mobile phone android version.
     *
     * For example: "11" will be returned.
     */
    val systemAndroidVersion: String
        get() = Build.VERSION.RELEASE

    /**
     * Returns the end-user-visible name for the end product.
     */
    val systemModel: String
        get() = Build.MODEL

    /**
     * Returns the consumer-visible brand with which the product/hardware will be associated, if any.
     */
    val deviceBrand: String
        get() = Build.BRAND
}