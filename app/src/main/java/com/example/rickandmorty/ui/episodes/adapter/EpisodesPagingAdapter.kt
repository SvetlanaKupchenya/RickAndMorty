package com.example.rickandmorty.ui.episodes.adapter

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
import com.example.rickandmorty.ui.episodedetails.EpisodeDetailsFragment
import com.example.rickandmorty.ui.episodes.api.EpisodeApi
import kotlinx.android.synthetic.main.card_episode.view.*

class EpisodesPagingAdapter():
    PagingDataAdapter<EpisodeApi, EpisodesPagingAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Bind RecyclerView
        holder.bind(getItem(position)!!)
        //Open EpisodeDetailsFragment
        holder.mView.setOnClickListener {
            val itemId = holder.episodeItemId
            Log.d("TAG", "Episode.id = $itemId")
            val activity = it.context as AppCompatActivity
            val fragmentEpisodeDetailsFragment = EpisodeDetailsFragment.newInstance(
                itemId!!)
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragmentEpisodeDetailsFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_episode, parent, false)
        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val episode_name: TextView = itemView.episode_name
        val episode_episode: TextView = itemView.episode_episode
        val episode_air_date: TextView = itemView.episode_air_date

        val mView: View = view
        var episodeItemId: Int? = null

        fun bind(data: EpisodeApi) {
            episodeItemId = data.id

            episode_name.text = "Name: " + data.name
            episode_episode.text = "Episode: " + data.episode
            episode_air_date.text = "Air_date: " + data.air_date
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<EpisodeApi>() {
        override fun areItemsTheSame(oldItem: EpisodeApi, newItem: EpisodeApi): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: EpisodeApi, newItem: EpisodeApi): Boolean {
            return oldItem == newItem
        }

    }
}