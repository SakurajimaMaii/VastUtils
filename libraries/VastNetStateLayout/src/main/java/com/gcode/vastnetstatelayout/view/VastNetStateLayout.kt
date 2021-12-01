package com.gcode.vastnetstatelayout.view

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.util.SparseArray
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.gcode.vastnetstatelayout.R
import com.gcode.vastnetstatelayout.annotation.VastNetState
import com.gcode.vastnetstatelayout.annotation.VastNetState.*


/**
 * Created by Vast Gui on 2021/11/5
 *
 * [VastNetStateLayout] is a layout to set customized network state ui.
 *
 * You can use [showLoading]、[showLoadingError]、[showNetError]、[showEmptyData]
 * to show the net state view.
 *
 * If you want to customize the net state view or click events,please set
 * [vastNetStateMgr],like this:
 *
 * ```kotlin
 * val vastNetStateMgr = VastNetStateMgr(this)
 * vastNetStateMgr.setLoadingView(R.layout.simple_net_error_view)
 * vastNetStateMgr.setVastRetryClickListener(object :VastRetryClickListener{
 *     override fun onRetry() {
 *
 *     }
 * })
 * mNetStateLayout.setVastNetStateMgr(vastNetStateMgr)
 * ```
 *
 * If you don't set the [vastNetStateMgr],it will take the default value
 * when you call a method like [showLoading]
 */
class VastNetStateLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,defStyleAttr:Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    /**
     * Set default vastNetStateMgr.
     */
    lateinit var vastNetStateMgr:VastNetStateMgr
        private set

    /**
     * Layout sparse array.
     */
    private val layoutSparseArray: SparseArray<View> = SparseArray<View>()

    fun setVastNetStateMgr(mgr: VastNetStateMgr){
        vastNetStateMgr = mgr
        addNetStateView()
    }

    /**
     * Add all different state layouts to the frame layout
     */
    private fun addNetStateView(){
        vastNetStateMgr.apply {
            addView(loadingVs,LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
            addView(netErrorRetryVs,LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
            addView(emptyDataVs,LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
            addView(loadingErrorVs,LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        }
    }

    fun showLoading(){
        if(!::vastNetStateMgr.isInitialized){
            vastNetStateMgr = VastNetStateMgr(context)
            addNetStateView()
        }
        if(inflateSvLayout(CONTENT_STATE_SHOW_LOADING)){
            showHideViewById(CONTENT_STATE_SHOW_LOADING)
        }
    }

    fun showNetError(){
        if(!::vastNetStateMgr.isInitialized){
            vastNetStateMgr = VastNetStateMgr(context)
            addNetStateView()
        }
        if(inflateSvLayout(CONTENT_STATE_SHOW_NET_ERROR)){
            showHideViewById(CONTENT_STATE_SHOW_NET_ERROR)
        }
    }

    fun showLoadingError(){
        if(!::vastNetStateMgr.isInitialized){
            vastNetStateMgr = VastNetStateMgr(context)
            addNetStateView()
        }
        if(inflateSvLayout(CONTENT_STATE_SHOW_LOADING_ERROR)){
            showHideViewById(CONTENT_STATE_SHOW_LOADING_ERROR)
        }
    }

    fun showEmptyData(){
        if(!::vastNetStateMgr.isInitialized){
            vastNetStateMgr = VastNetStateMgr(context)
            addNetStateView()
        }
        if(inflateSvLayout(CONTENT_STATE_SHOW_EMPTY_DATA)){
            showHideViewById(CONTENT_STATE_SHOW_EMPTY_DATA)
        }
    }

    fun showSuccess(){
        if(!::vastNetStateMgr.isInitialized){
            vastNetStateMgr = VastNetStateMgr(context)
            addNetStateView()
        }
        showHideViewById(CONTENT_STATE_SHOW_SUCCESS)
    }

    /**
     * Show or hide view according to the [layoutId]
     */
    private fun showHideViewById(@VastNetState.NetStateView layoutId: Int) {
        for (i in 0 until layoutSparseArray.size()) {
            val key = layoutSparseArray.keyAt(i)
            val valueView = layoutSparseArray.valueAt(i)
            if (key == layoutId) {
                valueView.visibility = VISIBLE
            } else {
                if (valueView.visibility != GONE) {
                    valueView.visibility = GONE
                }
            }
        }
    }

    /**
     * Mainly to inflate the ViewStub layout,
     * such as network view, loading view, and
     * empty data view
     *
     * @return Whether the layout corresponding to layoutId is shown or not.
     */
    private fun inflateSvLayout(@VastNetState.NetStateView layoutId:Int):Boolean{
        var isShow = true
        when(layoutId){
            CONTENT_STATE_SHOW_LOADING->{
                isShow = run {
                    val view = vastNetStateMgr.loadingVs.inflate()
                    view.setOnClickListener {
                        vastNetStateMgr.vastRetryClickListener?.onRetry()
                    }
                    layoutSparseArray.put(layoutId,view)
                    true
                }
            }
            CONTENT_STATE_SHOW_NET_ERROR->{
                isShow = run {
                    val view = vastNetStateMgr.netErrorRetryVs.inflate()
                    view.setOnClickListener {
                        vastNetStateMgr.vastNetWorkClickListener?.onNetWork()
                    }
                    layoutSparseArray.put(layoutId,view)
                    true
                }
            }
            CONTENT_STATE_SHOW_LOADING_ERROR->{
                isShow = run {
                    val view = vastNetStateMgr.loadingErrorVs.inflate()
                    view.setOnClickListener {
                        vastNetStateMgr.vastRetryClickListener?.onRetry()
                    }
                    layoutSparseArray.put(layoutId,view)
                    true
                }
            }
            CONTENT_STATE_SHOW_EMPTY_DATA ->{
                isShow = run {
                    val view = vastNetStateMgr.emptyDataVs.inflate()
                    view.setOnClickListener {
                        vastNetStateMgr.vastRetryClickListener?.onRetry()
                    }
                    layoutSparseArray.put(layoutId,view)
                    true
                }
            }
            CONTENT_STATE_SHOW_SUCCESS -> {
                TODO()
            }
        }
        return isShow
    }
}