package com.gcode.tools.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

/**
 * 自定义通用工具类
 */
object MsgWindowUtils {
    /**
     * @param context Context
     * @param msg String
     */
    fun showShortMsg(context: Context,msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

    /**
     * @param context Context
     * @param msg String
     */
    fun showLongMsg(context: Context,msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
    }

    /**
     * @param context Context
     * @param msg String
     * @param title String
     */
    @JvmOverloads fun showDlgMsg(context: Context,msg:String,title:String = "提示消息"){
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(msg)
            .setPositiveButton("确定",null)
            .setNegativeButton("取消",null)
            .create().show()
    }
}