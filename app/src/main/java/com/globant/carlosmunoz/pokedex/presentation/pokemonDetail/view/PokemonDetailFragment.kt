package com.globant.carlosmunoz.pokedex.presentation.pokemonDetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.globant.carlosmunoz.pokedex.R
import com.globant.carlosmunoz.pokedex.data.api.ApiConnection
import com.globant.carlosmunoz.pokedex.data.repositories.PokemonDetailRepository
import com.globant.carlosmunoz.pokedex.databinding.FragmentPokemonDetailBinding


class PokemonDetailFragment : Fragment() {

    private val args: PokemonDetailFragmentArgs by navArgs()


    //Logic
    private lateinit var viewModel: PokemonDetailViewModel
    private lateinit var repository: PokemonDetailRepository

    private lateinit var binding: FragmentPokemonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        setupRepository()
        setupViewModel()
        setupObserver()
        return binding.root
    }

    private fun setupObserver() {
        viewModel.pokemonDetailFetched.observe(viewLifecycleOwner){
            binding.tvURL.text = "${it.baseExperience} + ${it.order} ${it.height}"
        }
    }

    private fun setupRepository() {
        repository = PokemonDetailRepository(ApiConnection)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            PokemonDetailViewModelFactory(repository)
        )[PokemonDetailViewModel::class.java]
        viewModel.getPokemonDetail(args.pokemonName)
    }

}