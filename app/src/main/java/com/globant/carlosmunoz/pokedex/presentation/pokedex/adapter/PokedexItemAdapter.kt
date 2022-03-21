package com.globant.carlosmunoz.pokedex.presentation.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globant.carlosmunoz.pokedex.databinding.PokedexRowItemBinding
import com.globant.carlosmunoz.pokedex.entities.Pokemon

/*****
 * Project: Pokedex
 * From: com.globant.carlosmunoz.pokedex.presentation.pokedex.adapter
 * Created by Carlos Muñoz on 21-03-22 at 16:49
 ****/

class PokedexItemAdapter(var pokemonList: List<Pokemon>, val listener: (Pokemon) -> Unit) : RecyclerView.Adapter<PokedexItemAdapter.ViewHolder>() {

    private lateinit var binding: PokedexRowItemBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        binding = PokedexRowItemBinding.inflate(LayoutInflater.from(context), parent,false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            bind(pokemonList[position])
        }
    }

    override fun getItemCount(): Int = pokemonList.size


    inner class ViewHolder( binding: PokedexRowItemBinding, val listener: (Pokemon) -> Unit) : RecyclerView.ViewHolder(binding.root){
        private val llItem = binding.linearLayout
        private val tvPokemonName = binding.tvPokemonName

        fun bind(pokemon: Pokemon){
            tvPokemonName.text = pokemon.name
            llItem.setOnClickListener {
                listener
            }
        }
    }
}