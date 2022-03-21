package com.globant.carlosmunoz.pokedex.presentation.pokedex.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.globant.carlosmunoz.pokedex.R
import com.globant.carlosmunoz.pokedex.databinding.FragmentPokedexBinding
import com.globant.carlosmunoz.pokedex.entities.Pokemon
import com.globant.carlosmunoz.pokedex.presentation.pokedex.adapter.PokedexItemAdapter


class PokedexFragment : Fragment() {

    private lateinit var binding: FragmentPokedexBinding
    private lateinit var rowAdapter: PokedexItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPokedexBinding.inflate(inflater, container, false)
        setupAdapter()
        setupRecycler()
        return binding.root
    }

    private fun setupAdapter() {
        rowAdapter = PokedexItemAdapter(listOf(Pokemon("Caca", "adasdad"))){ pokemon ->
            Toast.makeText(requireContext(), "${pokemon.name}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecycler() {
    binding.recyclerView.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = rowAdapter
    }

    }

}