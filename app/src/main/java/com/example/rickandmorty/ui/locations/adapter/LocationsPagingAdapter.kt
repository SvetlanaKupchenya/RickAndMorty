package com.example.rickandmorty.ui.locations.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.locationdetails.LocationDetailsFragment
import com.example.rickandmorty.ui.locations.api.LocationApi
import kotlinx.android.synthetic.main.card_location.view.*

class LocationsPagingAdapter():
    PagingDataAdapter<LocationApi, LocationsPagingAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Bind RecyclerView
        holder.bind(getItem(position)!!)
        //Open LocationDetailsFragment
        holder.mView.setOnClickListener {
            val itemId = holder.locationItemId
            Log.d("TAG", "location.id = $itemId")
            val activity = it.context as AppCompatActivity
            val fragmentLocationDetailsFragment = LocationDetailsFragment.newInstance(
                itemId!!)
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragmentLocationDetailsFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_location, parent, false)
        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val location_name: TextView = itemView.location_name
        val location_type: TextView = itemView.location_type
        val location_dimension: TextView = itemView.location_dimension

        val mView: View = view
        var locationItemId: Int? = null

        fun bind(data: LocationApi) {
            locationItemId = data.id
            location_name.text = "Name: " + data.name
            location_type.text = "Type: " + data.type
            location_dimension.text = "Dimension: " + data.dimension
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<LocationApi>() {
        override fun areItemsTheSame(oldItem: LocationApi, newItem: LocationApi): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: LocationApi, newItem: LocationApi): Boolean {
            return oldItem == newItem
        }
    }
}