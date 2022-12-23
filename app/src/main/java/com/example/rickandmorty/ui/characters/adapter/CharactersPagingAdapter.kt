package com.example.rickandmorty.ui.characters.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.characterdetails.CharacterDetailsFragment
import com.example.rickandmorty.ui.characters.api.CharacterApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_character.view.*

class CharactersPagingAdapter():
    PagingDataAdapter<CharacterApi, CharactersPagingAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Bind RecyclerView
        holder.bind(getItem(position)!!)
        //Open CharacterDetailsFragment
        holder.mView.setOnClickListener {
            val itemId = holder.characterItemId
            Log.d("TAG", "character.id = $itemId")
            val activity = it.context as AppCompatActivity
            val fragmentCharacterDetailsFragment = CharacterDetailsFragment.newInstance(
                itemId!!)
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragmentCharacterDetailsFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_character, parent, false)
        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val character_image: ImageView = view.findViewById(R.id.character_image)
        val character_name: TextView = view.findViewById(R.id.character_name)
        val character_species: TextView = view.findViewById(R.id.character_species)
        val character_status: TextView = view.findViewById(R.id.character_status)
        val character_gender: TextView = view.findViewById(R.id.character_gender)

        val mView: View = view
        var characterItemId: Int? = null

        fun bind(data: CharacterApi) {
            characterItemId = data.id
            val imageUri: Uri
            imageUri = Uri.parse(data.image)
            Picasso.get()
                .load(imageUri)
                .error(R.drawable.error)
                .into(character_image)
            character_name.text = "Name: " + data.name
            character_species.text = "Species: " + data.species
            character_status.text = "Status: " + data.status
            character_gender.text = "Gender: " + data.gender
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<CharacterApi>() {
        override fun areItemsTheSame(oldItem: CharacterApi, newItem: CharacterApi): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterApi, newItem: CharacterApi): Boolean {
            return oldItem == newItem
        }
    }
}