package com.gcode.toolsforandroid

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.gcode.vasttools.utils.IntentUtils
import com.gcode.vasttools.utils.MsgWindowUtils

class IntentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        val callBtn = findViewById<Button>(R.id.callBtn)
        val searchWeb = findViewById<Button>(R.id.searchWeb)
        val openWebPage = findViewById<Button>(R.id.openWebPage)
        val sendMmsMessage = findViewById<Button>(R.id.sendMmsMessage)
        val sendEmail = findViewById<Button>(R.id.sendEmail)
        val createAlarm = findViewById<Button>(R.id.createAlarm)

        callBtn.setOnClickListener {
            startActivity(IntentUtils.dialPhoneNumber("12345678910"))
        }

        searchWeb.setOnClickListener {
            startActivity(IntentUtils.searchWeb("12345678910"))
        }

        openWebPage.setOnClickListener {
            startActivity(IntentUtils.openWebPage("www.baidu.com"))
        }

        val smsLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                MsgWindowUtils.showShortMsg(this,"$isGranted")
                if (isGranted) {
                    startActivity(IntentUtils.sendMmsMessage("123456"))
                }else{
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),1024)
                    startActivity(IntentUtils.sendMmsMessage("123456","1238489"))
                }
            }

        sendMmsMessage.setOnClickListener {
            smsLauncher.launch(Manifest.permission.SEND_SMS)
        }

        sendEmail.setOnClickListener {
            startActivity(IntentUtils.openEmail(arrayOf("1550651926@qq.com")))
        }

        val alarmLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    startActivity(IntentUtils.createAlarm("122",1,1))
                }else{
                    ActivityCompat.requestPermissions(this, arrayOf("com.android.alarm.permission.SET_ALARM"),1023)
                    startActivity(IntentUtils.createAlarm("122",1,1))
                }
            }

        createAlarm.setOnClickListener {
            alarmLauncher.launch("com.android.alarm.permission.SET_ALARM")
        }
    }
}