package com.gcode.vastutils.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gcode.vastutils.baseadpexample.BaseAdapterActivity
import com.gcode.vastutils.basebindadpexample.BaseBindingAdapterActivity
import com.gcode.vastutils.databinding.ActivityMainBinding
import com.gcode.vastutils.swipeexample.SwipeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.loadingPage.setOnClickListener {
            startActivity(Intent(this, NetStateActivity::class.java))
        }

        binding.swipeListViewPage.setOnClickListener {
            startActivity(Intent(this, SwipeActivity::class.java))
        }

        binding.baseAdapter.setOnClickListener {
            startActivity(Intent(this, BaseAdapterActivity::class.java))
        }

        binding.baseBindAdapter.setOnClickListener {
            startActivity(Intent(this, BaseBindingAdapterActivity::class.java))
        }

        binding.intent.setOnClickListener {
            startActivity(Intent(this,IntentActivity::class.java))
        }

        binding.shape.setOnClickListener {
            startActivity(Intent(this,ShapeActivity::class.java))
        }
    }
}