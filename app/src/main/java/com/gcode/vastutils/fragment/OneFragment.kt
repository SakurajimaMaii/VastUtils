/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gcode.vastutils.fragment

import android.os.Bundle
import com.gcode.vasttools.fragment.VastVbVmFragment
import com.gcode.vastutils.databinding.FragmentOneBinding
import com.gcode.vastutils.viewModel.MainSharedVM


class OneFragment(override val layoutId: Int = 0) :
    VastVbVmFragment<FragmentOneBinding, MainSharedVM>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.addOne.setOnClickListener {
            mViewModel.addOne()
        }

        mViewModel.count.observe(requireActivity()){
            mBinding.count.text = it.toString()
        }
    }

}