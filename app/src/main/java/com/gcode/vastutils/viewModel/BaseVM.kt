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

    private val _count:MutableLiveData<Int> = MutableLiveData(0)

    val count:LiveData<Int>
        get() = _count

    fun addOne(){
        _count.postValue(_count.value?.plus(1) ?: 0)
    }

}