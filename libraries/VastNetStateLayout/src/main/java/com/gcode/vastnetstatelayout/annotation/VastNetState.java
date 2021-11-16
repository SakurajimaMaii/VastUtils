package com.gcode.vastnetstatelayout.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Net state
 *
 * Created by Vast Gui on 2021/11/5.
 */
public class VastNetState {
    /**
     * show customized network error view
     */
    public static final int CONTENT_STATE_SHOW_LOADING = 1;
    /**
     * show customized network loading view
     */
    public static final int CONTENT_STATE_SHOW_NET_ERROR = 2;
    /**
     * show customized network loading error view
     */
    public static final int CONTENT_STATE_SHOW_LOADING_ERROR = 3;
    /**
     * show customized network empty view
     */
    public static final int CONTENT_STATE_SHOW_EMPTY_DATA= 4;
    /**
     * show customized success view
     */
    public static final int CONTENT_STATE_SHOW_SUCCESS = 5;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            CONTENT_STATE_SHOW_LOADING,
            CONTENT_STATE_SHOW_NET_ERROR,
            CONTENT_STATE_SHOW_LOADING_ERROR,
            CONTENT_STATE_SHOW_EMPTY_DATA,
            CONTENT_STATE_SHOW_SUCCESS
    })
    public @interface NetStateView {}
}