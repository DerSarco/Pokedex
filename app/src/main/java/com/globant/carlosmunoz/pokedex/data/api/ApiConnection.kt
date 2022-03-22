package com.globant.carlosmunoz.pokedex.data.api

import android.util.Log
import com.globant.carlosmunoz.pokedex.domain.entities.PokemonDetail
import com.globant.carlosmunoz.pokedex.domain.entities.PokemonList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConnection {
    private val BASE_LIST_URl = "https://pokeapi.co/api/v2/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())


    suspend fun getList(): Response<PokemonList> {
        val response: Response<PokemonList> =
            retrofit.baseUrl(BASE_LIST_URl).build().create(ApiPokemonList::class.java)
                .getPokemonList()
        if (response.isSuccessful) {
            Log.i("API Response", response.body().toString())
        }
        return response
    }

    suspend fun getPokemonDetail(pokemonName: String): Response<PokemonDetail> {
        val response =
            retrofit.baseUrl(BASE_LIST_URl).build().create(ApiPokemonDetail::class.java)
                .getPokemonDetail(pokemonName)
        if (response.isSuccessful) {
            Log.i("API Response", response.body().toString())
        }
        return response
    }

}