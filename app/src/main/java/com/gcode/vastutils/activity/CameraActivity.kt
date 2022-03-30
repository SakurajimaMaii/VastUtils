package com.gcode.vastutils.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vasttools.base.VastVbActivity
import com.gcode.vastutils.databinding.ActivityCameraBinding

class CameraActivity : VastVbActivity<ActivityCameraBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

}

class mAdapter <T:RecyclerView.ViewHolder> (val datas:MutableList<String>): RecyclerView.Adapter<T>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

class mViewHolder(val view: View) : RecyclerView.ViewHolder(view){

}