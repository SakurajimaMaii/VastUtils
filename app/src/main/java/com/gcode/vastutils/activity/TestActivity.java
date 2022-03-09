package com.gcode.vastutils.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import com.gcode.vasttools.base.VastVbActivity;
import com.gcode.vasttools.utils.LogUtils;
import com.gcode.vasttools.utils.RegexUtils;
import com.gcode.vastutils.databinding.ActivityTestBinding;

public class TestActivity extends VastVbActivity<ActivityTestBinding> {

    @Override
    public void onActCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        LogUtils.INSTANCE.i("test", RegexUtils.isPhoneNumber("")+"");
    }

}