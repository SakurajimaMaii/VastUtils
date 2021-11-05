package com.gcode.vastnetstatelayout.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.Nullable
import com.gcode.vastnetstatelayout.R
import com.gcode.vastnetstatelayout.interfaces.INetErrorView
import com.gcode.vastnetstatelayout.interfaces.INetErrorView.OnRetryClickListener
import com.gcode.vastnetstatelayout.interfaces.INetLoadingView
import com.gcodes.vastnetstatelayout.view.NetState

/**
 * Created by Vast Gui on 2021/11/5
 */
class NetStateLayout(context: Context, attrs: AttributeSet?) :
    FrameLayout(context, attrs) {
    private var mNetErrorView: INetErrorView? = null
    private var mNetLoadingView: INetLoadingView? = null
    private val netErrorClassName: String?
    private val netLoadingClassName: String?
    private var mContentState = NetState.CONTENT_STATE_HIDE

    /**
     * set customized network error view.
     *
     * @param netErrorView
     * @see SimpleNetErrorView
     */
    fun setNetErrorView(@Nullable netErrorView: INetErrorView?) {
        if (netErrorView == null) {
            return
        }
        if (mNetErrorView != null) {
            removeView(mNetErrorView!!.getView(context))
        }
        mNetErrorView = netErrorView
        addView(netErrorView.getView(context))
    }

    /**
     * set customized network loading view.
     *
     * @param netLoadingView
     * @see SimpleNetLoadingView
     */
    fun setNetLoadingView(@Nullable netLoadingView: INetLoadingView?) {
        if (netLoadingView == null) {
            return
        }
        if (mNetLoadingView != null) {
            removeView(mNetLoadingView!!.getView(context))
        }
        mNetLoadingView = netLoadingView
        addView(netLoadingView.getView(context))
    }

    /**
     * get current state
     *
     * @return [NetState.CONTENT_STATE_SHOW_NET_ERROR],[NetState.CONTENT_STATE_SHOW_LOADING],[NetState.CONTENT_STATE_HIDE]
     */
    var contentState: NetState
        get() = mContentState
        set(contentState) {
            if (mNetErrorView == null) {
                try {
                    mNetErrorView = if(netErrorClassName != null){
                        Class.forName(netErrorClassName).newInstance() as INetErrorView
                    }else{
                        SimpleNetErrorView() //Avoid no corresponding interface display when netErrorClassName is null
                    }
                    addView(
                        mNetErrorView!!.getView(context),
                        LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            if (mNetLoadingView == null) {
                try {
                    mNetLoadingView = if(netLoadingClassName != null){
                        Class.forName(netLoadingClassName).newInstance() as INetLoadingView
                    }else{
                        SimpleNetLoadingView() //Avoid no corresponding interface display when netLoadingClassName
                    }
                    addView(mNetLoadingView!!.getView(context))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            mContentState = contentState
            when (contentState) {
                NetState.CONTENT_STATE_SHOW_NET_ERROR -> {
                    mNetErrorView?.show()
                    mNetLoadingView?.hide()
                }
                NetState.CONTENT_STATE_SHOW_LOADING -> {
                    mNetErrorView?.hide()
                    mNetLoadingView?.show()
                }
                NetState.CONTENT_STATE_HIDE -> {
                    mNetErrorView?.hide()
                    mNetLoadingView?.hide()
                }
            }
        }

    fun setOnRetryClickListener(onRetryClickListener: OnRetryClickListener?) {
        if (mNetErrorView == null) {
            try {
                mNetErrorView = if(netErrorClassName != null){
                    Class.forName(netErrorClassName).newInstance() as INetErrorView
                }else{
                    SimpleNetErrorView() //Avoid no corresponding interface display when netErrorClassName is null
                }
                addView(
                    mNetErrorView!!.getView(context),
                    LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        if (mNetErrorView != null) {
            mNetErrorView!!.setRetryClickListener(onRetryClickListener)
        }
    }

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.NetStateLayout)
        netErrorClassName = ta.getString(R.styleable.NetStateLayout_net_error)
        netLoadingClassName = ta.getString(R.styleable.NetStateLayout_net_loading)
        ta.recycle()
    }
}