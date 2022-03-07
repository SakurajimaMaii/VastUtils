package com.gcode.vasttools.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gcode.vastactfrag.ext.getVmClass

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/2/19
 */
abstract class VastVmActivity<VM : ViewModel> : ComponentActivity() {

    protected lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = createViewModel()
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClass(this))
    }

}