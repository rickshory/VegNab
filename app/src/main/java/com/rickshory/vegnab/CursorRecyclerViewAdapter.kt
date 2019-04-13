package com.rickshory.vegnab

import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VisitViewHolder(val containerView: View) :
    RecyclerView.ViewHolder{containerView} {
        var name: TextView = containerView.FindViewById()
}

private const val TAG = "CursorRVAdapt"

class CursorRecyclerViewAdapter:
    RecyclerView.Adapter<VisitViewHolder>() {
}