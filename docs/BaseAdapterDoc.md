### BaseBindingAdapter使用

1. 首先让对象继承BaseItem接口，并将布局id作为getItemViewType的返回值，例如

   ```kotlin
   data class Person(val firstName:String,val lastName: String):BaseItem{
       override fun getItemViewType(): Int {
           return R.layout.item_layout
       }
   
       override fun getItemBindViewType(): Int {
           //nothing to do
       }
   }
   ```

2. 编辑布局文件

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
       android:orientation="vertical" android:layout_width="match_parent"
       android:layout_height="wrap_content">
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
   
3. 为RecyclerView的子项进行赋值

   ```kotlin
   class TestBaseAdapter(items: MutableList<BaseItem>) : BaseAdapter(items) {
           override fun bindData(holder: RecyclerViewHolder, position: Int, item: BaseItem) {
               holder.findViewById<TextView>(R.id.firstName).text = (item as Person).firstName
               holder.findViewById<TextView>(R.id.lastName).text = item.lastName
           }
       }
```
   