# BaseGcodeBindingAdapter使用

## 1. 首先让对象继承 `BaseGcodeItem` 接口，并将布局id作为 `getItemBindViewType` 的返回值，例如

```kotlin
data class Person(val firstName:String,val lastName: String):BaseGcodeItem{
    override fun getItemViewType(): Int {
        //nothing to do
    }

    override fun getItemBindViewType(): Int {
        return R.layout.item_bind_layout
    }
}
```

## 2. 编辑布局文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>
    <data>
        <variable
            name="item"
            type="com.gcode.gutilssampledemo.Person" />
    </data>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:orientation="vertical" android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{item.firstName}"/>
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{item.lastName}"/>
    </LinearLayout>
</layout>
```

## 3. 设置 `VariableId` ，例如:

```kotlin
inner class TestBaseBindingAdapter(items: MutableList<BaseItem>) :BaseUtilBindingAdapter(items){
        override fun setVariableId(): Int {
            return BR.item
        }
    }
```

注意，这里的item对应布局文件中的**variable**的**name**