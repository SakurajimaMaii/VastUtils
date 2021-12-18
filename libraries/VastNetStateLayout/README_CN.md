<h1 align="center">VastNatStateLayout</h1>

<p align="center">VastNatStateLayout继承自framelayout。你可以自定义下列状态页面: loading,error,ok,empty data.</p>

<p align="center">简体中文 | <a href="https://github.com/SakurajimaMaii/VastUtils/blob/master/libraries/VastNetStateLayout/README.md">English</a></p>

# 💫 特性

- 👍 支持自定义四种状态页面 `loading 加载页面` `empty data 空数据页面` `net error 网络错误页面` `retry 重试界面`
- 👍 支持自定义界面点击事件
- 👍 使用`VastNetStateMgr`进行界面管理
- 👍 设置有默认界面，添加即用
<div>
	<image src="https://img-blog.csdnimg.cn/07db693ac8154e968cc7dbbd8f95ef3e.jpg" width="30%"/>
	<image src="https://img-blog.csdnimg.cn/a270b3dd21554189a07ac50e3a426c6f.jpg" width="30%"/>
	<image src="https://img-blog.csdnimg.cn/6925a02f78cd46e2904bf524b74d1c3e.jpg" width="30%"/>
	<image src="https://img-blog.csdnimg.cn/e3d521f969014a2387c8ff4348c47800.jpg" width="30%"/>
</div>

# 😎 如何使用

```gradle
implementation 'io.github.sakurajimamaii:VastNetStateLayout:0.0.2'
```

# 🤔 设定

## 在你的布局文件内添加`VastNatStateLayout`

```xml
<com.gcode.vastnetstatelayout.view.VastNetStateLayout
    android:id="@+id/net_state_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <!--正常情况下显示的内容-->
</com.gcode.vastnetstatelayout.view.VastNetStateLayout>
```

## 设定网络状态布局，我们这里以设置加载页面为例

```kotlin
// 获取vastNetStateMgr
val vastNetStateMgr = VastNetStateMgr(this)
// 设置布局
vastNetStateMgr.setLoadingView(R.layout.simple_loading_view)
```

## 设置点击事件，我们以设置重试事件为例

```kotlin
vastNetStateMgr.setVastRetryClickListener(object : VastRetryClickListener {
    override fun onRetry() {
        object:Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                mNetStateLayout!!.showNetError() //显示对应的界面
            }
        }.sendEmptyMessageDelayed(0, 3000)
    }
})
```

## 将设置好的`vastNetStateMgr`给`VastNatStateLayout`

```kotlin
mNetStateLayout!!.setVastNetStateMgr(vastNetStateMgr)
```