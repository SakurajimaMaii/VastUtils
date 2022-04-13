/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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