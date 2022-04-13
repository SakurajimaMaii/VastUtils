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

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import com.gcode.vasttools.base.VastVbActivity
import com.gcode.vasttools.utils.*
import com.gcode.vastutils.databinding.ActivityIntentBinding

class IntentActivity : VastVbActivity<ActivityIntentBinding>() {

    @SuppressLint("MissingPermission")
    override fun initView(savedInstanceState: Bundle?) {
        mBinding.callBtn.setOnClickListener {
            dialPhoneNumber("12345678910")
        }

        mBinding.searchWeb.setOnClickListener {
            searchWeb("12345678910")
        }

        mBinding.openWebPage.setOnClickListener {
            openWebPage("http://www.baidu.com")
        }

        mBinding.sendMmsMessage.setOnClickListener {
            sendMmsMessage("123456","1238489")
        }

        mBinding.sendEmail.setOnClickListener {
            openEmail(arrayOf("1550651926@qq.com"))
        }

        mBinding.createAlarm.setOnClickListener {
            createAlarm("你好",12,30)
        }

        mBinding.wifiSetting.setOnClickListener {
            openWirelessSettings()
        }

    }
}