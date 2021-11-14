package com.gcode.vastutils;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.gcode.vasttools.utils.LogUtils;
import com.gcode.vasttools.utils.ScreenSizeUtils;
import com.gcode.vasttools.utils.SystemUtils;

public class LogActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    }
}