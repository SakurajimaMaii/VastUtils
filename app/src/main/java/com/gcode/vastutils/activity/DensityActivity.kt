package com.gcode.vastutils.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.gcode.vasttools.utils.DensityUtils
import com.gcode.vasttools.utils.LogUtils
import com.gcode.vasttools.utils.colorHex2Int
import com.gcode.vasttools.utils.colorHex2RGB
import com.gcode.vastutils.R

class DensityActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_density)

        val tv = findViewById<TextView>(R.id.tv)

        val btn = findViewById<TextView>(R.id.btn)
    }
    
    override fun onContentChanged() {
        super.onContentChanged()

        val btn = findViewById<TextView>(R.id.btn)

        //LogUtils.i(this.javaClass,this.javaClass.simpleName,"${DensityUtils.dip2px(this,80f)}")
    }
}