package com.globant.carlosmunoz.pokedex.presentation.pokedex.view

import androidx.lifecycle.*
import com.globant.carlosmunoz.pokedex.data.repositories.PokemonListRepository
import com.globant.carlosmunoz.pokedex.domain.entities.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.NullPointerException

class PokemonListViewModel(private val repository: PokemonListRepository) : ViewModel() {
    private var pokemonList: MutableLiveData<PokemonList> = MutableLiveData()
    val pokemonListFetched: MutableLiveData<PokemonList>
        get() = pokemonList

    fun getPokemonList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val fetchedData = repository.getList().body()
                fetchedData?.results?.forEachIndexed { index, _ ->
                    fetchedData.results[index].id = (index + 1).toLong()
                }
                pokemonList.postValue(fetchedData)
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