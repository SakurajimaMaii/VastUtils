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

package com.gcode.vastutils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import com.gcode.vasttools.BuildConfig
import com.gcode.vasttools.utils.getPackageName
import java.io.File
import java.io.IOException

/**
 * Camera utils
 * @constructor Create empty Camera utils
 */
object ImgUtils {

    const val TAKE_PHOTO_REQUEST_CODE = 0x01
    const val CHOICE_FROM_ALBUM_REQUEST_CODE = 0x02
    const val CROP_PHOTO_REQUEST_CODE = 0x03

    fun startCamera(activity: FragmentActivity):Bitmap?{
        val file: File = File(activity.externalCacheDir,"output_image")
        try {
            if(file.exists()){
                file.delete()
            }
            file.createNewFile()
        }catch (e: IOException){
            e.printStackTrace()
        }
        val photoUri:Uri = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            FileProvider.getUriForFile(activity,"${getPackageName(activity)}.fileProvider",file)
        }else{
            Uri.fromFile(file)
        }
        val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
        var bitmap:Bitmap? = null
        ActivityLauncher.init(activity)
            .startActivityForResult(takePhotoIntent,object : ActivityLauncher.Callback {
            override fun onActivityResult(resultCode: Int, data: Intent?) {
                bitmap = null//cropPhoto(activity, photoUri)
            }
        })
        return bitmap
    }

//    错误代码
//    fun cropPhoto(activity: FragmentActivity,photoUri:Uri):Bitmap?{
//        val cropPhotoIntent = Intent("com.android.camera.action.CROP")
//        cropPhotoIntent.setDataAndType(photoUri,"image/*")
//        cropPhotoIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//        val mOutPutFile:File
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            //android 11
//            val path: String =
//                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//                    .path
//            //storage/emulated/0/Pictures
//            mOutPutFile = File(path, System.currentTimeMillis().toString() + ".png")
//            cropPhotoIntent.putExtra(
//                MediaStore.EXTRA_OUTPUT,
//                Uri.parse("file://" + mOutPutFile.absolutePath)
//            )
//        } else {
//            //storage/emulated/0/Android/data/com.xxxxx/cache
//            val sdPath = activity.applicationContext.getExternalFilesDir(BuildConfig.LIBRARY_PACKAGE_NAME)
//                ?.absolutePath
//            mOutPutFile = File(sdPath, System.currentTimeMillis().toString() + ".png")
//            cropPhotoIntent.putExtra(
//                MediaStore.EXTRA_OUTPUT,
//                Uri.parse("file://" + mOutPutFile.absolutePath)
//            )
//        }
//        var bitmap:Bitmap? = null
//        ActivityLauncher.init(activity)
//            .startActivityForResult(cropPhotoIntent,object : ActivityLauncher.Callback {
//            override fun onActivityResult(resultCode: Int, data: Intent?) {
//                val file:File = File(mOutPutFile.path)
//                bitmap = if(file.exists()){
//                    BitmapFactory.decodeFile(mOutPutFile.path)
//                }else{
//                    null
//                }
//            }
//        })
//        return bitmap
//    }

    fun choiceFromAlbum(activity: Activity){
        val openAlbumIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activity.startActivityForResult(openAlbumIntent, CHOICE_FROM_ALBUM_REQUEST_CODE)
    }

    fun choiceFromAlbum(data: Intent,context: Context): Bitmap?{
        val uri = data.data
        var image: Bitmap? = null
        if(uri != null) {
            context.contentResolver?.openFileDescriptor(uri, "r")?.use { pfd ->
                image = BitmapFactory.decodeFileDescriptor(pfd.fileDescriptor)
            }
        }
        return image
    }
}