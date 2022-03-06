package com.gcode.vastutils.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/2/19
 */
class BaseVM:ViewModel() {

    private val _str:MutableLiveData<String> = MutableLiveData()

    val str:LiveData<String>
        get() = _str


}