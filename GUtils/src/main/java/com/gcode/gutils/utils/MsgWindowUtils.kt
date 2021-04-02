package com.gcode.gutils.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

/**
 * 自定义通用工具类
 */
object MsgWindowUtils {
    /**
     * 显示短消息
     * @param context Context
     * @param msg String
     */
    fun showShortMsg(context: Context,msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

    /**
     * 显示长消息
     * @param context Context
     * @param msg String
     */
    fun showLongMsg(context: Context,msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
    }

    /**
     * 显示消息对话框
     * @param context Context
     * @param msg String
     */
    fun showDlgMsg(context: Context,msg:String){
        AlertDialog.Builder(context)
            .setTitle("提示消息")
            .setMessage(msg)
            .setPositiveButton("确定",null)
            .setNegativeButton("取消",null)
            .create().show()
    }
}