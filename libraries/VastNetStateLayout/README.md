# VastNatStateLayout

netstatelayout extend framelayout.you can custom view to show net state: loading,error,ok.

<img src="https://github.com/xingliuhua/netStateLayout/blob/master/screenshot-1533289379486.jpg" height="330" width="190" ><img src="https://github.com/xingliuhua/netStateLayout/blob/master/screenshot-1533289408273.jpg" height="330" width="190" ><img src="https://github.com/xingliuhua/netStateLayout/blob/master/screenshot-1533289418975.jpg" height="330" width="190" >

## How to use

### implement interface

implement INetErrorView and INetLoadingView to custom your UI .of course,you can use SimpleNetErrorView and SimpleNetLoadingView.

```java
public class MyNetErrorView implements INetErrorView {
   ...
}
```

```java
public class MyNetLoadingView implements INetLoadingView {
    ...
}
```

### set view

you can set view by code or xml

1. set view by code

```java
 mNetStateLayout.setNetErrorView(new MyNetErrorView());
 mNetStateLayout.setNetLoadingView(new MyNetLoadingView());
```

2. set view by xml

```xml
<com.gcode.vastnetstatelayout.view.NetStateLayout
        android:id="@+id/net_state_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:net_error="com.gcode.vastnetstatelayout.view.MyNetErrorView"
        app:net_loading="com.gcode.vastnetstatelayout.view.MyNetLoadingView">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">


            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="by xml attr"/>

            <Button
                android:id="@+id/test"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="to second activity"/>
        </LinearLayout>
</com.gcode.vastnetstatelayout.view.NetStateLayout>
```

### switch state

```java
public class MainActivity extends AppCompatActivity {
    private NetStateLayout mNetStateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        mNetStateLayout = (NetStateLayout) findViewById(R.id.net_state_layout);
        mNetStateLayout.setContentState(NetStateLayout.CONTENT_STATE_SHOW_LOADING);
        mNetStateLayout.setOnRetryClickListener(new INetErrorView.OnRetryClickListener() {
            @Override
            public void onRetryClicked() {
                mNetStateLayout.setContentState(NetStateLayout.CONTENT_STATE_HIDE);
            }
        });
        mHandler.sendEmptyMessageDelayed(0, 3000);

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mNetStateLayout.setContentState(NetStateLayout.CONTENT_STATE_SHOW_NET_ERROR);
        }
    };
}
```
