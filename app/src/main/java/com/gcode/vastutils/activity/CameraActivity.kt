package com.gcode.vastutils.activity

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.LinearLayout.VERTICAL
import androidx.annotation.RequiresApi
import androidx.core.view.OneShotPreDrawListener.add
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.gcode.vastactfrag.VastVbActivity
import com.gcode.vastutils.ImgUtils
import com.gcode.vastutils.databinding.ActivityCameraBinding

class CameraActivity : VastVbActivity<ActivityCameraBinding>() {

    override fun onActCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {

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