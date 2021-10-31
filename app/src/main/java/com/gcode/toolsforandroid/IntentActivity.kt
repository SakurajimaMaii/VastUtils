package com.gcode.toolsforandroid

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.gcode.tools.utils.IntentUtils
import com.gcode.tools.utils.MsgWindowUtils

class IntentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        val callBtn = findViewById<Button>(R.id.callBtn)
        val searchWeb = findViewById<Button>(R.id.searchWeb)
        val openWebPage = findViewById<Button>(R.id.openWebPage)
        val sendMmsMessage = findViewById<Button>(R.id.sendMmsMessage)

        callBtn.setOnClickListener {
            startActivity(IntentUtils.dialPhoneNumber("12345678910"))
        }

        searchWeb.setOnClickListener {
            startActivity(IntentUtils.searchWeb("12345678910"))
        }

        openWebPage.setOnClickListener {
            startActivity(IntentUtils.openWebPage("www.baidu.com"))
        }

        val requestPermissionLauncher =
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
            requestPermissionLauncher.launch(Manifest.permission.SEND_SMS)
        }
    }
}