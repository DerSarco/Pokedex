package com.globant.carlosmunoz.pokedex.data.api

import com.globant.carlosmunoz.pokedex.domain.entities.PokemonDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiPokemonDetail {
    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetail(@Path(value = "pokemonName") pokemonName: String): Response<PokemonDetail>
}