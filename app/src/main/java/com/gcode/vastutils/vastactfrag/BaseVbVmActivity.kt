package com.gcode.vastutils.vastactfrag

import android.os.Bundle
import com.gcode.vasttools.base.VastVbVmActivity
import com.gcode.vasttools.utils.LogUtils
import com.gcode.vasttools.utils.getMobileScreenHeight
import com.gcode.vasttools.utils.getMobileScreenWidth
import com.gcode.vastutils.databinding.ActivityBaseVbBinding
import com.gcode.vastutils.viewModel.BaseVM

class BaseVbVmActivity : VastVbVmActivity<ActivityBaseVbBinding,BaseVM>() {

    override fun initView(savedInstanceState: Bundle?) {

        enableFullScreen = true // 启用全面屏

        mBinding.addOne.setOnClickListener {
            mViewModel.addOne()
        }

        mViewModel.count.observe(this){
            mBinding.count.text = it.toString()
        }

    }

}