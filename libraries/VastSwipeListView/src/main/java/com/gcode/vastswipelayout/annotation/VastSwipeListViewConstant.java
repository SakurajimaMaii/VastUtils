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
    /**
     * Using when you want to set swipe menu content style.
     */
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            ONLY_TITLE,ONLY_ICON,ICON_TITLE
    })
    public @interface SwipeMenuContentStyle {}

    /**
     * Swipe menu close state.
     */
    public final static int STATE_CLOSE = 0;
    /**
     * Swipe menu right open state.
     */
    public final static int STATE_RIGHT_OPEN = -1;
    /**
     * Swipe menu left open state.
     */
    public final static int STATE_LEFT_OPEN = 1;

    /**
     * Not init
     */
    public final static int NOT_INIT = -1;
    /**
     * Only right have menu.
     */
    public final static int ONLY_RIGHT = 0;
    /**
     * Only left have menu.
     */
    public final static int ONLY_LEFT = 1;
    /**
     * Left and right have menu.
     */
    public final static int LEFT_RIGHT = 2;
    /**
     * Using when you want to set swipe menu style.
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            NOT_INIT,ONLY_LEFT,ONLY_RIGHT,LEFT_RIGHT
    })
    public @interface SwipeMenuStyle {}

    /**
     * @hide
     */
    public final static int SWIPE_LEFT = STATE_RIGHT_OPEN;

    /**
     * @hide
     */
    public final static int SWIPE_RIGHT = STATE_LEFT_OPEN;

    /**
     * @hide
     */
    public final static int SWIPE_NONE = 0;

    /**
     * @hide
     *
     * Use when set the swipe orientation
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            SWIPE_NONE,SWIPE_LEFT,SWIPE_RIGHT
    })
    public @interface SwipeMenuOrientation {}
}
