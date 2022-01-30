package com.gcode.vastutils.activity

import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.gcode.vastutils.R

class CameraActivity : AppCompatActivity() {
    private lateinit var button:Button

    private var bitmap: Bitmap? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val button = findViewById<Button>(R.id.camerabutton)

        val image = findViewById<ImageView>(R.id.image)

    }

    override fun onStart() {
        super.onStart()
    }
}