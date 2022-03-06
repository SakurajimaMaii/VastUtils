package com.gcode.vastutils.vastactfrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gcode.vastactfrag.VastVbFragment
import com.gcode.vastutils.R
import com.gcode.vastutils.databinding.FragmentBaseVbBinding
import com.gcode.vastutils.viewModel.BaseVM


class BaseVbFragment(override val layoutId: Int = 0) : VastVbFragment<FragmentBaseVbBinding,BaseVM>() {



}