package com.globant.carlosmunoz.pokedex.presentation.pokedex.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.globant.carlosmunoz.pokedex.data.repositories.PokemonListRepository
import com.globant.carlosmunoz.pokedex.domain.entities.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.NullPointerException

class PokemonListViewModel(private val repository: PokemonListRepository) : ViewModel() {
    private var pokemonList: MutableLiveData<PokemonList> = MutableLiveData()
    val pokemonListFetched: MutableLiveData<PokemonList>
        get() = pokemonList

    fun getPokemonList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                pokemonList.postValue(repository.getList().body())
            }
        }
    }
}

class PokemonListViewModelFactory(private val repository: PokemonListRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonListViewModel::class.java)) {
            return PokemonListViewModel(repository) as T
        } else {
            throw NullPointerException("ViewModel class Unknown")
        }

    }
}