package com.rickshory.vegnab.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rickshory.vegnab.R
import com.rickshory.vegnab.roomdb.entities.Visit
import kotlinx.android.synthetic.main.visit_list_item.view.*

class VisitsListAdapter : RecyclerView.Adapter<VisitsListAdapter.VisitsViewHolder>() {
    private val TAG = this::class.java.simpleName
    private var visits = emptyList<Visit>() // cached copy of list of Visits

    fun setVisits(visits: List<Visit>) {
        Log.d(TAG, "in setVisits")
        this.visits = visits
        notifyDataSetChanged()
    }

    fun sayHello() {
        // empty stub
    }

    inner class VisitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameItemView: TextView = itemView.vli_name
        val dateNotesItemView: TextView = itemView.vli_date_notes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitsViewHolder {
        val itemView =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.visit_list_item, parent, false);
        return VisitsViewHolder(itemView)
    }

    // If a context is needed outside of the onCreateViewHolder() method, as long as there's a ViewHolder instance
    // available, a context can be retrieved via: viewHolder.itemView.getContext().

    override fun onBindViewHolder(holder: VisitsViewHolder, position: Int) {
        val current = visits[position]
        holder.nameItemView.text = current.name
        holder.dateNotesItemView.text = current.notes // TODO add date later
    }

    override fun getItemCount() = visits.size
}