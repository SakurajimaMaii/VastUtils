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
import androidx.fragment.app.Fragment
import com.gcode.vasttools.adapter.BaseFragmentAdapter
import com.gcode.vasttools.base.VastVbActivity
import com.gcode.vastutils.databinding.ActivityBaseFragmentBinding
import com.gcode.vastutils.fragment.OneFragment
import com.gcode.vastutils.fragment.TwoFragment
import com.gcode.vastutils.fragment.ThreeFragment

// Author: Vast Gui 
// Email: guihy2019@gmail.com
// Date: 2022/4/13 19:45
// Description:
// Documentation:

class BaseFragmentActivity : VastVbActivity<ActivityBaseFragmentBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.vp2.apply {
            /**
             * [BaseFragmentAdapter] 使用示例
             */
            adapter = BaseFragmentAdapter(this@BaseFragmentActivity,ArrayList<Fragment>().apply {
                add(OneFragment())
                add(TwoFragment())
                add(ThreeFragment())
            })
        }
    }

}