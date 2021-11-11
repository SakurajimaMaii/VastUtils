package com.gcode.vastutils

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gcode.vasttools.annotation.DateFormat.GMT_PLUS_ONE
import com.gcode.vasttools.utils.DateUtils
import com.gcode.vasttools.utils.LogUtils
import com.gcode.vasttools.utils.ScreenSizeUtils
import com.gcode.vasttools.utils.SystemUtils

class DateActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)
    }
}