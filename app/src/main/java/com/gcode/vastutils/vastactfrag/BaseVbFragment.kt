package com.gcode.vastutils.vastactfrag

import android.content.Context
import android.os.Bundle
import com.gcode.vasttools.base.VastVbVmFragment
import com.gcode.vasttools.utils.ToastUtils
import com.gcode.vasttools.utils.showShortMsg
import com.gcode.vastutils.databinding.FragmentBaseVbBinding
import com.gcode.vastutils.viewModel.BaseVM


class BaseVbFragment(override val layoutId: Int = 0) : VastVbVmFragment<FragmentBaseVbBinding, BaseVM>() {

    override fun initView(savedInstanceState: Bundle?) {

        mBinding.tv.setOnClickListener {

        }

    }

}