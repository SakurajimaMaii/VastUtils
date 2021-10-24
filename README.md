# [ToolsForAndroid](https://github.com/SakurajimaMaii/ToolsForAndroid)说明

## 前言

### 项目介绍

为了方便安卓开发，我将一些常用的工具类进行了整合，以便更快速的进行项目开发。

### 版本及语言

![targetSdkVersion](https://img.shields.io/badge/targetSdkVersion-30-%230984e3) ![minSdkVersion](https://img.shields.io/badge/minSdkVersion-23-%23079992) ![Programming language](https://img.shields.io/badge/Programming%20language-kotlin-%23eb3b5a) ![currentVersion](https://jitpack.io/v/SakurajimaMaii/ToolsForAndroid.svg)

### 添加依赖

1. 在项目根目录下的 build.gradle 添加

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

2. 添加依赖

```gradle
dependencies {
  implementation 'com.github.SakurajimaMaii:ToolsForAndroid:2.4.2'
}
```

## Adapter 介绍

### 模式介绍

根据项目的需求，我将 Adapter 设计成两种模式，分别是：

- 支持 DataBinding
- 不支持 DataBinding

如果你的项目**支持 DataBinding**，请使用**BaseGcodeBindingAdapter**，如果**不支持 DataBinding**，请使用**BaseGcodeAdapter**

### 使用说明

- [BaseGcodeBindingAdapter 使用](https://github.com/SakurajimaMaii/ToolsForAndroid/blob/master/docs/BaseGcodeBindingAdapter.md)
- [BaseGcodeAdapter 使用](https://github.com/SakurajimaMaii/ToolsForAndroid/blob/master/docs/BaseGcodeAdapter.md)

### Adapter 方法介绍

adapter 目前提供了以下几种方法

|                                   方法名                                    |             说明             |
| :-------------------------------------------------------------------------: | :--------------------------: |
|                               getItemCount()                                |       获取 item 的数量       |
|                       getItemViewType(position: Int)                        | 根据 position 获取 ViewType  |
|                                isItemEmpty()                                |     判断 items 是否为空      |
|                           getItemByPos(pos: Int)                            |      根据 pos 获取 item      |
|                             addItem(item: obj?)                             |       在数据集最后添加       |
|                             addItem(item: obj?)                             |     在最后添加对象 item      |
|                      addItemByPos(item: obj, pos: Int)                      |    根据 pos 添加对象 item    |
|             addItemsByPos(addItems: MutableList<obj>, pos: Int)             |   通过 pos 来批量添加 item   |
|                         removeItemByObj(item: obj?)                         |     通过对象来删除 Item      |
|                          removeItemByPos(pos: Int)                          |     通过 pos 来删除对象      |
| removeItemsByPos(startPos: Int, endPos: Int,includeEndPos: Boolean = false) | startPos到endPos范围内的元素 |
|                                 clearItem()                                 |          清空 items          |

## Utils 介绍

### MsgWindowUtils

!!! info "介绍"
    消息弹窗工具类，目前支持以下三种消息弹窗

    - Short Toast

    - Long Toast

    - showDlgMsg

调用方法

```kotlin
MsgWindowUtils.showShortMsg(this, "These permissions are denied: $deniedList")
```

### ScreenSizeUtils

!!! info ScreenSizeUtils介绍
    用于返回屏幕大小相关信息，方便你根据此来设计控件尺寸，提供了以下方法

```kotlin
fun isAllScreenDevice(context: Context) //判断手机是否为全面屏
fun getMobileScreenWidth(context: Context) //获取屏幕宽度
fun getMobileScreenHeight(context: Context) //获取屏幕高度
```

### LogUtils

!!! info "LogUtils介绍"
    日志工具类主要用于打印日志，效果如下：

![log example](https://img-blog.csdnimg.cn/e5e2c730d428481fba80a41f8c126af6.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA56CB5LiK5aSP6Zuo,size_20,color_FFFFFF,t_70,g_se,x_16)

打印的内容包含 `类名`  `Log调用的行数` `调用Log的方法` `关键字` `输出信息`

你可以通过 `setLogEnabled` 方法来开启或者关闭日志

```kotlin
LogUtils.setLogEnabled(false)
```

调用方法

```kotlin
LogUtils.i(this.javaClass,"Hello","${1+2}")
LogUtils.d(this.javaClass,"Hello","${1+2}")
LogUtils.e(this.javaClass,"Hello","${1+2}")
LogUtils.v(this.javaClass,"Hello","${1+2}")
LogUtils.w(this.javaClass,"Hello","${1+2}")
```

### CameraUtils

!!! info "CameraUtils介绍"
    相机工具类主要用于获取相册内的图片并将其转化为 `Bitmap` 对象

使用方法

```kotlin
// 获取到的图片
private var bitmap: Bitmap? = null

// 打开相册
private val getPhoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
    if(result.resultCode == Activity.RESULT_OK){
        bitmap = result.data?.let { CameraUtils.displayImage(it,this) }
    }
}
```

## 参考来源

[Android获取图片：拍照和从相册中选择](https://www.jianshu.com/p/57487bb1ec5a)