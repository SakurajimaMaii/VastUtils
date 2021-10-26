package com.gcode.toolsforandroid

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.gcode.tools.utils.CameraUtils

class CameraActivity : AppCompatActivity() {
    private lateinit var button:Button

    private var bitmap: Bitmap? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val button = findViewById<Button>(R.id.camerabutton)

        val image = findViewById<ImageView>(R.id.image)

        val getPhoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if(result.resultCode == Activity.RESULT_OK){
                image.setImageBitmap(result.data?.let { CameraUtils.getImageBitMapApi29Down(it,this) })
            }
        }

        button.setOnClickListener {
            getPhoto.launch(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI))
        }
    }

    override fun onStart() {
        super.onStart()
    }
}