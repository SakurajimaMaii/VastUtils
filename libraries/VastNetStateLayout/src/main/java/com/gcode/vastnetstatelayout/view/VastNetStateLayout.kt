/*
 * MIT License
 *
 * Copyright (c) 2021 码上夏雨
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.gcode.vastnetstatelayout.view

import android.content.Context
import android.util.AttributeSet
import android.util.SparseArray
import android.view.View
import android.widget.FrameLayout
import com.gcode.vastnetstatelayout.interfaces.BaseNetStateLayout

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2021/11/5
// Description:
// Documentation:

/**
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
 * vastNetStateMgr.setLoadingErrorListener(object :VastLoadingErrorListener{
 *          override fun onLoadingError() {
 *                  // Something to do when loading error
 *          }
 * })
 * mNetStateLayout.setVastNetStateMgr(vastNetStateMgr)
 * ```
 *
 * If you don't set the [vastNetStateMgr],it will take the default value
 * when you call a method like [showLoading]
 */
class VastNetStateLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,defStyleAttr:Int = 0
) : FrameLayout(context, attrs, defStyleAttr),BaseNetStateLayout {
    /**
     * Set default vastNetStateMgr.
     */
    lateinit var vastNetStateMgr:VastNetStateMgr
        private set

    /**
     * Layout sparse array.
     */
    private val layoutSparseArray: SparseArray<View> = SparseArray<View>()

    override fun setVastNetStateMgr(mgr: VastNetStateMgr){
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

    override fun showLoading(){
        if(!::vastNetStateMgr.isInitialized){
            vastNetStateMgr = VastNetStateMgr(context)
            addNetStateView()
        }
        if(inflateSvLayout(CONTENT_STATE_SHOW_LOADING)){
            showHideViewById(CONTENT_STATE_SHOW_LOADING)
        }
    }

    override fun showNetError(){
        if(!::vastNetStateMgr.isInitialized){
            vastNetStateMgr = VastNetStateMgr(context)
            addNetStateView()
        }
        if(inflateSvLayout(CONTENT_STATE_SHOW_NET_ERROR)){
            showHideViewById(CONTENT_STATE_SHOW_NET_ERROR)
        }
    }

    override fun showLoadingError(){
        if(!::vastNetStateMgr.isInitialized){
            vastNetStateMgr = VastNetStateMgr(context)
            addNetStateView()
        }
        if(inflateSvLayout(CONTENT_STATE_SHOW_LOADING_ERROR)){
            showHideViewById(CONTENT_STATE_SHOW_LOADING_ERROR)
        }
    }

    override fun showEmptyData(){
        if(!::vastNetStateMgr.isInitialized){
            vastNetStateMgr = VastNetStateMgr(context)
            addNetStateView()
        }
        if(inflateSvLayout(CONTENT_STATE_SHOW_EMPTY_DATA)){
            showHideViewById(CONTENT_STATE_SHOW_EMPTY_DATA)
        }
    }

    override fun showSuccess(){
        if(!::vastNetStateMgr.isInitialized){
            vastNetStateMgr = VastNetStateMgr(context)
            addNetStateView()
        }
        showHideViewById(CONTENT_STATE_SHOW_SUCCESS)
    }

    /**
     * Show or hide view according to the [layoutId]
     */
    private fun showHideViewById(@NetStateView layoutId: Int) {
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
    private fun inflateSvLayout(@NetStateView layoutId:Int):Boolean{
        var isShow = true
        when(layoutId){
            CONTENT_STATE_SHOW_LOADING->{
                isShow = run {
                    val view = vastNetStateMgr.loadingVs.inflate()
                    view.setOnClickListener {
                        vastNetStateMgr.loadingListener?.onLoading()
                    }
                    layoutSparseArray.put(layoutId, view)
                    true
                }
            }
            CONTENT_STATE_SHOW_NET_ERROR->{
                isShow = run {
                    val view = vastNetStateMgr.netErrorRetryVs.inflate()
                    view.setOnClickListener {
                        vastNetStateMgr.netErrorListener?.onNetWorkError()
                    }
                    layoutSparseArray.put(layoutId,view)
                    true
                }
            }
            CONTENT_STATE_SHOW_LOADING_ERROR->{
                isShow = run {
                    val view = vastNetStateMgr.loadingErrorVs.inflate()
                    view.setOnClickListener {
                        vastNetStateMgr.loadingErrorListener?.onLoadingError()
                    }
                    layoutSparseArray.put(layoutId,view)
                    true
                }
            }
            CONTENT_STATE_SHOW_EMPTY_DATA ->{
                isShow = run {
                    val view = vastNetStateMgr.emptyDataVs.inflate()
                    view.setOnClickListener {
                        vastNetStateMgr.emptyDataListener?.onEmptyData()
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