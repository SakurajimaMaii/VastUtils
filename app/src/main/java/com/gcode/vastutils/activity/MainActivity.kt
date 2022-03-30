package com.gcode.vastutils.activity

import android.content.Intent
import android.os.Bundle
import com.gcode.vasttools.base.VastVbActivity
import com.gcode.vastutils.baseadpexample.BaseAdapterActivity
import com.gcode.vastutils.basebindadpexample.BaseBindingAdapterActivity
import com.gcode.vastutils.databinding.ActivityMainBinding
import com.gcode.vastutils.swipeexample.SwipeActivity

class MainActivity : VastVbActivity<ActivityMainBinding>() {

    override fun initView(savedInstanceState: Bundle?) {

        mBinding.loadingPage.setOnClickListener {
            startActivity(Intent(this, NetStateActivity::class.java))
        }

        mBinding.swipeListViewPage.setOnClickListener {
            startActivity(Intent(this, SwipeActivity::class.java))
        }

        mBinding.baseAdapter.setOnClickListener {
            startActivity(Intent(this, BaseAdapterActivity::class.java))
        }

        mBinding.baseBindAdapter.setOnClickListener {
            startActivity(Intent(this, BaseBindingAdapterActivity::class.java))
        }

        mBinding.intent.setOnClickListener {
            startActivity(Intent(this,IntentActivity::class.java))
        }

        mBinding.shape.setOnClickListener {
            startActivity(Intent(this,ShapeActivity::class.java))
        }
    }

}