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

package com.gcode.vastutilsjava;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.gcode.vasttools.base.VastVbActivity;
import com.gcode.vasttools.skin.VastSkinManager;
import com.gcode.vastutilsjava.databinding.ActivityMainBinding;

public class MainActivity extends VastVbActivity<ActivityMainBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mBinding.start.setOnClickListener(v ->
                VastSkinManager.INSTANCE.loadSkin("data/data/com.gcode.vastutils/files/darkskin-debug.apk"));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}