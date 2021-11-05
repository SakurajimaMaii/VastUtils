package com.gcode.vastactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import java.lang.reflect.ParameterizedType

abstract class VastBaseActivity<DB:ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding:DB

    override fun onCreate(savedInstanceState: Bundle?) {
        initDataBind()
        super.onCreate(savedInstanceState)
    }

    /**
     * 创建DataBinding
     */
    private fun initDataBind() {
        //利用反射 根据泛型得到 ViewDataBinding
        val superClass = javaClass.genericSuperclass
        val aClass = (superClass as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        binding =  method.invoke(null,layoutInflater) as DB
        binding.lifecycleOwner = this
    }
}