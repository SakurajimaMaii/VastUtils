package com.gcode.utilssampledemo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.gcode.tools.utils.CameraUtils
import com.gcode.tools.utils.LogUtils
import com.gcode.tools.utils.MsgWindowUtils

class CameraActivity : AppCompatActivity() {
    private lateinit var button:Button

    private var bitmap: Bitmap? = null

    private val getPhoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        if(result.resultCode == Activity.RESULT_OK){
            bitmap = result.data?.let { CameraUtils.displayImage(it,this) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val button = findViewById<Button>(R.id.camerabutton)

        val image = findViewById<ImageView>(R.id.image)

        button.setOnClickListener {
            getPhoto.launch(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI))

            image.setImageBitmap(bitmap)
        }
    }

    override fun onStart() {
        super.onStart()
    }
}