package com.gcode.vastutils

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastadapter.BaseVastAdapter
import com.gcode.vastadapter.BaseVastBindingAdapter
import com.gcode.vasttools.utils.MsgWindowUtils
import com.gcode.vastutils.databinding.ActivityMainBinding
import com.gcode.vastutils.model.Person
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.loadingPage.setOnClickListener {
            startActivity(Intent(this,NetStateActivity::class.java))
        }
    }
}