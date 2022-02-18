package com.gcode.vasttools.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

object ToastUtils {
    /**
     * @param context Context
     * @param msg String
     */
    @JvmStatic
    fun showShortMsg(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * @param context Context
     * @param msg String
     */
    @JvmStatic
    fun showLongMsg(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}

fun Context.showShortMsg(msg:String) = ToastUtils.showShortMsg(this,msg)

fun Context.showShortMsg(@StringRes id:Int) = ToastUtils.showShortMsg(
    this,this.resources.getString(id)
)

fun Context.showLongMsg(msg:String) = ToastUtils.showShortMsg(this,msg)

fun Context.showLongMsg(@StringRes id:Int) = ToastUtils.showShortMsg(
    this,this.resources.getString(id)
)