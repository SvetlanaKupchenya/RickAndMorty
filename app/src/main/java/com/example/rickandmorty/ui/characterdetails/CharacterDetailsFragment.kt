package com.example.rickandmorty.ui.characterdetails

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.characters.api.CharacterApi
import com.example.rickandmorty.ui.episodedetails.EpisodeDetailsFragment
import com.example.rickandmorty.ui.locationdetails.LocationDetailsFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_character_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterDetailsFragment : Fragment() {

    var characterItemId: Int = 0
    var episodeItemId: Int = 0
    var locationItemId: Int = 0

    companion object {
        fun newInstance(characterId: Int) = CharacterDetailsFragment().apply {
            characterItemId = characterId
        }
    }

    private lateinit var viewModel: CharacterDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_character_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        providedCharacterId.text = "Identifier: " + characterItemId.toString()

        //Send a simple GET Request
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(CharacterDetailsNetworkSource::class.java)

        api.fetchSingleCharacter(characterItemId).enqueue(object: Callback<CharacterApi> {
            override fun onResponse(call: Call<CharacterApi>, response: Response<CharacterApi>) {
                if (response.isSuccessful) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    providedCharacterName.text = "Name: " + response.body()!!.name
                    providedCharacterStatus.text = "Status: " + response.body()!!.status
                    providedCharacterSpecies.text = "Species: " + response.body()!!.species
                    providedCharacterType.text = "Type: " + response.body()!!.type
                    providedCharacterGender.text = "Gender: " + response.body()!!.gender
                    providedCharacterOriginName.text = "Origin name: " + response.body()!!.origin.name
                    providedCharacterOriginURL.text = "Origin URL: " + response.body()!!.origin.url
                    providedCharacterLocationName.text = "Location name: " + response.body()!!.location.name
                    providedCharacterLocationURL.text = "Location URL: " + response.body()!!.location.url
                    providedCharacterURL.text = "This URL: " + response.body()!!.url
                    providedCharacterCreated.text = "Created: " + response.body()!!.created

                    val imageUri = Uri.parse(response.body()!!.image)
                    Picasso.get()
                        .load(imageUri)
                        .error(R.drawable.error)
                        .into(providedCharacterImage)

                    val listOfEpisodes = mutableListOf<String>()
                    response.body()!!.episode.forEach {
                        listOfEpisodes.add(it)
                    }
                    val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                        context!!, android.R.layout.simple_list_item_1, listOfEpisodes
                    )
                    providedCharacterEpisodes.adapter = arrayAdapter

                    //Open EpisodeDetailsFragment
                    providedCharacterEpisodes.setOnItemClickListener { parent, view, position, id ->
                        Log.d("TAG", "episode URL = ${listOfEpisodes[position]}")

                        //Get episode id out of episode URL
                        val index: Int = listOfEpisodes[position].lastIndexOf("/")
                        if (index > 0) {
                            val subString: String = listOfEpisodes[position].substring(index + 1)
                            Log.d("TAG", "index = $index, subString = $subString")
                            episodeItemId = subString.toInt()

                            val activity = view.context as AppCompatActivity
                            val fragmentEpisodeDetailsFragment = EpisodeDetailsFragment.newInstance(
                                episodeItemId)
                            activity.supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.nav_host_fragment_activity_main, fragmentEpisodeDetailsFragment)
                                .addToBackStack(null)
                                .commit()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<CharacterApi>, t: Throwable) {
                Log.d("TAG", "onFailure")
            }
        })

        //Open URL for origin and for location
        providedCharacterOriginURL.setOnClickListener {
            val index: Int = providedCharacterOriginURL.text.lastIndexOf("/")
            if (index > 0) {
                val subString: String = providedCharacterOriginURL.text.substring(index + 1)
                Log.d("TAG", "index = $index, subString = $subString")
                locationItemId = subString.toInt()

                val activity = view.context as AppCompatActivity
                val fragmentLocationDetailsFragment = LocationDetailsFragment.newInstance(
                    locationItemId)
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, fragmentLocationDetailsFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
        providedCharacterLocationURL.setOnClickListener {
            val index: Int = providedCharacterLocationURL.text.lastIndexOf("/")

            if (index > 0) {
                val subString: String = providedCharacterLocationURL.text.substring(index + 1)
                Log.d("TAG", "index = $index, subString = $subString")
                locationItemId = subString.toInt()

                val activity = view.context as AppCompatActivity
                val fragmentLocationDetailsFragment = LocationDetailsFragment.newInstance(
                    locationItemId)
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, fragmentLocationDetailsFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }
}