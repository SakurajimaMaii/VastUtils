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

package com.gcode.vastutils.activity

import android.os.Bundle
import android.util.Log
import com.gcode.vasttools.activity.VastVbActivity
import com.gcode.vasttools.helper.ContextHelper
import com.gcode.vasttools.utils.*
import com.gcode.vasttools.utils.FileUtils.appExternalCacheDir
import com.gcode.vasttools.utils.FileUtils.appInternalCacheDir
import com.gcode.vasttools.utils.FileUtils.appInternalFilesDir
import com.gcode.vasttools.utils.FileUtils.saveFile
import com.gcode.vastutils.databinding.ActivityFileBinding
import java.io.File
import java.io.FileWriter
import java.util.*


// Author: SakurajimaMai
// Email: guihy2019@gmail.com
// Date: 2022/5/31
// Description: 
// Documentation:

class FileActivity : VastVbActivity<ActivityFileBinding>() {

    private val tag = this.javaClass.simpleName

    override fun initView(savedInstanceState: Bundle?) {
        Log.i("VU", EncryptionUtils.encode("Hello World!"))
        LogUtils.i(tag, appInternalFilesDir().path)
        LogUtils.i(tag, appInternalFilesDir().absolutePath)
        LogUtils.i(tag, appInternalCacheDir().path)
        LogUtils.i(tag, appInternalCacheDir().absolutePath)
        LogUtils.i(tag, appExternalCacheDir()?.path)

        saveFile(appInternalFilesDir().path, "test.txt", object : FileUtils.WriteEventListener {
            override fun writeEvent(fileWriter: FileWriter) {
                fileWriter.write("Hello World")
            }
        })

        FileUtils.makeDir(appInternalFilesDir().path, "a")

        FileUtils.rename(File(appInternalFilesDir().path, "a"), "b")

        LogUtils.i(tag, File(appInternalFilesDir().path).listFiles()?.toList().toString())
    }

}