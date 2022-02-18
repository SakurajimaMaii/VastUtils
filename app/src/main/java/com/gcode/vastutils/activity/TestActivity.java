package com.gcode.vastutils.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gcode.vastnetstatelayout.annotation.VastNetState;
import com.gcode.vasttools.utils.ToastUtils;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ToastUtils.showShortMsg(this,"Hello");
    }
}