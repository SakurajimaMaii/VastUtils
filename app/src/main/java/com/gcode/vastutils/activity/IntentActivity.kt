package com.gcode.vastutils.activity

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ComponentActivity
import com.gcode.vasttools.utils.*
import com.gcode.vastutils.R
import com.gcode.vastutils.databinding.ActivityIntentBinding

class IntentActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityIntentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityIntentBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

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