package com.gcode.vastutils.vastactfrag

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import com.gcode.vastactfrag.VastVbVmActivity
import com.gcode.vastutils.databinding.ActivityBaseVbBinding
import com.gcode.vastutils.viewModel.BaseVM

class BaseVbActivity : VastVbVmActivity<ActivityBaseVbBinding,BaseVM>() {

    override fun onActCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        mBinding.btn.setOnClickListener {
            Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
        }

        mViewModel.str.observe(this){

        }
    }

}