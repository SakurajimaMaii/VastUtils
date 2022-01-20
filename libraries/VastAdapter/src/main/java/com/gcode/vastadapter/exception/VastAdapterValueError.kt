package com.gcode.vastadapter.exception

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/12/20
 */
internal data class VastAdapterValueError(override val message: String?):Throwable(message){}
