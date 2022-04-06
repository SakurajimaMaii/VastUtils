package com.gcode.vastadapter.interfaces

import com.gcode.vastadapter.base.VastAdapter
import com.gcode.vastadapter.base.VastAdapterVH

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/19
 */

/**
 * Please make sure that your list class implement [VastBindAdapterItem]
 * when you use [VastAdapter]
 */
interface VastAdapterItem {

    fun setVAapClickEventListener(l: VAapClickEventListener?)

    fun getVAapClickEventListener(): VAapClickEventListener?

    fun setVAdpLongClickEventListener(l: VAdpLongClickEventListener?)

    fun getVAdpLongClickEventListener(): VAdpLongClickEventListener?

    /**
     * @return A string which is same as the value you set in [VastAdapterVH.BVAdpVHFactory.getVAdpVHType].
     *         In this way, the data item can be matched to the corresponding ViewHolder.
     */
    fun getVAdpItemType(): String

}