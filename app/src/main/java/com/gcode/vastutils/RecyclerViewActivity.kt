package com.gcode.vastutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.gcode.vastadapter.BaseVastBindingAdapter
import com.gcode.vastutils.databinding.ActivityRecyclerViewBinding
import com.gcode.vastutils.model.Person

class RecyclerViewActivity : AppCompatActivity() {

    inner class Adapter(items: MutableList<Person>) : BaseVastBindingAdapter<Person>(items) {
        override fun setVariableId(): Int {
            return BR.item
        }
    }

    private lateinit var binding:ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_recycler_view)

        val adapter = Adapter(ArrayList())
        adapter.setOnItemClickListener(object :BaseVastBindingAdapter.OnItemClickListener{
            override fun onItemClick(itemView: View?, pos: Int, itemId: Long) {

            }
        })
    }
}