package com.gcode.utilssampledemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.gcode.tools.adapter.BaseAdapter;
import com.gcode.tools.adapter.BaseItem;

import java.util.ArrayList;
import java.util.List;

class MyTestBaseAdapter extends BaseAdapter{

    public MyTestBaseAdapter(@NonNull List<BaseItem> items) {
        super(items);
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, @NonNull BaseItem item) {
        ((TextView)holder.findViewById(R.id.firstName)).setText(((Person) item).getFirstName());
        ((TextView)holder.findViewById(R.id.lastName)).setText(((Person) item).getLastName());
    }
}

public class AdapterActivity extends AppCompatActivity {

    private ArrayList<BaseItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);

        for (int i= 0;i<30;i++){
            list.add(new Person("张$j","王$j"));
        }

        MyTestBaseAdapter adapter = new MyTestBaseAdapter(list);

        adapter.addItemByPos(new Person("张$j","王$j"),1506);
    }
}