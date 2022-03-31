package com.globant.carlosmunoz.pokedex.presentation.pokemonDetail.view

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.ListViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globant.carlosmunoz.pokedex.R
import com.globant.carlosmunoz.pokedex.data.api.ApiConnection
import com.globant.carlosmunoz.pokedex.data.repositories.PokemonDetailRepository
import com.globant.carlosmunoz.pokedex.databinding.FragmentPokemonDetailBinding
import com.globant.carlosmunoz.pokedex.domain.entities.PokemonDetail
import com.globant.carlosmunoz.pokedex.domain.utils.loadCoil
import com.globant.carlosmunoz.pokedex.presentation.pokemonDetail.adapter.PokemonAbilitiesAdapter


class PokemonDetailFragment : Fragment() {
    private val TAG: String = "PokemonDetailFragment"


    private val args: PokemonDetailFragmentArgs by navArgs()

    //Logic
    private lateinit var viewModel: PokemonDetailViewModel
    private lateinit var repository: PokemonDetailRepository
    private lateinit var binding: FragmentPokemonDetailBinding

    private lateinit var mAbilityAdapter: PokemonAbilitiesAdapter


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
        viewModel.pokemonDetailFetched.observe(viewLifecycleOwner) {
            Log.d(TAG, it.toString())
            setupUI(it)
        }
    }

    private fun setupUI(pokemonDetail: PokemonDetail) {
        with(binding) {
            ivPokemonDetailPhoto.loadCoil(args.pokemonID)
            tvPokemonName.text = args.pokemonName.replaceFirstChar { it.uppercaseChar() }
            tvBaseExperience.text = pokemonDetail.baseExperience.toString()
            tvWeight.text = pokemonDetail.weight.toString()
            tvHeight.text = pokemonDetail.height.toString()
            btnAbilities.setOnClickListener {
                openAbilitiesDialog(pokemonDetail)
            }
        }

    }

    private fun openAbilitiesDialog(pokemonDetail: PokemonDetail) {
        val abilitiesArray: MutableList<String> = mutableListOf()
        pokemonDetail.abilities.forEach {
            abilitiesArray.add(it.ability.name.replaceFirstChar { char -> char.uppercaseChar() })
        }

        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_recycler_view)
        mAbilityAdapter = PokemonAbilitiesAdapter(abilitiesArray)
        with(dialog) {
            val tvTitle = findViewById<TextView>(R.id.tvDialogTitle)
            val btnClose = findViewById<Button>(R.id.btnCloseDialog)
            val recyclerView = findViewById<RecyclerView>(R.id.dialogRecyclerView)
            val linearLayoutManager = object : LinearLayoutManager(requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = mAbilityAdapter
            recyclerView.isNestedScrollingEnabled = false
            btnClose.setOnClickListener {
                dismiss()
            }
            //applyting text to the title with also
            "${pokemonDetail.name.replaceFirstChar { it.uppercaseChar() }} Abilities".also {
                tvTitle.text = it
            }
            show()
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