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
import com.gcode.vasttools.activity.VastVbActivity
import com.gcode.vasttools.skin.VastSkinManager
import com.gcode.vasttools.utils.*
import com.gcode.vastutils.databinding.ActivityThemeBinding

class ThemeActivity : VastVbActivity<ActivityThemeBinding>() {

    private val tag = this.javaClass.simpleName

    override fun initView(savedInstanceState: Bundle?) {

        mBinding.start.setOnClickListener {
            VastSkinManager.loadSkin("data/data/com.gcode.vastutils/files/darkskin-debug.apk")
        }

        mBinding.end.setOnClickListener {
            VastSkinManager.loadSkin("")
        }

    }

}