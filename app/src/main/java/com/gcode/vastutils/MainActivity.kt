package com.gcode.vastutils

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gcode.vastutils.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.loadingPage.setOnClickListener {
            startActivity(Intent(this,NetStateActivity::class.java))
        }

        binding.swipeListViewPage.setOnClickListener {
            startActivity(Intent(this,SlideActivity::class.java))
        }

        binding.baseAdapter.setOnClickListener {
            startActivity(Intent(this, BaseAdapterActivity::class.java))
        }
    }
}