package com.globant.carlosmunoz.pokedex.data.api


import com.globant.carlosmunoz.pokedex.domain.entities.PokemonList
import retrofit2.Response
import retrofit2.http.GET

interface ApiPokemonList {

    @GET("pokemon?limit=151")
    suspend fun getPokemonList(): Response<PokemonList>

}