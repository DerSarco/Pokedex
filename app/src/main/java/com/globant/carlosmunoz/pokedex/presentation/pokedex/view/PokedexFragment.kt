package com.globant.carlosmunoz.pokedex.presentation.pokedex.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.globant.carlosmunoz.pokedex.data.api.ApiConnection
import com.globant.carlosmunoz.pokedex.data.repositories.PokemonListRepository
import com.globant.carlosmunoz.pokedex.databinding.FragmentPokedexBinding
import com.globant.carlosmunoz.pokedex.presentation.pokedex.adapter.PokedexItemAdapter


class PokedexFragment : Fragment() {

    private lateinit var binding: FragmentPokedexBinding
    private lateinit var rowAdapter: PokedexItemAdapter

    //Logic

    private lateinit var viewModel: PokemonListViewModel
    private lateinit var repository: PokemonListRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPokedexBinding.inflate(inflater, container, false)
        setupRepository()
        setupViewModel()
        setupAdapter()
        setupObserver()
        setupRecycler()
        return binding.root
    }

    private fun setupObserver() {
        viewModel.pokemonListFetched.observe(viewLifecycleOwner) {
            rowAdapter.setNewData(it.results)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            PokemonListViewModelFactory(repository)
        )[PokemonListViewModel::class.java]
        viewModel.getPokemonList()
    }

    private fun setupRepository() {
        repository = PokemonListRepository(ApiConnection)
    }

    private fun setupAdapter() {
        rowAdapter =
            PokedexItemAdapter(listOf()) { pokemon ->
                val action =
                    PokedexFragmentDirections.actionPokedexFragmentToPokemonDetailFragment(pokemon.name, pokemon.id!!.toLong())
                requireView().findNavController().navigate(action)
            }
    }

    private fun setupRecycler() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rowAdapter
        }

    }

}