/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gcode.vastutils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import com.gcode.vasttools.utils.AppUtils.getPackageName
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