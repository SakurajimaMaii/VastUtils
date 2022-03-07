@file:JvmName("ViewModelExt")
@file:Suppress("UNCHECKED_CAST")

package com.gcode.vastactfrag.ext

import java.lang.reflect.ParameterizedType

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/2/19
 */

internal fun <VM> getVmClass(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}