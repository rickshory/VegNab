package com.rickshory.vegnab

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VisitViewHolder(val containerView: View) :
    RecyclerView.ViewHolder{containerView} {
        var name: TextView = containerView.FindViewById(R.id.vli_name)
}

private const val TAG = "CursorRVAdapt"

class CursorRecyclerViewAdapter:
    RecyclerView.Adapter<VisitViewHolder>() {
}