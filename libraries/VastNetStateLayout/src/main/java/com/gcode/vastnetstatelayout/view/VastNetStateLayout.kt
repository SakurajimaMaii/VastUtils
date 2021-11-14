package com.gcode.vastnetstatelayout.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.Nullable
import com.gcode.vastnetstatelayout.R
import com.gcode.vastnetstatelayout.interfaces.VastNetErrorView
import com.gcode.vastnetstatelayout.interfaces.VastNetLoadingView
import com.gcode.vastnetstatelayout.interfaces.VastRetry

/**
 * Created by Vast Gui on 2021/11/5
 */
class VastNetStateLayout(context: Context, attrs: AttributeSet?) :
    FrameLayout(context, attrs) {
    private var mNetErrorView: VastNetErrorView? = null
    private var mNetLoadingView: VastNetLoadingView? = null
    private var netErrorClassName: String = ""
    private var netLoadingClassName: String = ""
    private var mContentState = VastNetState.CONTENT_STATE_HIDE

    /**
     * set customized network error view.
     *
     * @param netErrorView
     * @see SimpleNetErrorView
     */
    fun setNetErrorView(@Nullable netErrorView: VastNetErrorView?) {
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
    fun setNetLoadingView(@Nullable netLoadingView: VastNetLoadingView?) {
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
     * @return [VastNetState.CONTENT_STATE_SHOW_NET_ERROR],[VastNetState.CONTENT_STATE_SHOW_LOADING],[VastNetState.CONTENT_STATE_HIDE]
     */
    var contentState: VastNetState
        get() = mContentState
        set(contentState) {
            if (mNetErrorView == null) {
                mNetErrorView = try {
                    Class.forName(netErrorClassName).newInstance() as VastNetErrorView
                } catch (e: Exception) {
                    SimpleNetErrorView() //Avoid no corresponding interface display when netErrorClassName is null
                }
                addView(
                    mNetErrorView!!.getView(context),
                    LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                )
            }
            if (mNetLoadingView == null) {
                mNetLoadingView = try {
                    Class.forName(netLoadingClassName).newInstance() as VastNetLoadingView
                } catch (e: Exception) {
                    SimpleNetLoadingView() //Avoid no corresponding interface display when netLoadingClassName is null
                }
                addView(
                    mNetLoadingView!!.getView(context),
                    LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                )
            }
            mContentState = contentState
            when (contentState) {
                VastNetState.CONTENT_STATE_SHOW_NET_ERROR -> {
                    mNetErrorView?.viewShow()
                    mNetLoadingView?.viewHide()
                }
                VastNetState.CONTENT_STATE_SHOW_LOADING -> {
                    mNetErrorView?.viewHide()
                    mNetLoadingView?.viewShow()
                }
                VastNetState.CONTENT_STATE_HIDE -> {
                    mNetErrorView?.viewHide()
                    mNetLoadingView?.viewHide()
                }
            }
        }

    /**
     * When you use [SimpleNetErrorView],You can use this method
     * to define the click event of the button on the page.
     */
    fun setOnRetryClickListener(vastRetryClickListener: VastRetry.VastRetryClickListener) {
        if(mNetErrorView is SimpleNetErrorView){
            (mNetErrorView as SimpleNetErrorView).setRetryClickListener(vastRetryClickListener)
        }
    }

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.VastNetStateLayout)
        netErrorClassName = ta.getString(R.styleable.VastNetStateLayout_net_error).toString()
        netLoadingClassName = ta.getString(R.styleable.VastNetStateLayout_net_loading).toString()
        ta.recycle()
    }
}