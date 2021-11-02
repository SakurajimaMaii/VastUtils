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
 * @constructor Create empty Camera utils
 */
object CameraUtils {
    /**
     * Get the picture you want to show
     *
     * This is more recommended when your application Api version is **greater** than **29**
     * @param data The [Intent] returned after opening the picture selection
     * @param context
     * @return
     */
    fun getImageBitMapApi29Above(data: Intent, context: Context?): Bitmap? {
        val uri = data.data
        var image: Bitmap? = null
        if(uri != null) {
            context?.contentResolver?.openFileDescriptor(uri, "r")?.use { pfd ->
                image = BitmapFactory.decodeFileDescriptor(pfd.fileDescriptor)
            }
        }
        return image
    }

    /**
     * Get the picture you want to show
     *
     * This is more recommended when your application Api version is **less** than **29**
     * @param data The [Intent] returned after opening the picture selection
     * @param context
     * @return
     */
    private fun getImageBitMapApi29Down(data: Intent, context: Context?):Bitmap? {
        val imagePath = getImagePathApi29Down(data, context)
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
    private fun getImagePathApi29Down(data: Intent, context: Context?): String? {
        val uri = data.data
        if(uri == null){
            return null
        }else{
            var imagePath: String? = null
            if (DocumentsContract.isDocumentUri(context, uri)) {
                // If it is a document type Uri, it will be processed by document id
                val docId = DocumentsContract.getDocumentId(uri)
                if ("com.android.providers.media.documents" == uri.authority) {
                    val id = docId.split(":").toTypedArray()[1] // Parse the id in digital format
                    val selection = MediaStore.Images.Media._ID + "=" + id
                    imagePath = getImageSpecifiedPathApi29Down(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, context)
                } else if ("com.android.providers.downloads.documents" == uri.authority) {
                    val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(docId))
                    imagePath = getImageSpecifiedPathApi29Down(contentUri, null, context)
                }
            } else if ("content".equals(uri.scheme, ignoreCase = true)) {
                // If it is a content type Uri
                imagePath = getImageSpecifiedPathApi29Down(uri, null, context)
            } else if ("file".equals(uri.scheme, ignoreCase = true)) {
                // If it is a file type Uri, just get the image path directly
                imagePath = uri.path
            }
            return imagePath
        }
    }

    /**
     * Query whether there is a picture with a specified path in the gallery
     * @param uri Path URI
     * @param selection Filter condition
     * @param context
     * @return
     */
    private fun getImageSpecifiedPathApi29Down(uri: Uri?, selection: String?, context: Context?): String? {
        var path: String? = null
        // Get the real picture path through uri and selection
        val cursor: Cursor? =
            uri?.let { context?.contentResolver?.query(it, null, selection, null, null) }
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path
    }
}