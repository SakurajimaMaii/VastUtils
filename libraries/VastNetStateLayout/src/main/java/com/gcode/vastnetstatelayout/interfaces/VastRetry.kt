package com.gcode.vastnetstatelayout.interfaces

import androidx.annotation.Nullable

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/15
 */
interface VastRetry{
    fun setRetryClickListener(@Nullable retryClickListener: VastRetryClickListener?)

    /**
     * It is just used to define the button click event on the
     * [com.gcode.vastnetstatelayout.view.SimpleNetErrorView] page.
     */
    interface VastRetryClickListener {
        fun onRetryClicked()
    }
}