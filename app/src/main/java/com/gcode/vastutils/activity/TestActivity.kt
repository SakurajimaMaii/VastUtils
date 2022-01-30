package com.gcode.vastutils.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.gcode.vastutils.R

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val linear = findViewById<LinearLayout>(R.id.linear)

        // 将Button 加入到LinearLayout 中
        val b1 = Button(this);
        b1.setText("取消");
        linear.addView(b1);

        // 将Button 2 加入到LinearLayout 中
        val b2 = Button(this);
        b2.setText("确定");
        linear. addView ( b2 );

    }
}