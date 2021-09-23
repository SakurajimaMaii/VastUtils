# [Utils](https://github.com/SakurajimaMaii/Utils)说明

## 项目介绍

为了方便安卓开发，我将一些常用的工具类进行了整合，以便更快速的进行项目开发。目前项目主要包含**adapter**和**utils**两部分

## 版本及语言
![targetSdkVersion](https://img.shields.io/badge/targetSdkVersion-30-%230984e3)  ![minSdkVersion](https://img.shields.io/badge/minSdkVersion-23-%23079992)  ![Programming language](https://img.shields.io/badge/Programming%20language-kotlin-%23eb3b5a)

## 添加依赖

1. 在项目根目录下的build.gradle添加
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
	implementation 'com.github.SakurajimaMaii:Utils:2.2.0'
}
```

## Adapter介绍

### 模式介绍

根据项目的需求，我将Adapter设计成两种模式，分别是：
- 支持DataBinding
- 不支持DataBinding

如果你的项目**支持DataBinding**，请使用**BaseUtilBindingAdapter**，如果**不支持DataBinding**，请使用**BaseUtilAdapter**

## 使用说明

- [BaseUtilBindingAdapter使用](https://github.com/SakurajimaMaii/GUtils/blob/master/docs/BaseBindingAdapterDoc.md)
- [BaseUtilAdapter使用](https://github.com/SakurajimaMaii/GUtils/blob/master/docs/BaseAdapterDoc.md)

### Adapter方法介绍

adapter目前提供了以下几种方法

|             方法名             |           说明            |
| :----------------------------: | :-----------------------: |
|         getItemCount()         |      获取item的数量       |
| getItemViewType(position: Int) | 根据position获取ViewType  |
|          isItemEmpty()         |     判断items是否为空     |
|     getItemByPos(position: Int)     |   根据position获取item    |
|      addItem(item: BaseItem)       |    在最后添加对象item     |
| addItemPyPos(pos: Int, item: BaseItem)  | 根据position添加对象item  |
|    removeItem(item: BaseItem?)     |     移除指定item对象      |
|       removeItemByPos(index: Int)       | 根据index移除指定item对象 |
|            clearItem()             |         清空items         |

## Utils介绍

### ActivityCollectorUtils

Activity管理功能工具类，借助这个工具类你可以实现强制登录功能

### MsgWindowUtils

消息弹窗工具类，目前支持以下三种消息弹窗

- Short Toast

- Long Toast

- showDlgMsg

调用方法
```kotlin
MsgWindowUtils.showShortMsg(this, "These permissions are denied: $deniedList")
```

### ScreenSizeUtils

用于返回屏幕大小相关信息，方便你根据此来设计控件尺寸，提供了以下方法

```kotlin
fun isAllScreenDevice(context: Context) //判断手机是否为全面屏
fun getMobileScreenWidth(context: Context) //获取屏幕宽度
fun getMobileScreenHeight(context: Context) //获取屏幕高度
```

