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

package com.gcode.vastutils.activity

import android.os.Bundle
import com.gcode.progressmanager.body.ProgressInfo
import com.gcode.vasttools.base.VastVbActivity
import com.gcode.vasttools.utils.DownloadUtils
import com.gcode.vasttools.utils.LogUtils
import com.gcode.vastutils.databinding.ActivityDownloadBinding

// Author: Vast Gui 
// Email: guihy2019@gmail.com
// Date: 2022/4/14 18:42
// Description:
// Documentation:

class DownloadActivity : VastVbActivity<ActivityDownloadBinding>() {

    private val tag = this.javaClass.simpleName

    private val downloadUrl =
        "https://objects.githubusercontent.com/github-production-release-asset-2e65be/316135129/e8d324e9-a886-4a30-962e-ccb9a2a359d7" +
                "?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20220415%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20220415T030124Z&X" +
                "-Amz-Expires=300&X-Amz-Signature=dabf8e016e572e8d18bf2d810773c396e25edfd4d7ca2549e8c9f0e72111c486&X-Amz-SignedHeaders=host&actor_id=46998172&key_id" +
                "=0&repo_id=316135129&response-content-disposition=attachment%3B%20filename%3Dbluetooth.apk&response-content-type=application%2Fvnd.android.package-archive"
    private val downloadUrl2 =
        "https://objects.githubusercontent.com/github-production-release-asset-2e65be/221809776/9c4ea539-bfa7-4595-a251-4e90b19b65f4?X-Amz-Algorithm" +
                "=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20220418%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20220418T094110Z&X-Amz-Expires=300&X-Amz-Signature=" +
                "9649140fc3161fc5daf21ddec21ef1d7398892dcdafddafc86bfe8d0d104046e&X-Amz-SignedHeaders=host&actor_id=46998172&key_id=0&repo_id=221809776&response-content-disposition" +
                "=attachment%3B%20filename%3Dcfa-2.4.14-premium-universal-release.apk&response-content-type=application%2Fvnd.android.package-archive"
    private val saveDir = "data/data/com.gcode.vastutils/files/"

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.download.setOnClickListener {
            downloadApk()
        }
    }

    private fun downloadApk() {
        DownloadUtils.download(
            downloadUrl2,
            saveDir,
            "cf.apk",
            object : DownloadUtils.OnDownloadListener {
                override fun onDownloadSuccess() {
                    LogUtils.i(tag, "download success.")
                    mBinding.downloadCv.setProgress(1f)
                }

                override fun onDownloading(progress: ProgressInfo?) {
                    LogUtils.i(tag, "downloading ${progress?.percent?.toFloat()}")
                    mBinding.downloadCv.setProgress((progress?.percent?.toFloat() ?: 0f) / 100f)
                }

                override fun onDownloadFailed(e: Exception?) {
                    LogUtils.i(tag, "download failed:" + e?.message)
                }

            }
        )
    }

}