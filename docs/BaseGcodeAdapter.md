# BaseGcodeAdapter使用

## 1. 首先让对象继承 `BaseGcodeItem` 接口，并将布局id作为 `getItemViewType` 的返回值，例如

```kotlin
data class Person(val firstName:String,val lastName: String):BaseGcodeItem{
    override fun getItemViewType(): Int {
        return R.layout.item_layout
    }

    override fun getItemBindViewType(): Int {
        //nothing to do
    }
}
```

## 2. 编辑布局文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/util_gold">
    <TextView
        android:id="@+id/firstName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
    <TextView
        android:id="@+id/lastName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</LinearLayout>
```

## 3. 为 `RecyclerView` 的子项进行赋值

```kotlin
class TestBaseAdapter(items: MutableList<Person>) : BaseGcodeAdapter<Person>(items) {
        override fun bindData(holder: RecyclerViewHolder, position: Int, item: Person) {
        holder.findViewById<TextView>(R.id.firstName).text = item.firstName
        holder.findViewById<TextView>(R.id.lastName).text = item.lastName
    }
}
```