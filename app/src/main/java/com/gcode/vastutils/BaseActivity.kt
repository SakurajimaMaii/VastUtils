package com.gcode.toolsforandroid

import android.os.Bundle
import com.gcode.vastactivity.VastBaseActivity
import com.gcode.toolsforandroid.databinding.ActivityBaseBinding

class BaseActivity : VastBaseActivity<ActivityBaseBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.button.setOnClickListener {

        }
    }

}