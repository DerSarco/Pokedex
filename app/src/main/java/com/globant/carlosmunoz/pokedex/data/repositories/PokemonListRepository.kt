package com.globant.carlosmunoz.pokedex.data.repositories

import com.globant.carlosmunoz.pokedex.data.api.ApiConnection
import com.globant.carlosmunoz.pokedex.domain.entities.PokemonList
import retrofit2.Response

class PokemonListRepository(private val apiService: ApiConnection) {
    suspend fun getList(): Response<PokemonList> {
        return apiService.getList()
    }
}