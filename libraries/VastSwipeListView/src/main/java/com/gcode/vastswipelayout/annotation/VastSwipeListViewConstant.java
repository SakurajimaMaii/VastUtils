package com.gcode.vastswipelayout.annotation;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/29
 */
public class VastSwipeListViewConstant {
    /**
     * Swipe in the x-axis direction
     */
    public final static int TOUCH_STATE_X = 1;
    /**
     * Swipe in the y-axis direction
     */
    public final static int TOUCH_STATE_Y = 2;
    /**
     * Default swipe direction.
     */
    public final static int TOUCH_STATE_NONE = 0;

    /**
     * Just show title
     */
    public final static String ONLY_TITLE = "ONLY_TITLE";
    /**
     * Just show icon
     */
    public final static String ONLY_ICON = "ONLY_ICON";
    /**
     * show title and icon
     */
    public final static String ICON_TITLE = "ICON_TITLE";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            ONLY_TITLE,ONLY_ICON,ICON_TITLE
    })
    public @interface SwipeMenuContentStyle {}

    public final static int SwipeMenuId = 520;

    /**
     * Swipe menu close state.
     */
    public final static int STATE_CLOSE = 0;
    /**
     * Swipe menu open state.
     */
    public final static int STATE_OPEN = 1;
}
