package com.gcode.vastnetstatelayout.view

import android.content.Context
import android.view.ViewStub
import androidx.annotation.LayoutRes
import com.gcode.vastnetstatelayout.R
import com.gcode.vastnetstatelayout.interfaces.VastEmptyDataListener
import com.gcode.vastnetstatelayout.interfaces.VastLoadingErrorListener
import com.gcode.vastnetstatelayout.interfaces.VastNetErrorListener
import com.gcode.vastnetstatelayout.interfaces.VastLoadingListener

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/16
 */

/**
 * [VastNetStateMgr] mainly used to manage
 * the view of different network status
 *
 * Currently mainly supports four view:
 * **EMPTY DATA** , **NET ERROR** , **LOADING ERROR** , **LOADING**
 *
 * If you want to customize the view, you can use
 * [setEmptyDataView] , [setNetErrorView] ,
 * [setLoadingView] , [setLoadingView] ,
 * otherwise the default view will be used.
 */
class VastNetStateMgr(private val context: Context) {

    internal var loadingVs: ViewStub
        private set
    var loadingViewId = 0
        private set

    internal var netErrorRetryVs: ViewStub
        private set
    var netErrorRetryViewId = 0
        private set

    internal var emptyDataVs: ViewStub
        private set
    var emptyDataRetryViewId = 0
        private set

    internal var loadingErrorVs: ViewStub
        private set
    var loadingErrorRetryViewId = 0
        private set

    /**
     * It will be called when the layout state is [com.gcode.vastnetstatelayout.annotation.CONTENT_STATE_SHOW_LOADING].
     */
    internal var loadingListener: VastLoadingListener? = null
        private set

    /**
     * It will be called when the layout state is [com.gcode.vastnetstatelayout.annotation.CONTENT_STATE_SHOW_NET_ERROR].
     */
    internal var netErrorListener: VastNetErrorListener? = null
        private set

    /**
     * It will be called when the layout state is [com.gcode.vastnetstatelayout.annotation.CONTENT_STATE_SHOW_EMPTY_DATA].
     */
    internal var emptyDataListener: VastEmptyDataListener? = null
        private set

    /**
     * It will be called when the layout state is [com.gcode.vastnetstatelayout.annotation.CONTENT_STATE_SHOW_LOADING_ERROR].
     */
    internal var loadingErrorListener: VastLoadingErrorListener? = null
        private set

    /**
     * Set [loadingViewId] and [loadingVs]
     *
     * @param loadingViewId Your custom net loading view id.
     */
    fun setLoadingView(@LayoutRes loadingViewId: Int){
        this.loadingViewId = loadingViewId
        loadingVs = ViewStub(context).apply {
            layoutResource = loadingViewId
        }
    }

    /**
     * Set [netErrorRetryViewId] and [netErrorRetryVs]
     *
     * @param netErrorRetryViewId Your custom net error view id.
     */
    fun setNetErrorView(@LayoutRes netErrorRetryViewId:Int){
        this.netErrorRetryViewId = netErrorRetryViewId
        netErrorRetryVs = ViewStub(context).apply {
            layoutResource = netErrorRetryViewId
        }
    }

    /**
     * Set [emptyDataRetryViewId] and [emptyDataVs]
     *
     * @param emptyDataRetryViewId Your custom empty data view id.
     */
    fun setEmptyDataView(@LayoutRes emptyDataRetryViewId:Int){
        this.emptyDataRetryViewId = emptyDataRetryViewId
        emptyDataVs = ViewStub(context).apply {
            layoutResource = emptyDataRetryViewId
        }
    }

    /**
     * Set [loadingErrorRetryViewId] and [loadingErrorVs]
     *
     * @param loadingErrorRetryViewId Your custom loading error view id.
     */
    fun setLoadingErrorView(@LayoutRes loadingErrorRetryViewId:Int){
        this.loadingErrorRetryViewId = loadingErrorRetryViewId
        loadingErrorVs = ViewStub(context).apply {
            layoutResource = loadingErrorRetryViewId
        }
    }

    /**
     * You can set the view click event including
     * the following status:**NET ERROR**
     */
    fun setNetErrorListener(netErrorListener: VastNetErrorListener?){
        this.netErrorListener = netErrorListener
    }

    /**
     * You can set the view click event including
     * the following status:**LOADING**
     */
    fun setLoadingListener(loadingListener: VastLoadingListener?){
        this.loadingListener = loadingListener
    }

    /**
     * You can set the view click event including
     * the following status:**EMPTY DATA**
     */
    fun setEmptyDataListener(emptyDataListener: VastEmptyDataListener?){
        this.emptyDataListener = emptyDataListener
    }

    /**
     * You can set the view click event including
     * the following status:**LOADING ERROR**
     */
    fun setLoadingErrorListener(loadingErrorListener: VastLoadingErrorListener?){
        this.loadingErrorListener = loadingErrorListener
    }

    init {
        // Load default loading view.
        loadingVs = ViewStub(context).apply {
            layoutResource = R.layout.simple_loading_view
        }
        loadingViewId = R.layout.simple_loading_view
        // Load net error view.
        netErrorRetryVs = ViewStub(context).apply {
            layoutResource = R.layout.simple_net_error_view
        }
        netErrorRetryViewId = R.layout.simple_net_error_view
        // Load default empty data view.
        emptyDataVs = ViewStub(context).apply {
            layoutResource = R.layout.simple_empty_data_view
        }
        emptyDataRetryViewId = R.layout.simple_empty_data_view
        // Load default loading error view.
        loadingErrorVs = ViewStub(context).apply {
            layoutResource = R.layout.simple_loading_error_view
        }
        loadingErrorRetryViewId = R.layout.simple_loading_error_view
    }
}