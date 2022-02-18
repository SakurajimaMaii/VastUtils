package com.gcode.vastutils.activity

import android.graphics.drawable.GradientDrawable.OVAL
import android.graphics.drawable.GradientDrawable.RECTANGLE
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gcode.vasttools.utils.LogUtils
import com.gcode.vasttools.utils.ShapeAndStateUtils
import com.gcode.vasttools.utils.colorHex2Int
import com.gcode.vasttools.utils.colorInt2Hex
import com.gcode.vastutils.R
import com.gcode.vastutils.databinding.ActivityShapeBinding

class ShapeActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityShapeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityShapeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val states = arrayOfNulls<IntArray>(6).apply {
            set(0, intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled))
            set(1, intArrayOf(android.R.attr.state_focused, android.R.attr.state_enabled))
            set(2, intArrayOf(-android.R.attr.state_focused, android.R.attr.state_enabled))
            set(3, intArrayOf(android.R.attr.state_focused))
            set(4, intArrayOf(android.R.attr.state_window_focused))
            set(5, intArrayOf())
        }

        val colorList = IntArray(6).apply {
            set(0, colorHex2Int("#00F260"))
            set(1, colorHex2Int("#FFFFFF"))
            set(2, colorHex2Int("#0575E6"))
            set(3, colorHex2Int("#FFFFFF"))
            set(4, colorHex2Int("#EF3B36"))
            set(5, colorHex2Int("#0575E6"))
        }

        val btnbk1 = ShapeAndStateUtils.create()
            .setShape(RECTANGLE)
            .setRadius(50f)
            .setStroke(15f, colorHex2Int("#3E5151"))
            .setBgColorStateList(states,colorList)
            .build()

        val btnbk2 = ShapeAndStateUtils.create()
            .setShape(RECTANGLE)
            .setRadius(50f)
            .setGradient(45, colorHex2Int("#0F2027"),colorHex2Int("#78ffd6"))
            .build()

        mBinding.btn1.background = btnbk1

        //mBinding.btn2.background = btnbk2

        LogUtils.i("test", colorInt2Hex(colorHex2Int("#0F2027")))
    }
}