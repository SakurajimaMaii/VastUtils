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

package com.gcode.vasttools.utils

import com.gcode.vasttools.helper.ContextHelper
import com.gcode.vasttools.utils.FileUtils.ResultSet.*
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.net.URI

// Author: SakurajimaMai
// Email: guihy2019@gmail.com
// Date: 2022/5/30
// Description: 
// Documentation:

object FileUtils {
    const val FILE_UTILS_TAG = "FileUtils"

    /**
     * File operations result.
     *
     * [FLAG_SUCCESS] means running successful.
     *
     * [FLAG_PARENT_NOT_EXISTS] means the parent of the file is not
     * exist.
     *
     * [FLAG_EXISTS] means the file or directory is exist.
     *
     * [FLAG_NOT_EXISTS] means the file or directory is not exist.
     *
     * [FLAG_FAILED] means running failed.
     */
    enum class ResultSet {
        FLAG_SUCCESS,
        FLAG_PARENT_NOT_EXISTS,
        FLAG_EXISTS,
        FLAG_NOT_EXISTS,
        FLAG_FAILED
    }

    /** @since 0.0.9 */
    @JvmStatic
    fun appInternalFilesDir(): File = ContextHelper.getAppContext().filesDir

    /** @since 0.0.9 */
    @JvmStatic
    fun appInternalCacheDir(): File = ContextHelper.getAppContext().cacheDir

    /** @since 0.0.9 */
    @JvmStatic
    fun appExternalFilesDir(path: String?) = ContextHelper.getAppContext().getExternalFilesDir(path)

    /** @since 0.0.9 */
    @JvmStatic
    fun appExternalCacheDir() = ContextHelper.getAppContext().externalCacheDir

    /**
     * Save file.If the file is exist,the original file will be deleted
     * and create a new file.
     *
     * @param saveParent The dir you want to save.
     * @param saveChild The name of the file.
     * @since 0.0.9
     */
    @JvmStatic
    @JvmOverloads
    fun saveFile(
        saveParent: String,
        saveChild: String? = null,
    ) {
        if (saveChild != null) {
            File(saveParent, saveChild).realFileWrite()
        } else {
            File(saveParent).realFileWrite()
        }
    }

    /**
     * Save file to [saveUri]
     *
     * @param saveUri The uri you want to save.
     * @since 0.0.9
     */
    @JvmStatic
    fun saveFile(saveUri: URI) {
        File(saveUri).realFileWrite()
    }

    /**
     * Save file.
     *
     * @param file The file you want to save.
     * @since 0.0.9
     */
    @JvmStatic
    fun saveFile(file: File) {
        file.realFileWrite()
    }

    /**
     * Delete file.
     *
     * @param file the file you want to delete.
     * @return [ResultSet]
     * @since 0.0.9
     */
    @JvmStatic
    fun deleteFile(file: File): ResultSet {
        return if (file.isFile) {
            if (file.delete()) {
                FLAG_SUCCESS
            } else {
                FLAG_FAILED
            }
        } else {
            FLAG_FAILED
        }
    }

    @JvmStatic
    fun writeFile(file: File, writeEventListener: WriteEventListener) {
        if ("txt" == getFileExtension(file)) {
            val fileWriter = FileWriter(file)
            writeEventListener.writeEvent(fileWriter)
            fileWriter.close()
        }
    }

    /**
     * Make directory.
     *
     * For example,if you want create a directory named vast.
     *
     * ```
     * directoryMake(appInternalFilesDir().path+"/"+"vast")
     * // or
     * directoryMake(appInternalFilesDir().path,"vast")
     * ```
     *
     * @param dirPath The path of the directory.
     * @return Operations result.
     * @since 0.0.9
     */
    @JvmOverloads
    fun makeDir(dirPath: String, dirName: String? = null): ResultSet {
        val dir = if (null != dirName) {
            File(dirPath, dirName)
        } else File(dirPath)
        if (dir.exists()) {
            return FLAG_EXISTS
        }
        val path = if (!dir.path.endsWith(File.separator)) {
            dir.path + File.separator
        } else dir.path
        if (File(path).mkdir()) {
            return FLAG_SUCCESS
        }
        return FLAG_FAILED
    }

    /**
     * Delete directory.
     *
     * @param dirPath The path of the directory.
     * @param dirName The name of the directory.
     * @return Operations result.
     * @since 0.0.9
     */
    fun deleteDir(dirPath: String, dirName: String? = null): ResultSet {
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
     * @since 0.0.9
     */
    fun deleteDir(file: File): ResultSet {
        if (!file.exists()) {
            return FLAG_FAILED
        }
        if (null == file.listFiles()) {
            file.delete()
        } else {
            for (f in file.listFiles()!!) {
                if (f.isFile) {
                    f.delete()
                } else if (f.isDirectory) {
                    deleteDir(f)
                }
            }
        }
        file.delete()
        return FLAG_SUCCESS
    }

    /**
     * Renaming file or directory.
     *
     * @param file The file or directory you want to rename.
     * @param newName The new name.
     * @return Operations result.
     * @since 0.0.9
     */
    fun rename(file: File, newName: String): ResultSet {
        if (!file.exists()) {
            return ResultSet.FLAG_NOT_EXISTS
        } else if (newName == file.name) {
            return FLAG_SUCCESS
        } else {
            return if (null == file.parent) {
                FLAG_FAILED
            } else {
                val newFile = File(file.parent!! + File.separator + newName)
                if (file.renameTo(newFile)) {
                    FLAG_SUCCESS
                } else {
                    FLAG_FAILED
                }
            }
        }
    }

    /** Get the extension name of the file. */
    fun getFileExtension(file: File): String {
        return file.name.substring(file.name.lastIndexOf(".") + 1)
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
         * @param fileWriter Convenience class for writing character
         *     files.
         * @since 0.0.9
         */
        fun writeEvent(fileWriter: FileWriter)
    }

    /**
     * Register a listener when write result.
     *
     * @since 0.0.9
     */
    interface WriteEventResultListener {

        /**
         * On success.
         *
         * @since 0.0.9
         */
        fun onSuccess()


        /**
         * On failed.Default print StackTrace.
         *
         * @since 0.0.9
         */
        fun onFailed(e: Exception) {
            e.printStackTrace()
        }
    }

    /** @since 0.0.9 */
    private fun File.realFileWrite() {
        if (this.exists() && this.isFile) {
            this.delete()
        }
        if (!this.parentFile?.exists()!!)
            this.parentFile?.mkdirs()
        val fileOutputStream = FileOutputStream(this)
        fileOutputStream.close()
    }

}