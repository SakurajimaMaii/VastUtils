package com.gcode.vastactfrag

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class VastVbVmActivity<VB: ViewBinding,VM: ViewModel> : VastVmActivity<VM>() {

    protected lateinit var mBinding:VB

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBind()
        onActCreate(savedInstanceState,null)
    }

    final override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initDataBind()
        onActCreate(savedInstanceState, persistentState)
    }

    /**
     * Used to replace the original onCreate method
     *
     * By default, persistentState value is null
     */
    abstract fun onActCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle? = null)

    @Suppress("UNCHECKED_CAST")
    private fun initDataBind() {
        val superClass = javaClass.genericSuperclass
        val clazz = (superClass as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = clazz.getMethod("inflate", LayoutInflater::class.java)
        mBinding = method.invoke(null, layoutInflater) as VB
        setContentView(mBinding.root)
    }
}