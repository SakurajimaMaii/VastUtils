package com.gcode.vastutils;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.gcode.vasttools.internal.exception.NoMatchAspectRatio;
import com.gcode.vasttools.model.AspectRatioDevice;
import com.gcode.vasttools.utils.LogUtils;
import com.gcode.vasttools.utils.ScreenSizeUtils;

public class LogActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        ScreenSizeUtils.INSTANCE.addDevice(new AspectRatioDevice("SAMSUNG",2.3f),new AspectRatioDevice("Apple",1.5f));

        ScreenSizeUtils.INSTANCE.resetDeviceList();
        try {
            LogUtils.INSTANCE.e(this.getClass(),"LogActivity", String.valueOf(ScreenSizeUtils.INSTANCE.isAllScreenDeviceApi31(this)));
        } catch (NoMatchAspectRatio e) {
            e.printStackTrace();
        }
    }
}