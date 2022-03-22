package com.globant.carlosmunoz.pokedex.presentation.pokemonDetail.view

import androidx.lifecycle.*
import com.globant.carlosmunoz.pokedex.data.repositories.PokemonDetailRepository
import com.globant.carlosmunoz.pokedex.data.repositories.PokemonListRepository
import com.globant.carlosmunoz.pokedex.domain.entities.PokemonDetail
import com.globant.carlosmunoz.pokedex.domain.entities.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.NullPointerException

class PokemonDetailViewModel(private val repository: PokemonDetailRepository) :
    ViewModel() {

    private var pokemonDetail: MutableLiveData<PokemonDetail> = MutableLiveData()
    val pokemonDetailFetched: LiveData<PokemonDetail>
        get() = pokemonDetail

    fun getPokemonDetail(pokemonName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                pokemonDetail.postValue(repository.getPokemonDetail(pokemonName).body())
            }
        }
    }

}

class PokemonDetailViewModelFactory(private val repository: PokemonDetailRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonDetailViewModel::class.java)) {
            return PokemonDetailViewModel(repository) as T
        } else {
            throw NullPointerException("ViewModel class unknown")
        }
    }
}