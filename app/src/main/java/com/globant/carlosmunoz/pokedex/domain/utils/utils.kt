package com.globant.carlosmunoz.pokedex.domain.utils

import android.widget.ImageView
import coil.decode.SvgDecoder
import coil.load
import coil.request.CachePolicy
import com.globant.carlosmunoz.pokedex.R

fun ImageView.loadCoil(pokemonId: Long){
    this.load( "${Constants.IMAGE_URL}${pokemonId}.svg"){
        decoderFactory(SvgDecoder.Factory())
        crossfade(500)
        diskCacheKey(pokemonId.toString())
        diskCachePolicy(CachePolicy.ENABLED)
        placeholder(R.drawable.pokemon_compass)
        error(R.drawable.pokemon_compass)
    }
}