package com.example.rickandmorty.ui.episodedetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.characters.api.CharacterApi
import com.example.rickandmorty.ui.episodes.api.EpisodeApi
import com.example.rickandmorty.ui.simplecharactrsadapter.CharacterAdapter
import kotlinx.android.synthetic.main.fragment_episode_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EpisodeDetailsFragment : Fragment() {

    var episodeItemId: Int = 0

    companion object {
        fun newInstance(episodeId: Int) = EpisodeDetailsFragment().apply {
            episodeItemId = episodeId
        }
    }

    private lateinit var viewModel: EpisodeDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_episode_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        providedEpisodeId.text = "Identifier: " + episodeItemId.toString()

        //Send a simple GET Request
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(EpisodeDetailsNetworkSource::class.java)

        var listOfCharacters: List<Int>

        api.fetchSingleEpisode(episodeItemId).enqueue(object: Callback<EpisodeApi> {
            override fun onResponse(call: Call<EpisodeApi>, response: Response<EpisodeApi>) {
                if (response.isSuccessful) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    providedEpisodeName.text = "Name: " + response.body()!!.name
                    providedEpisodeAirDate.text = "Air date: " + response.body()!!.air_date
                    providedEpisodeEpisode.text = "Episode: " + response.body()!!.episode
                    providedEpisodeUrl.text = "This URL: " + response.body()!!.url
                    providedEpisodeCreated.text = "Created: " + response.body()!!.created

                    //Make list of characters
                    val mutableListOfCharacters = mutableListOf<Int>()
                    response.body()!!.characters.forEach {
                        val index: Int = it.lastIndexOf("/")
                        val subString: String = it.substring(index + 1)
                        Log.d("TAG", "index = $index, subString = $subString")
                        mutableListOfCharacters.add(subString.toInt())
                    }
                    listOfCharacters = mutableListOfCharacters
                    Log.d("TAG", "listOfCharacters = $listOfCharacters")

                    api.fetchMultipleCharacters(listOfCharacters).enqueue(object: Callback<List<CharacterApi>> {
                        override fun onResponse(call: Call<List<CharacterApi>>, response: Response<List<CharacterApi>>) {
                            if (response.isSuccessful) {
                                showData(response.body()!!)
                                Log.d("TAG", "onResponse: ${response.body()}")
                            } else {
                                val toast = Toast.makeText(context, "No characters found!", Toast.LENGTH_SHORT)
                                toast.show()
                            }
                        }
                        override fun onFailure(call: Call<List<CharacterApi>>, t: Throwable) {
                            Log.d("TAG", "onFailure")
                        }
                    })

                }
            }
            override fun onFailure(call: Call<EpisodeApi>, t: Throwable) {
                Log.d("TAG", "onFailure")
            }
        })
    }

    //Apply recycler adapter
    private fun showData(characters: List<CharacterApi>) {
        providedEpisodeCharacters.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = CharacterAdapter(characters)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EpisodeDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }
}