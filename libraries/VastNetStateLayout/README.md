<h1 align="center">VastNatStateLayout</h1>

<p align="center">netstatelayout extend framelayout.you can custom view to show net state: loading,error,ok,empty data.</p>

<p align="center">English | <a href="https://github.com/SakurajimaMaii/VastUtils/blob/master/libraries/VastNetStateLayout/README_CN.md">简体中文</a></p>

<div>
	<image src="https://img-blog.csdnimg.cn/07db693ac8154e968cc7dbbd8f95ef3e.jpg" width="30%"/>
	<image src="https://img-blog.csdnimg.cn/a270b3dd21554189a07ac50e3a426c6f.jpg" width="30%"/>
	<image src="https://img-blog.csdnimg.cn/6925a02f78cd46e2904bf524b74d1c3e.jpg" width="30%"/>
	<image src="https://img-blog.csdnimg.cn/e3d521f969014a2387c8ff4348c47800.jpg" width="30%"/>
</div>

## How to use

```gradle
implementation 'io.github.sakurajimamaii:VastNetStateLayout:0.0.2'
```

## set view

### Add `VastNatStateLayout` in xml

```xml
<com.gcode.vastnetstatelayout.view.VastNetStateLayout
    android:id="@+id/net_state_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <!--What's shown under normal state-->
</com.gcode.vastnetstatelayout.view.VastNetStateLayout>
```

### Set net state view

```kotlin
// Get vastNetStateMgr
val vastNetStateMgr = VastNetStateMgr(this)
// Set net state view
vastNetStateMgr.setLoadingView(R.layout.simple_loading_view)
```

### Set click event

```kotlin
vastNetStateMgr.setVastRetryClickListener(object : VastRetryClickListener {
    override fun onRetry() {
        object:Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                mNetStateLayout!!.showNetError()
            }
        }.sendEmptyMessageDelayed(0, 3000)
    }
})
```

### Set `vastNetStateMgr`

```kotlin
mNetStateLayout!!.setVastNetStateMgr(vastNetStateMgr)
```
