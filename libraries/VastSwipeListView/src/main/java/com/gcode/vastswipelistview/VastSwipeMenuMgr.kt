package com.gcode.vastswipelistview

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.MotionEvent
import android.view.animation.Interpolator
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import com.gcode.vastswipelistview.annotation.VastSwipeListViewConstant
import com.gcode.vastswipelistview.annotation.VastSwipeListViewConstant.*
import com.gcode.vastswipelistview.exception.NotInit
import com.gcode.vastswipelistview.model.VastSwipeMenuItem
import kotlin.jvm.Throws

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/29
 */

/**
 * VastSwipeMenuMgr
 *
 * By using the [VastSwipeMenuMgr],you can set the menu content style,
 * title size,icon size,leftMenuItems,rightMenuItems,menu open duration,
 * menu close duration,open distance threshold,close distance threshold,
 * open interpolator,close interpolator and eventListener.For example:
 *
 * ```kotlin
 * //Firstly you must set the swipeMenuStyle.It is most important.
 * vastSwipeMenuMgr.setSwipeMenuStyle(LEFT_RIGHT)
 *
 * //Secondly,you need to set the menu items;
 * //leftMenuItems is an array of the VastSwipeMenuItem
 * vastSwipeMenuMgr.setLeftMenuItems(leftMenuItems)
 *
 * //Thirdly,if you just want to show the icon,you can do like this
 * vastSwipeMenuMgr.setSwipeMenuContentStyle(ONLY_ICON)
 *
 * //You can define eventListener by this way
 * vastSwipeMenuMgr.setEventListener(object : VastSwipeMenuMgr.EventListener {
 *      override fun eventDownListener(position: Int, event: MotionEvent) {
 *          Log.d(tag,"eventDownListener")
 *      }
 *      override fun eventMoveListener(position: Int, event: MotionEvent) {
 *          Log.d(tag,"eventMoveListener")
 *      }
 *
 *      override fun eventUpListener(position: Int, event: MotionEvent) {
 *          Log.d(tag,"eventUpListener")
 *      }
 * })
 *
 * //Now VastSwipeMenuMgr is ready to be used,you should do this
 * vastSwipeListView.setSwipeMenuMgr(vastSwipeMenuMgr)
 * ```
 */
class VastSwipeMenuMgr @JvmOverloads constructor(
    context: Context,
    leftMenuItems: ArrayList<VastSwipeMenuItem> = ArrayList(),
    rightMenuItems: ArrayList<VastSwipeMenuItem> = ArrayList()
){
    var context:Context = context
        private set

    /**
     * An array of [VastSwipeMenuItem] item on the left.
     */
    var leftMenuItems:ArrayList<VastSwipeMenuItem> = leftMenuItems
        private set

    /**
     * An array of [VastSwipeMenuItem] item on the right.
     */
    var rightMenuItems:ArrayList<VastSwipeMenuItem> = rightMenuItems
        private set

    /**
     * Title size,default font size is **15sp**.
     */
    var titleSize = context.resources.getDimension(R.dimen.default_menu_item_title_size)
        private set

    /**
     * Icon size,default font size is **30dp**.
     */
    var iconSize = context.resources.getDimension(R.dimen.default_menu_item_icon_size)
        private set

    /**
     * Menu content style
     *
     * @see VastSwipeListViewConstant.ONLY_ICON
     * @see VastSwipeListViewConstant.ONLY_TITLE
     * @see VastSwipeListViewConstant.ICON_TITLE
     */
    var swipeMenuContentStyle:String = ICON_TITLE
        private set

    /**
     * Swipe menu open duration(default value is **300**).
     */
    var menuOpenDuration:Int = 300
        private set

    /**
     * Swipe menu close duration(default value is **300**).
     */
    var menuCloseDuration:Int = 300
        private set

    /**
     * Close interpolator.
     */
    var closeInterpolator: Interpolator? = null
        private set

    /**
     * Open interpolator.
     */
    var openInterpolator: Interpolator? = null
        private set

    /**
     * Menu style.Default value is [ONLY_RIGHT]
     *
     * @see VastSwipeListViewConstant.NOT_INIT
     * @see VastSwipeListViewConstant.ONLY_RIGHT
     * @see VastSwipeListViewConstant.ONLY_LEFT
     * @see VastSwipeListViewConstant.LEFT_RIGHT
     */
    var menuStyle:Int = NOT_INIT
        private set

    /**
     * Lets you to define the swipe events.
     */
    var eventListener: EventListener? = null
        private set

    /**
     * When the distance(in pixels) you swipe to the right on the screen
     * is greater than the half of [leftMenuOpenDistanceThreshold],the
     * left menu will be opened.If [leftMenuOpenDistanceThreshold] takes
     * the default value of -1,The distance threshold value will be set
     * to the half of the contentView width.When you set it,it should be
     * set to a value between 0 and the screen width.
     */
    var leftMenuOpenDistanceThreshold:Int = -1
        private set

    /**
     * When the distance(in pixels) you swipe to the left on the screen
     * is greater than the half of [rightMenuOpenDistanceThreshold],the
     * right menu will be opened.If [rightMenuOpenDistanceThreshold] takes
     * the default value of -1,The distance threshold value will be set
     * to the half of the contentView width.When you set it,it should be
     * set to a value between 0 and the screen width.
     */
    var rightMenuOpenDistanceThreshold:Int = -1
        private set

    /**
     * Set [leftMenuItems] and [rightMenuItems] by [menuStyle].
     *
     * When you call [setItems],if [menuStyle] is [ONLY_LEFT],
     * you can not set [rightMenuItems],or [menuStyle] is [ONLY_RIGHT]
     * you can not set [leftMenuItems].
     *
     * @throws Throwable When [menuStyle] is equals to [NOT_INIT],
     *                   then throw an exception.
     */
    @JvmOverloads
    @Throws(Throwable::class)
    fun setItems(
        leftItems:ArrayList<VastSwipeMenuItem> = ArrayList(),
        rightItems:ArrayList<VastSwipeMenuItem> = ArrayList(),
    ){
        when(menuStyle){
            NOT_INIT->{
                throw Throwable("You should set swipeMenuStyle")
            }
            ONLY_RIGHT -> {
                leftMenuItems.clear()
                rightMenuItems = rightItems
            }
            ONLY_LEFT ->{
                leftMenuItems = leftItems
                rightMenuItems.clear()
            }
            LEFT_RIGHT -> {
                leftMenuItems = leftItems
                rightMenuItems = rightItems
            }
        }
    }

    /**
     * Set [VastSwipeMenuMgr.leftMenuItems] when
     * [menuStyle] is not [ONLY_RIGHT].
     *
     * **Note:**
     * That this will clear the previous settings
     *
     * @throws NotInit When [menuStyle] is equals to [NOT_INIT],
     *                 then throw an exception.
     */
    @Throws(NotInit::class)
    fun setLeftMenuItems(leftMenuItems: ArrayList<VastSwipeMenuItem>) {
        when(menuStyle){
            NOT_INIT->{
                throw NotInit("You should set swipeMenuStyle")
            }
            ONLY_RIGHT->{
                return
            }
            else ->{
                this.leftMenuItems.clear()
                this.leftMenuItems = leftMenuItems
            }
        }
    }

    /**
     * Set [VastSwipeMenuMgr.rightMenuItems] when
     * [menuStyle] is not [ONLY_LEFT].
     *
     * **Note:**
     * That this will clear the previous settings
     *
     * @throws NotInit When [menuStyle] is equals to [NOT_INIT],
     *                 then throw an exception.
     */
    @Throws(NotInit::class)
    fun addRightMenuItems(rightMenuItems: ArrayList<VastSwipeMenuItem>){
        when(menuStyle){
            NOT_INIT->{
                throw NotInit("You should set swipeMenuStyle")
            }
            ONLY_LEFT->{
                return
            }
            else ->{
                this.rightMenuItems.clear()
                this.rightMenuItems = rightMenuItems
            }
        }
    }

    /**
     * Set [VastSwipeMenuMgr.swipeMenuContentStyle].
     */
    fun setSwipeMenuContentStyle(@VastSwipeListViewConstant.SwipeMenuContentStyle swipeMenuContentStyle:String){
        this.swipeMenuContentStyle = swipeMenuContentStyle
    }

    /**
     * Set [VastSwipeMenuMgr.menuOpenDuration]
     */
    fun setMenuOpenDuration(@IntRange(from = 0) openDuration:Int){
        menuOpenDuration = openDuration
    }

    /**
     * Set [VastSwipeMenuMgr.menuCloseDuration]
     */
    fun setMenuCloseDuration(@IntRange(from = 0) closeDuration:Int){
        menuCloseDuration = closeDuration
    }

    /**
     * Set [titleSize] by [titleSize].
     */
    fun setTitleSizeByFloat(@FloatRange(from = 0.0) titleSize:Float){
        this.titleSize = titleSize
    }

    /**
     * Set [titleSize] by [titleSizeSp].
     */
    fun setTitleSizeBySp(@FloatRange(from = 0.0) titleSizeSp: Float){
        this.titleSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            titleSize,
            Resources.getSystem().displayMetrics
        )
    }

    /**
     * Set iconSize by [iconSize].
     */
    fun setIconSizeByFloat(@FloatRange(from = 0.0) iconSize:Float){
        this.iconSize = iconSize
    }

    /**
     * Set iconSize by [iconSizeDp].
     */
    fun setIconSizeByDp(@FloatRange(from = 0.0) iconSizeDp:Float){
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            iconSizeDp,
            Resources.getSystem().displayMetrics
        )
    }

    /**
     * Set close interpolator.
     */
    fun setCloseInterpolator(interpolator: Interpolator?) {
        closeInterpolator = interpolator
    }

    /**
     * Set open interpolator.
     */
    fun setOpenInterpolator(interpolator: Interpolator?) {
        openInterpolator = interpolator
    }

    /**
     * Set [VastSwipeMenuMgr.menuStyle].
     */
    fun setMenuStyle(@VastSwipeListViewConstant.MenuStyle menuStyle: Int){
        this.menuStyle = menuStyle
    }

    /**
     * Set [VastSwipeMenuMgr.eventListener]
     */
    fun setEventListener(eventListener: EventListener){
        this.eventListener = eventListener
    }

    /**
     * Set [leftMenuOpenDistanceThreshold] (in pixels)
     */
    fun setLeftMenuOpenDistanceThreshold(@IntRange(from = 0) distance: Int){
        leftMenuOpenDistanceThreshold = distance
    }

    /**
     * Set [rightMenuOpenDistanceThreshold] (in pixels)
     */
    fun setRightMenuOpenDistanceThreshold(@IntRange(from = 0) distance: Int){
        rightMenuOpenDistanceThreshold = distance
    }

    interface EventListener {
        /**
         * @param position The position of the list item you touch.
         */
        fun eventDownListener(position: Int,event: MotionEvent)

        /**
         * @param position The position of the list item you touch.
         */
        fun eventMoveListener(position: Int,event: MotionEvent)

        /**
         * @param position The position of the list item you touch.
         */
        fun eventUpListener(position: Int,event: MotionEvent)
    }
}