package com.sabeeh.graphkotlin.view

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.platform.LocalContext
import androidx.recyclerview.widget.RecyclerView
import com.sabeeh.graphkotlin.R
import kotlinx.coroutines.currentCoroutineContext
import java.util.ArrayList
import kotlin.coroutines.coroutineContext

class CustomAdapter(private val mList: ArrayList<String>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // sets the text to the textview from our itemHolder class
        holder.textViewName.text = mList[position]
        holder.textViewName.setOnClickListener {
            Log.d(""+holder.textViewName.text,""+holder.textViewName.text+" Clicked")

        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val textViewName: TextView = itemView.findViewById(R.id.graphName)
    }
}