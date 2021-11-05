package com.gcodes.vastnetstatelayout.view

/**
 * Net state
 *
 * Created by Vast Gui on 2021/11/5.
 */
enum class NetState {
    /**
     * show customized network error view
     */
    CONTENT_STATE_SHOW_NET_ERROR,
    /**
     * show customized network loading view
     */
    CONTENT_STATE_SHOW_LOADING,
    /**
     * hide customized view,show your data content
     */
    CONTENT_STATE_HIDE,
}