package com.example.rickandmorty.ui.characters

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
import com.example.rickandmorty.databinding.FragmentCharactersListBinding
import com.example.rickandmorty.ui.characters.adapter.CharactersPagingAdapter
import com.example.rickandmorty.ui.characters.api.CharactersFilteredPagingSource
import kotlinx.android.synthetic.main.fragment_characters_list.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class CharactersListFragment : Fragment() {

    private var _binding: FragmentCharactersListBinding? = null
    lateinit var recyclerViewAdapter: CharactersPagingAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val charactersListViewModel =
            ViewModelProvider(this,
                ViewModelProvider.NewInstanceFactory()).get(CharactersListViewModel::class.java)

        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rvCardCharacter = binding.rvCardCharacter

        //initRecyclerView()
        rvCardCharacter.apply {
            layoutManager = GridLayoutManager(context, 2)
            recyclerViewAdapter = CharactersPagingAdapter()
            adapter = recyclerViewAdapter
        }

        //initViewModel()
        lifecycleScope.launchWhenCreated {
            charactersListViewModel.getAllCharactersList().collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }

        //Get input data from the fields
        var queryName: String
        var queryStatus: String
        var querySpecies: String
        var queryGender: String

        val searchName: EditText = binding.searchName
        val searchStatus: EditText = binding.searchStatus
        val searchSpecies: EditText = binding.searchSpecies
        val searchGender: EditText = binding.searchGender

        searchName.text.clear()
        searchStatus.text.clear()
        searchSpecies.text.clear()
        searchGender.text.clear()

        //Apply custom filters
        val searchButton: Button = binding.searchButton
        val undoSearchButton: Button = binding.undoSearchButton

        searchButton.setOnClickListener {
            queryName = searchName.text.toString()
            queryStatus = searchStatus.text.toString()
            querySpecies = searchSpecies.text.toString()
            queryGender = searchGender.text.toString()

            val options: HashMap<String, String> = HashMap()
            options.put("name", queryName)
            options.put("status", queryStatus)
            options.put("species", querySpecies)
            options.put("gender", queryGender)

            lifecycleScope.launch {
                charactersListViewModel.getFilteredCharactersList(options).collectLatest {
                    recyclerViewAdapter.submitData(it)
                }
            }
        }

        //Undo custom filters, return to main results from charactersListResponse
        undoSearchButton.setOnClickListener {
            searchName.text.clear()
            searchStatus.text.clear()
            searchSpecies.text.clear()
            searchGender.text.clear()

            lifecycleScope.launch {
                charactersListViewModel.getAllCharactersList().collectLatest {
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