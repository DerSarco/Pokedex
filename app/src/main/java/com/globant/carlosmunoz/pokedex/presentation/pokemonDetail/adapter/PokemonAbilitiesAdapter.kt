package com.globant.carlosmunoz.pokedex.presentation.pokemonDetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globant.carlosmunoz.pokedex.databinding.PokemonAbilityItemBinding

class PokemonAbilitiesAdapter(private val abilitiesList: List<String>) :
    RecyclerView.Adapter<PokemonAbilitiesAdapter.ViewHolder>() {

    private lateinit var binding: PokemonAbilityItemBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        binding = PokemonAbilityItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bind(abilitiesList[position])
        }
    }

    override fun getItemCount(): Int = abilitiesList.size
    inner class ViewHolder(binding: PokemonAbilityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val tvPokemonAbility = binding.tvPokemonAbility

        fun bind(ability: String) {
            tvPokemonAbility.text = ability
        }

    }
}