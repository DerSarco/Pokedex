package com.globant.carlosmunoz.pokedex.data.repositories

import com.globant.carlosmunoz.pokedex.data.api.ApiConnection
import com.globant.carlosmunoz.pokedex.domain.entities.PokemonDetail
import retrofit2.Response

class PokemonDetailRepository(private val apiConnection: ApiConnection) {

    suspend fun getPokemonDetail(pokemonName: String): Response<PokemonDetail> {
        return apiConnection.getPokemonDetail(pokemonName)
    }
}