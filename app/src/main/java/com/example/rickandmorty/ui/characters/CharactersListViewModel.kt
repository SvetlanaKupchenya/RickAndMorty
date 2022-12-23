package com.example.rickandmorty.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.ui.characters.api.*
import kotlinx.coroutines.flow.Flow

class CharactersListViewModel : ViewModel() {

    var charactersNetworkSource: CharactersNetworkSource

    init {
        charactersNetworkSource = CharactersRetrofitInstance
            .getCharactersRetrofitInstance()
            .create(CharactersNetworkSource::class.java)
    }

    fun getAllCharactersList(): Flow<PagingData<CharacterApi>> {
        return Pager (
            config = PagingConfig(pageSize = 42, maxSize = 200),
            pagingSourceFactory = { CharactersAllPagingSource(charactersNetworkSource) }
        ).flow.cachedIn(viewModelScope)
    }

    fun getFilteredCharactersList(options: HashMap<String, String>): Flow<PagingData<CharacterApi>> {
        return Pager (
            config = PagingConfig(pageSize = 42, maxSize = 200),
            pagingSourceFactory = { CharactersFilteredPagingSource(charactersNetworkSource, options) }
        ).flow.cachedIn(viewModelScope)
    }
}