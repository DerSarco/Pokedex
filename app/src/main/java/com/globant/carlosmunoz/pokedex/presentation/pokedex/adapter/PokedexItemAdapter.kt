package com.globant.carlosmunoz.pokedex.presentation.pokedex.adapter

import android.content.Context
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.decode.SvgDecoder
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.globant.carlosmunoz.pokedex.R
import com.globant.carlosmunoz.pokedex.databinding.PokedexRowItemBinding
import com.globant.carlosmunoz.pokedex.domain.entities.Result
import com.globant.carlosmunoz.pokedex.domain.utils.loadCoil

/*****
 * Project: Pokedex
 * From: com.globant.carlosmunoz.pokedex.presentation.pokedex.adapter
 * Created by Carlos Muñoz on 21-03-22 at 16:49
 ****/

class PokedexItemAdapter(
    var pokemonList: List<Result>,
    private val listener: (Result) -> Unit,
) : RecyclerView.Adapter<PokedexItemAdapter.ViewHolder>() {

    private lateinit var binding: PokedexRowItemBinding
    private lateinit var context: Context

    fun setNewData(newResultList: List<Result>) {
        pokemonList = newResultList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        binding = PokedexRowItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bind(pokemonList[position], listener)
        }
    }

    override fun getItemCount(): Int = pokemonList.size


    inner class ViewHolder(binding: PokedexRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val cardResultItem = binding.cardPokemonItem
        private val tvResultName = binding.tvPokemonName
        private val ivPokemonPhoto = binding.ivPokemonPhoto

        fun bind(pokemon: Result, listener: (Result) -> Unit) {
            tvResultName.text = pokemon.name.replaceFirstChar { it.uppercase() }
            Log.d("PokeID", pokemon.id.toString())
            if (pokemon.id != null) {
                ivPokemonPhoto.loadCoil(pokemon.id!!)
            }
            cardResultItem.setOnClickListener {
                listener(pokemon)
            }
        }
    }
}