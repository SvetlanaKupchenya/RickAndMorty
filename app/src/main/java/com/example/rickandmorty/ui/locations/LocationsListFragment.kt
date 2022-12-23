package com.example.rickandmorty.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.databinding.FragmentLocationsListBinding
import com.example.rickandmorty.ui.locations.adapter.LocationsPagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LocationsListFragment : Fragment() {

    private var _binding: FragmentLocationsListBinding? = null
    lateinit var recyclerViewAdapter: LocationsPagingAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val locationsListViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                LocationsListViewModel::class.java)

        _binding = FragmentLocationsListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rvCardLocation = binding.rvCardLocation

        //initRecyclerView()
        rvCardLocation.apply {
            layoutManager = GridLayoutManager(context, 2)
            recyclerViewAdapter = LocationsPagingAdapter()
            adapter = recyclerViewAdapter
        }

        //initViewModel()
        lifecycleScope.launchWhenCreated {
            locationsListViewModel.getAllLocationsList().collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }

        //Get input data from the fields
        var queryLocationName: String
        var queryLocationType: String
        var queryLocationDimension: String

        val searchLocationName: EditText = binding.searchLocatonName
        val searchLocationType: EditText = binding.searchLocationType
        val searchLocationDimension: EditText = binding.searchLocationDimension

        searchLocationName.text.clear()
        searchLocationType.text.clear()
        searchLocationDimension.text.clear()

        //Apply custom filters
        val searchButton: Button = binding.searchLocationButton
        val undoSearchButton = binding.undoSearchLocationButton

        searchButton.setOnClickListener {
            queryLocationName = searchLocationName.text.toString()
            queryLocationType = searchLocationType.text.toString()
            queryLocationDimension = searchLocationDimension.text.toString()

            val options: HashMap<String, String> = HashMap()
            options.put("name", queryLocationName)
            options.put("type", queryLocationType)
            options.put("dimension", queryLocationDimension)

            lifecycleScope.launch {
                locationsListViewModel.getFilteredLocationsList(options).collectLatest {
                    recyclerViewAdapter.submitData(it)
                }
            }
        }

        //Undo custom filters
        undoSearchButton.setOnClickListener {
            searchLocationName.text.clear()
            searchLocationType.text.clear()
            searchLocationDimension.text.clear()

            lifecycleScope.launch {
                locationsListViewModel.getAllLocationsList().collectLatest {
                    recyclerViewAdapter.submitData(it)
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}