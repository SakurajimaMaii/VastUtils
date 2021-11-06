package com.gcode.toolsforandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.gcode.vasttools.internal.exception.NoMatchAspectRatio;
import com.gcode.vasttools.model.AspectRatioDevice;
import com.gcode.vasttools.utils.LogUtils;
import com.gcode.vasttools.utils.ScreenSizeUtils;

import kotlin.Pair;

public class LogActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        ScreenSizeUtils.INSTANCE.addDevice(new AspectRatioDevice("SAMSUNG",2.3f),new AspectRatioDevice("Apple",1.5f));

        ScreenSizeUtils.INSTANCE.resetDeviceList();
        try {
            LogUtils.INSTANCE.e(this.getClass(),"LogActivity", String.valueOf(ScreenSizeUtils.INSTANCE.isAllScreenDeviceApi30(this,"SAMSUNG")));
        } catch (NoMatchAspectRatio e) {
            LogUtils.INSTANCE.e(this.getClass(),"LogActivity",e.getMessage());
        }
    }
}