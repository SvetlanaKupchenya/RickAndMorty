package com.example.rickandmorty.ui.episodes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.databinding.FragmentEpisodesListBinding
import com.example.rickandmorty.ui.episodes.adapter.EpisodesPagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EpisodesListFragment : Fragment() {

    private var _binding: FragmentEpisodesListBinding? = null
    lateinit var recyclerViewAdapter: EpisodesPagingAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val episodesListViewModel =
            ViewModelProvider(this,
                ViewModelProvider.NewInstanceFactory()).get(EpisodesListViewModel::class.java)

        _binding = FragmentEpisodesListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rvCardEpisode = binding.rvCardEpisode

        //initRecyclerView()
        rvCardEpisode.apply {
            layoutManager = GridLayoutManager(context, 2)
            recyclerViewAdapter = EpisodesPagingAdapter()
            adapter = recyclerViewAdapter
        }

        //initViewModel()
        lifecycleScope.launchWhenCreated {
            episodesListViewModel.getAllEpisodesList().collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }

        //Get input data from the fields
        var queryEpisodeName: String
        var queryEpisodeEpisode: String

        val searchEpisodeName: EditText = binding.searchEpisodeName
        val searchEpisodeEpisode: EditText = binding.searchEpisodeEpisode

        searchEpisodeName.text.clear()
        searchEpisodeEpisode.text.clear()

        //Apply custom filters
        val searchButton: Button = binding.searchEpisodeButton
        val undoSearchButton = binding.undoSearchEpisodeButton

        //Set fitters
        searchButton.setOnClickListener{
            queryEpisodeName = searchEpisodeName.text.toString()
            queryEpisodeEpisode = searchEpisodeEpisode.text.toString()

            val options: HashMap<String, String> = HashMap()
            options["name"] = queryEpisodeName
            options["episode"] = queryEpisodeEpisode

            lifecycleScope.launch {
                episodesListViewModel.getFilteredEpisodesList(options).collectLatest {
                    recyclerViewAdapter.submitData(it)
                }
            }
        }

        //Undo filters
        undoSearchButton.setOnClickListener {
            searchEpisodeName.text.clear()
            searchEpisodeEpisode.text.clear()

            lifecycleScope.launch {
                episodesListViewModel.getAllEpisodesList().collectLatest {
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