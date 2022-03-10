package com.danielpasser.rickandmorty.ui.characterslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danielpasser.rickandmorty.R
import com.danielpasser.rickandmorty.model.Character
import com.danielpasser.rickandmorty.model.Response
import com.danielpasser.rickandmorty.ui.adapter.CharactersAdapter
import com.danielpasser.rickandmorty.ui.adapter.CharactersAdapterPaging
import com.danielpasser.rickandmorty.ui.decorator.ItemDecorator
import com.danielpasser.rickandmorty.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    private val viewModel: CharactersListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    private val charactersAdapterPaging = CharactersAdapterPaging(
        onClick = {
            onTaskClick(it)
        },
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_characters_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupAdapter(view)


    }


    private fun setupAdapter(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_movies)

        recyclerView.apply {

            layoutManager = LinearLayoutManager(view.context)
            addItemDecoration(ItemDecorator(10))
            adapter = charactersAdapterPaging
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                charactersFlow.collectLatest { charactersAdapterPaging.submitData(it)
                }
            }
            errorMessage.observe(viewLifecycleOwner){displayError(it)}
        }

    }

    private fun displayError(message: String?) {
        if (message == null)
            Toast.makeText(context, "Unknown Error", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }

    private fun onTaskClick(character: Character) {
        val nav = CharactersListFragmentDirections.actionCharactersListFragmentToCharacterFragment(
            character
        )
        findNavController().navigate(nav)
    }
}