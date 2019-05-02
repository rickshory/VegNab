package com.rickshory.vegnab.adapters

import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rickshory.vegnab.R
import com.rickshory.vegnab.contracts.Contract_Visits
import com.rickshory.vegnab.models.Visit
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.visit_list_item.*
import kotlinx.android.synthetic.main.visit_list_item.view.*

class VisitViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer{
    init { // is this correct?
        // Define click listener for the ViewHolder's View.
        containerView.setOnClickListener { Log.d(TAG, "Element $adapterPosition clicked.") }
//        textView = containerView.findViewById(R.id.textView)
    }
    var visit: Visit? = null
        set(value) { // custom setter
            field = value // backing field
            containerView.vli_name.text = value?.name
            containerView.vli_date_notes.text = value?.notes // fix later to include DateStamp
        }
}

private const val TAG = "VisitRVCursorAdapt"

class VisitRVCursorAdapter(private var cursor: Cursor?):
    RecyclerView.Adapter<VisitViewHolder>() {

    var items: List<Visit> = emptyList()

    fun loadItems(newItems: List<Visit>) {
        items = newItems
    }

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitViewHolder {
        Log.d(TAG,"onCreateViewHolder: new View requested")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.visit_list_item, parent, false)
        return VisitViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     * Override [.onBindViewHolder] instead if Adapter can handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: VisitViewHolder, position: Int) {
        Log.d(TAG,"onBindViewHolder: starts")
        val cursor = cursor // avoid problems with smart cast
        if (cursor ==  null || cursor.count == 0) {
            Log.d(TAG,"onBindViewHolder: give message if empty")
            holder.vli_name.setText("No items yet")
            holder.vli_date_notes.setText("To start a visit, choose the Project and Plot Type")
            holder.vli_export.visibility = View.GONE
            holder.vli_hide_visit.visibility = View.GONE
        } else {
            if (!cursor.moveToPosition(position)) {
                throw IllegalStateException("Could not move cursor to position $position")
            }
            // Create a Visit opject from the data in the cursor
            val visit = Visit(
                cursor.getString(cursor.getColumnIndex(Contract_Visits.Columns.VISIT_NAME)),
                cursor.getString(cursor.getColumnIndex(Contract_Visits.Columns.VISIT_NOTES)),
                cursor.getString(cursor.getColumnIndex(Contract_Visits.Columns.VISIT_LOCATION))
            )
                // ID is not set in the constructor
            visit.id = cursor.getLong(cursor.getColumnIndex(Contract_Visits.Columns.ID))
            holder.vli_name.text = visit.name
            holder.vli_date_notes.text = visit.notes // change this later to included datestamp
//            holder.vli_location.text = visit.location // probably won't use here
            holder.vli_export.visibility = View.VISIBLE
            holder.vli_hide_visit.visibility = View.VISIBLE
        }
        TODO("Add long-press options, is this the correct place?")
//        holder.visit = items[position]
//        holder.containerView.setOnClickListener { onClick(items[position]) }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}