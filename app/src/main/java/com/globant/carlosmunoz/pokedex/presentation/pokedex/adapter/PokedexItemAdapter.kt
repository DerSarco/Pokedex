package com.globant.carlosmunoz.pokedex.presentation.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globant.carlosmunoz.pokedex.databinding.PokedexRowItemBinding
import com.globant.carlosmunoz.pokedex.domain.entities.Result

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

    fun setNewData(newResultList: List<Result>){
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

        fun bind(Result: Result, listener: (Result) -> Unit) {
            tvResultName.text = Result.name.replaceFirstChar { it.uppercase() }
            cardResultItem.setOnClickListener {
                listener(Result)
            }
        }
    }
}