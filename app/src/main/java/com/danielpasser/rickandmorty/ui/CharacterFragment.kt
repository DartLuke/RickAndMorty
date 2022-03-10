package com.danielpasser.rickandmorty.ui

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

import com.danielpasser.rickandmorty.R
import com.danielpasser.rickandmorty.model.Character


class CharacterFragment : Fragment() {

    //private val safeArgs: UserDetailsFragmentArgs by navArgs()
    private val safeArgs: CharacterFragmentArgs by navArgs()
    private val character: Character by lazy { safeArgs.character }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_character, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            findViewById<TextView>(R.id.name).text = character.name
            findViewById<TextView>(R.id.gender).text = character.gender
            findViewById<TextView>(R.id.species).text = character.species
            findViewById<TextView>(R.id.status).text = character.status
            findViewById<TextView>(R.id.type).text = character.type
            findViewById<TextView>(R.id.origin).text = character.origin?.name ?: ""
            findViewById<TextView>(R.id.location).text = character.location?.name ?: ""
            Glide.with(context).load(character.image)
                .into(findViewById(R.id.image_view))
        }
    }
}