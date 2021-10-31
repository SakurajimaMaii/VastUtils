package com.gcode.toolsforandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gcode.tools.interfaces.LogContent;
import com.gcode.tools.utils.LogUtils;

import java.util.regex.Pattern;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

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