package com.rickshory.vegnab.adapters

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.visit_list_item.view.*

class VisitsListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<VisitsListAdapter.VisitsViewHolder>() {


    inner class VisitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameItemView: TextView = itemView.vli_name
    }
    

}