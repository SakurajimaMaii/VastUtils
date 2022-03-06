package com.gcode.vastactfrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gcode.vastactfrag.ext.getVmClass

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/2/19
 */
abstract class VastVmFragment<VM:ViewModel>:VastFragment() {

    protected lateinit var mViewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewModel = createViewModel()

        return dataBindView ?: inflater.inflate(layoutId, container, false)
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClass(this))
    }

}