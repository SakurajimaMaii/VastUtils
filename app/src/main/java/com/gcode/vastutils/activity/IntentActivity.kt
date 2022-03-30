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
    }


}