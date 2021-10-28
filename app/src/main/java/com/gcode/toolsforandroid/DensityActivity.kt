package com.gcode.toolsforandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.gcode.tools.utils.DensityUtils
import com.gcode.tools.utils.DensityUtils.sp
import com.gcode.tools.utils.LogUtils

class DensityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_density)

        val tv = findViewById<TextView>(R.id.tv)

        LogUtils.i(this.javaClass,this.javaClass.simpleName,"${50f.sp} ${DensityUtils.sp2px(50f)}")
    }

    override fun onContentChanged() {
        super.onContentChanged()

        val btn = findViewById<TextView>(R.id.btn)

        //LogUtils.i(this.javaClass,this.javaClass.simpleName,"${DensityUtils.dip2px(this,80f)}")
    }
}