package com.gcode.toolsforandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gcode.tools.utils.LogUtils;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        LogUtils.INSTANCE.setLogEnabled(true);
        LogUtils.INSTANCE.d(this.getClass(),"hello","111111");

        Float value = 50F;
    }
}