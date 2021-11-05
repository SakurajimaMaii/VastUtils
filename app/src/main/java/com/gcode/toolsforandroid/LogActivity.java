package com.gcode.toolsforandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gcode.vasttools.interfaces.LogContent;
import com.gcode.vasttools.utils.AppUtils;
import com.gcode.vasttools.utils.DensityUtils;
import com.gcode.vasttools.utils.LogUtils;
import com.gcode.vasttools.utils.MsgWindowUtils;
import com.gcode.vasttools.utils.ScreenSizeUtils;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        AppUtils.INSTANCE.getAppName(this);

        DensityUtils.INSTANCE.dp2px(50F);

        LogUtils.INSTANCE.setLogEnabled(true);
        LogUtils.INSTANCE.setLogContentFormat(new LogContent() {
            @NonNull
            @Override
            public String logContentFormat(@NonNull String s, @Nullable String s1, @Nullable String s2) {
                return s1+s2;
            }
        });
        LogUtils.INSTANCE.d(this.getClass(),"hello","111111");

        Float value = 50F;
    }
}