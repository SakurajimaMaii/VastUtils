package com.gcode.vastadapter.base

import android.view.View

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/30
 */

// typealias click event.
typealias VAapClickEvent = ((view: View, pos: Int) -> Unit)?
// typealias long click event.
typealias VAdpLongClickEvent = ((view: View, pos: Int) -> Boolean)?