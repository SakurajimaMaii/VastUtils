package com.gcode.vastutils

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gcode.vasttools.utils.AppUtils
import com.gcode.vasttools.utils.LogUtils.e
import com.gcode.vasttools.utils.SystemUtils
import com.gcode.vasttools.utils.SystemUtils.deviceBrand
import com.gcode.vasttools.utils.SystemUtils.systemAndroidVersion
import com.gcode.vasttools.utils.SystemUtils.systemLanguage
import com.gcode.vasttools.utils.SystemUtils.systemModel

class DateActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)

        e(
            this.javaClass, "LogActivity",
            systemLanguage
        )

        e(
            this.javaClass, "LogActivity",
            systemAndroidVersion
        )

        e(
            this.javaClass, "LogActivity",
            systemModel
        )

        e(
            this.javaClass, "LogActivity",
            "${AppUtils.getAppDebug(this)}"
        )
    }
}