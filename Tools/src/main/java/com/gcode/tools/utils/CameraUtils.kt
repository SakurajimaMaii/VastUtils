package com.gcode.tools.utils

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore

/**
 * Camera utils
 * @see [https://www.jianshu.com/p/57487bb1ec5a]
 * @constructor Create empty Camera utils
 */
object CameraUtils {
    /**
     * Get the picture you want to show
     * @param data The [Intent] returned after opening the picture selection
     * @param context
     * @return
     */
    fun displayImage(data: Intent, context: Context?):Bitmap? {
        val imagePath = handleImageOnKitKat(data, context)
        return if (imagePath != null) {
            BitmapFactory.decodeFile(imagePath)
        }else null
    }

    /**
     * Read pictures from album
     * @param data The [Intent] returned after opening the picture selection
     * @param context
     * @return
     */
    private fun handleImageOnKitKat(data: Intent, context: Context?): String? {
        var imagePath: String? = null
        val uri = data.data
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            val docId = DocumentsContract.getDocumentId(uri)
            if ("com.android.providers.media.documents" == uri!!.authority) {
                val id = docId.split(":").toTypedArray()[1] // 解析出数字格式的id
                val selection = MediaStore.Images.Media._ID + "=" + id
                LogUtils.i(this.javaClass.simpleName, "id=$id,selection=$selection")
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, context)
            } else if ("com.android.providers.downloads.documents" == uri.authority) {
                val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(docId))
                imagePath = getImagePath(contentUri, null, context)
            }
        } else if ("content".equals(uri!!.scheme, ignoreCase = true)) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null, context)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.path
        }
        return imagePath
    }

    /**
     * Query whether there is a picture with a specified path in the gallery
     * @param uri Path URI
     * @param selection Filter condition
     * @param context
     * @return
     */
    private fun getImagePath(uri: Uri?, selection: String?, context: Context?): String? {
        var path: String? = null
        // 通过Uri和selection来获取真实的图片路径
        val cursor: Cursor? = context?.contentResolver?.query(uri!!, null, selection, null, null)
        if (cursor != null) {
            LogUtils.i(this.javaClass.simpleName, "cursor不为null  $selection")
            var i = 0
            while (i < cursor.columnCount) {
                var ss = cursor.getColumnName(i)
                i++
            }
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
                LogUtils.i(this.javaClass.simpleName, "get path= $path")
            }
            cursor.close()
        }
        return path
    }
}