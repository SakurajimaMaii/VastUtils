package com.gcode.vastutils;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.gcode.vastswipelistview.model.VastSwipeMenuItem;

public class LogActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        VastSwipeMenuItem item = new VastSwipeMenuItem(this);

        item.setClickEvent(
                (vastSwipeMenuItem, integer) -> {
                    Toast.makeText(LogActivity.this, "Log", Toast.LENGTH_SHORT).show();
                    return null;
                }
        );
    }
}