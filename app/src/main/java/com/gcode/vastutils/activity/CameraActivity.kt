package com.gcode.vastutils.activity

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.gcode.vastutils.ImgUtils
import com.gcode.vastutils.databinding.ActivityCameraBinding

class CameraActivity : FragmentActivity() {
    private lateinit var binding:ActivityCameraBinding

    private var bitmap: Bitmap? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val getPhoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
//            if(result.resultCode == Activity.RESULT_OK){
//                //image 就是 imageView控件
//                binding.image.setImageBitmap(result.data?.let { ImgUtils.choiceFromAlbum(it,this) })
//            }
//        }

        binding.camerabutton.setOnClickListener {
//            val intent = Intent(Intent.ACTION_GET_CONTENT)
//            intent.type = "image/*"
//            getPhoto.launch(intent)
            val bitmap = ImgUtils.startCamera(this)
            binding.image.setImageBitmap(bitmap)
        }
    }
}