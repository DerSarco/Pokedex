package com.globant.carlosmunoz.pokedex.data.api

import com.globant.carlosmunoz.pokedex.domain.entities.PokemonDetail
import com.globant.carlosmunoz.pokedex.domain.entities.PokemonList
import com.globant.carlosmunoz.pokedex.domain.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConnection {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())

    suspend fun getList(): Response<PokemonList> {
        return retrofit.baseUrl(Constants.BASE_LIST_URl).build().create(ApiPokemonList::class.java)
            .getPokemonList()
    }

    suspend fun getPokemonDetail(pokemonName: String): Response<PokemonDetail> {
        return retrofit.baseUrl(Constants.BASE_LIST_URl).build()
            .create(ApiPokemonDetail::class.java)
            .getPokemonDetail(pokemonName)
    }

}