# [ToolsForAndroid](https://github.com/SakurajimaMaii/ToolsForAndroid)说明

## 项目介绍

为了方便安卓开发，我将一些常用的工具类进行了整合，以便更快速的进行项目开发。目前项目主要包含**adapter**和**utils**两部分

## 版本及语言

![targetSdkVersion](https://img.shields.io/badge/targetSdkVersion-30-%230984e3) ![minSdkVersion](https://img.shields.io/badge/minSdkVersion-23-%23079992) ![Programming language](https://img.shields.io/badge/Programming%20language-kotlin-%23eb3b5a) ![currentVersion](https://jitpack.io/v/SakurajimaMaii/ToolsForAndroid.svg)

## 添加依赖

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
	implementation 'com.github.SakurajimaMaii:ToolsForAndroid:2.3.0'
}
```

## Adapter 介绍

### 模式介绍

根据项目的需求，我将 Adapter 设计成两种模式，分别是：

- 支持 DataBinding
- 不支持 DataBinding

如果你的项目**支持 DataBinding**，请使用**BaseGcodeBindingAdapter**，如果**不支持 DataBinding**，请使用**BaseGcodeAdapter**

## 使用说明

- [BaseGcodeBindingAdapter 使用](https://github.com/SakurajimaMaii/ToolsForAndroid/blob/master/docs/BaseUtilBindingAdapterDoc.md)
- [BaseGcodeAdapter 使用](https://github.com/SakurajimaMaii/ToolsForAndroid/blob/master/docs/BaseUtilAdapterDoc.md)

### Adapter 方法介绍

adapter 目前提供了以下几种方法

|                                                       方法名                                                        |                 说明                 |
| :-----------------------------------------------------------------------------------------------------------------: | :----------------------------------: |
|                                                   getItemCount()                                                    |           获取 item 的数量           |
|                                           getItemViewType(position: Int)                                            |     根据 position 获取 ViewType      |
|                                                    isItemEmpty()                                                    |         判断 items 是否为空          |
|                                     getItemByPos(@IntRange(from = 0) pos: Int)                                      |          根据 pos 获取 item          |
|                                            addItem(@Nullable item: obj?)                                            |           在数据集最后添加           |
|                                            addItem(@Nullable item: obj?)                                            |         在最后添加对象 item          |
|                                addItemByPos(item: obj, @IntRange(from = 0) pos: Int)                                |        根据 pos 添加对象 item        |
|                       addItemsByPos(addItems: MutableList<obj>, @IntRange(from = 0) pos: Int)                       |       通过 pos 来批量添加 item       |
|                                        removeItemByObj(@Nullable item: obj?)                                        |         通过对象来删除 Item          |
|                                    removeItemByPos(@IntRange(from = 0) pos: Int)                                    |         通过 pos 来删除对象          |
| removeItemsByPos(@IntRange(from = 0) startPos: Int, @IntRange(from = 0) endPos: Int,includeEndPos: Boolean = false) | 删除[startPos]到[endPos]范围内的元素 |
|                                                     clearItem()                                                     |              清空 items              |

## Utils 介绍

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
