package com.example.rickandmorty.ui.simplecharactrsadapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.characterdetails.CharacterDetailsFragment
import com.example.rickandmorty.ui.characters.api.CharacterApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_character.view.*

class CharacterAdapter(private val characters: List<CharacterApi>):
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    var characterItemId: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_character, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        val imageUri: Uri
        imageUri = Uri.parse(character.image)
        Picasso.get()
            .load(imageUri)
            .error(R.drawable.error)
            .into(holder.character_image)
        holder.character_name.text = "Name: " + character.name
        holder.character_species.text = "Species: " + character.species
        holder.character_status.text = "Status: " + character.status
        holder.character_gender.text = "Gender: " + character.gender

        //Open CharacterDetailsFragment
        holder.mView.setOnClickListener {
            characterItemId = character.id
            Log.d("TAG", "character.id = $characterItemId")
            val activity = it.context as AppCompatActivity
            val fragmentCharacterDetailsFragment = CharacterDetailsFragment.newInstance(
                characterItemId!!)
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragmentCharacterDetailsFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val character_image: ImageView = itemView.character_image
        val character_name: TextView = itemView.character_name
        val character_species: TextView = itemView.character_species
        val character_status: TextView = itemView.character_status
        val character_gender: TextView = itemView.character_gender

        val mView: View = itemView
    }
}