package com.example.rickandmorty.ui.locationdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.characterdetails.CharacterDetailsFragment
import com.example.rickandmorty.ui.characters.api.CharacterApi
import com.example.rickandmorty.ui.locations.api.LocationApi
import com.example.rickandmorty.ui.simplecharactrsadapter.CharacterAdapter
import kotlinx.android.synthetic.main.fragment_character_details.*
import kotlinx.android.synthetic.main.fragment_episode_details.*
import kotlinx.android.synthetic.main.fragment_location_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationDetailsFragment : Fragment() {

    var locationItemId: Int = 0
    var characterItemId: Int = 0

    companion object {
        fun newInstance(locationId: Int) = LocationDetailsFragment().apply {
            locationItemId = locationId
        }
    }

    private lateinit var viewModel: LocationDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_location_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        providedLocationId.text = "Identifier: " + locationItemId.toString()

        //Send a simple GET Request
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(LocationDetailsNetworkSource::class.java)

        var listOfCharacters: List<Int>

        api.fetchSingleLocation(locationItemId).enqueue(object: Callback<LocationApi> {
            override fun onResponse(call: Call<LocationApi>, response: Response<LocationApi>) {
                if (response.isSuccessful) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    providedLocationName.text = "Name: " + response.body()!!.name
                    providedLocationType.text = "Type: " + response.body()!!.type
                    providedLocationDimension.text = "Dimension: " + response.body()!!.dimension
                    providedLocationUrl.text = "This URL: " + response.body()!!.url
                    providedLocationCreated.text = "Created: " + response.body()!!.created

                    //Make list of characters
                    val mutableListOfCharacters = mutableListOf<Int>()
                    response.body()!!.residents.forEach {
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
            override fun onFailure(call: Call<LocationApi>, t: Throwable) {
                Log.d("TAG", "onFailure")
            }
        })
    }

    //Apply recycler adapter
    private fun showData(characters: List<CharacterApi>) {
        providedLocationResidents.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = CharacterAdapter(characters)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LocationDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}