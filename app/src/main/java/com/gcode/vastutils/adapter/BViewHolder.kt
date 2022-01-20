package com.gcode.vastutils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.gcode.vastadapter.baseadapter.BaseVastAdapterVH
import com.gcode.vastadapter.interfaces.VastAdapterItem
import com.gcode.vastutils.R
import com.gcode.vastutils.model.Picture


/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/19
 */
class BViewHolder(itemView: View) : BaseVastAdapterVH(itemView) {

    private val iv: ImageView

    override fun onBindData(iType: VastAdapterItem) {
        super.onBindData(iType)
        iv.setImageResource((iType as Picture).resId)
    }

    class Factory : BaseVastAdapterVH.Factory {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BaseVastAdapterVH {
            val inflater = LayoutInflater.from(parent.context)
            val itemView: View = inflater.inflate(R.layout.item_imageview, parent, false)
            return BViewHolder(itemView)
        }

        override fun getVastAdapterVHType(): String {
            return "picture"
        }

    }

    init {
        iv = itemView.findViewById(R.id.item_image)
    }
}