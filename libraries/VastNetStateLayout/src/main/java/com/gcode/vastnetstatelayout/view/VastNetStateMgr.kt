package com.gcode.vastnetstatelayout.view

import android.content.Context
import android.view.ViewStub
import androidx.annotation.LayoutRes
import com.gcode.vastnetstatelayout.R
import com.gcode.vastnetstatelayout.interfaces.VastNetWorkClickListener
import com.gcode.vastnetstatelayout.interfaces.VastRetryClickListener

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

    internal lateinit var loadingVs: ViewStub
        private set
    var loadingViewId = 0
        private set

    internal lateinit var netErrorRetryVs: ViewStub
        private set
    var netErrorRetryViewId = 0
        private set

    internal lateinit var emptyDataVs: ViewStub
        private set
    var emptyDataRetryViewId = 0
        private set

    internal lateinit var loadingErrorVs: ViewStub
        private set
    var loadingErrorRetryViewId = 0
        private set

    /**
     * It will be called when you want
     * to set retry click event.
     */
    internal var vastRetryClickListener: VastRetryClickListener? = null
        private set

    /**
     * It will be called when you want
     * to set net work.
     */
    internal var vastNetWorkClickListener: VastNetWorkClickListener? = null
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
    fun setNetWorkListener(vastNetWorkClickListener: VastNetWorkClickListener){
        this.vastNetWorkClickListener = vastNetWorkClickListener
    }

    /**
     * You can set the view click event including
     * the following status:
     * **EMPTY DATA** , **LOADING ERROR** , **LOADING**
     */
    fun setVastRetryClickListener(vastRetryClickListener: VastRetryClickListener){
        this.vastRetryClickListener = vastRetryClickListener
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