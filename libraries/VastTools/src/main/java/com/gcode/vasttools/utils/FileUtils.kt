/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:JvmName("FileUtils")

package com.gcode.vasttools.utils

import android.content.Context
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.net.URI


// Author: SakurajimaMai
// Email: guihy2019@gmail.com
// Date: 2022/5/30
// Description: 
// Documentation:

const val FILE_UTILS_TAG = "FileUtils"

/**
 * File operations result.
 *
 * [FLAG_SUCCESS] means running successful.
 *
 * [FLAG_PARENT_NOT_EXISTS] means the parent of the file is not exist.
 *
 * [FLAG_EXISTS] means the file or directory is exist.
 *
 * [FLAG_NOT_EXISTS] means the file or directory is not exist.
 *
 * [FLAG_FAILED] means running failed.
 */
enum class FileOperationsResult {
    FLAG_SUCCESS,
    FLAG_PARENT_NOT_EXISTS,
    FLAG_EXISTS,
    FLAG_NOT_EXISTS,
    FLAG_FAILED
}

/** @since 0.0.9 */
fun Context.appInternalFilesDir(): File = this.filesDir

/** @since 0.0.9 */
fun Context.appInternalCacheDir(): File = this.cacheDir

/** @since 0.0.9 */
fun Context.appExternalFilesDir(path: String) = this.getExternalFilesDir(path)

/** @since 0.0.9 */
fun Context.appExternalCacheDir() = this.externalCacheDir

/**
 * Save file.If the file is exist,the original file
 * will be deleted and create a new file.
 *
 * For example,if you want create a file named test.txt and write Hello
 * World into this file.
 *
 * ```
 * fileSave(appInternalFilesDir().path,"test.txt",object :WriteEventListener{
 *      override fun writeEvent(fileWriter: FileWriter) {
 *          fileWriter.write("Hello World")
 *      }
 * })
 * ```
 *
 * @param saveParent The dir you want to save.
 * @param saveChild The name of the file.
 * @param writeListener Register a listener when write to file.
 *
 * @since 0.0.9
 */
@JvmOverloads
fun saveFile(
    saveParent: String,
    saveChild: String? = null,
    writeListener: WriteEventListener? = null
) {
    if (saveChild != null) {
        File(saveParent, saveChild).realFileWrite(writeListener)
    } else {
        File(saveParent).realFileWrite(writeListener)
    }
}

/**
 * Save file to [saveUri]
 *
 * @param saveUri The uri you want to save.
 * @param writeListener Register a listener when write to file.
 *
 * @since 0.0.9
 */
@JvmOverloads
fun saveFile(saveUri: URI, writeListener: WriteEventListener? = null) {
    File(saveUri).realFileWrite(writeListener)
}

/**
 * Save file.
 *
 * @param file The file you want to save.
 * @param writeListener Register a listener when write to file.
 *
 * @since 0.0.9
 */
@JvmOverloads
fun saveFile(file:File,writeListener: WriteEventListener? = null){
    file.realFileWrite(writeListener)
}

fun deleteFile(file: File):FileOperationsResult{
    return if(file.isFile){
        if(file.delete()){
            FileOperationsResult.FLAG_SUCCESS
        }else{
            FileOperationsResult.FLAG_FAILED
        }
    }else{
        FileOperationsResult.FLAG_FAILED
    }
}

/**
 * Make directory.
 *
 * For example,if you want create a directory named vast.
 * ```
 * directoryMake(appInternalFilesDir().path+"/"+"vast")
 * // or
 * directoryMake(appInternalFilesDir().path,"vast")
 * ```
 *
 * @param dirPath The path of the directory.
 * @return Operations result.
 *
 * @since 0.0.9
 */
@JvmOverloads
fun makeDir(dirPath: String, dirName: String? = null): FileOperationsResult {
    val dir = if (null != dirName) {
        File(dirPath, dirName)
    } else File(dirPath)
    if (dir.exists()) {
        return FileOperationsResult.FLAG_EXISTS
    }
    val path = if (!dir.path.endsWith(File.separator)) {
        dir.path + File.separator
    } else dir.path
    if (File(path).mkdir()) {
        return FileOperationsResult.FLAG_SUCCESS
    }
    return FileOperationsResult.FLAG_FAILED
}

/**
 * Delete directory.
 *
 * @param dirPath The path of the directory.
 * @param dirName The name of the directory.
 * @return Operations result.
 *
 * @since 0.0.9
 */
fun deleteDir(dirPath: String, dirName: String? = null):FileOperationsResult{
    val dir = if (null != dirName) {
        File(dirPath, dirName)
    } else File(dirPath)
    return deleteDir(dir)
}

/**
 * Delete directory.
 *
 * @param file The directory you want to delete.
 * @return Operations result.
 *
 * @since 0.0.9
 */
fun deleteDir(file:File):FileOperationsResult{
    if(!file.exists()){
        return FileOperationsResult.FLAG_FAILED
    }
    for(f in file.listFiles()!!){
        if(f.isFile){
            f.delete()
        }else if(f.isDirectory){
            deleteDir(f)
        }
    }
    file.delete()
    return FileOperationsResult.FLAG_SUCCESS
}

/**
 * Renaming file or directory.
 *
 * @param file The file or directory you want to rename.
 * @param newName The new name.
 * @return Operations result.
 *
 * @since 0.0.9
 */
fun rename(file:File,newName:String):FileOperationsResult{
    if(!file.exists()){
        return FileOperationsResult.FLAG_NOT_EXISTS
    }else if(newName == file.name){
        return FileOperationsResult.FLAG_SUCCESS
    }else{
        return if(null == file.parent){
            FileOperationsResult.FLAG_FAILED
        }else{
            val newFile = File(file.parent!! + File.separator + newName)
            if(file.renameTo(newFile)){
                FileOperationsResult.FLAG_SUCCESS
            }else{
                FileOperationsResult.FLAG_FAILED
            }
        }
    }
}

/**
 * Register a listener when write to file.
 *
 * @since 0.0.9
 */
interface WriteEventListener {
    /**
     * Define write event.
     *
     * @param fileWriter Convenience class for writing character files.
     * @since 0.0.9
     */
    fun writeEvent(fileWriter: FileWriter)
}

/** @since 0.0.9 */
internal fun File.realFileWrite(writeListener: WriteEventListener?) {
    if (this.exists() && this.isFile) {
        this.delete()
    }
    if (!this.parentFile?.exists()!!)
        this.parentFile?.mkdirs()
    var fileWriter: FileWriter? = null
    try {
        fileWriter = FileWriter(this)
        writeListener?.writeEvent(fileWriter)
        fileWriter.close()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        if (fileWriter != null) {
            try {
                fileWriter.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}