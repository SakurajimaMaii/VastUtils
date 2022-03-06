package com.gcode.vastactfrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/2/19
 */
abstract class VastVbFragment<VB: ViewBinding,VM: ViewModel>:VastVmFragment<VM>() {

    protected lateinit var mBinding:VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDataBind()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @Suppress("UNCHECKED_CAST")
    private fun initDataBind(){
        val superClass = javaClass.genericSuperclass
        val clazz = (superClass as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = clazz.getMethod("inflate", LayoutInflater::class.java)
        mBinding = method.invoke(null, layoutInflater) as VB
        if(dataBindView != null){
            (dataBindView!!.parent as? ViewGroup)?.removeView(dataBindView)
        }
        dataBindView = mBinding.root
    }

}