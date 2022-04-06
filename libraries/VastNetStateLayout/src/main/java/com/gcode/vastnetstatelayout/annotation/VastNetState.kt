@file:JvmName("VastNetState")

package com.gcode.vastnetstatelayout.annotation

import androidx.annotation.IntDef

/**
 * Net state
 *
 * Created by Vast Gui on 2021/11/5.
 */

/**
 * show customized network error view
 */
const val CONTENT_STATE_SHOW_LOADING = 0X01

/**
 * show customized network loading view
 */
const val CONTENT_STATE_SHOW_NET_ERROR = 0X02

/**
 * show customized network loading error view
 */
const val CONTENT_STATE_SHOW_LOADING_ERROR = 0X03

/**
 * show customized network empty view
 */
const val CONTENT_STATE_SHOW_EMPTY_DATA = 0X04

/**
 * show customized success view
 */
const val CONTENT_STATE_SHOW_SUCCESS = 0X05

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    CONTENT_STATE_SHOW_LOADING,
    CONTENT_STATE_SHOW_NET_ERROR,
    CONTENT_STATE_SHOW_LOADING_ERROR,
    CONTENT_STATE_SHOW_EMPTY_DATA,
    CONTENT_STATE_SHOW_SUCCESS
)
annotation class NetStateView