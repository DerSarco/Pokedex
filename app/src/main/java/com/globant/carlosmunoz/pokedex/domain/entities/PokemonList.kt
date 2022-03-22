package com.globant.carlosmunoz.pokedex.domain.entities


import com.google.gson.annotations.SerializedName

data class PokemonList(
    @SerializedName("results")
    val results: List<Result>
)