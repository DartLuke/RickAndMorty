package com.danielpasser.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danielpasser.rickandmorty.R
import com.danielpasser.rickandmorty.model.Character

class CharactersAdapter(private val onClick: (Character) -> Unit) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {
    private val characters: ArrayList<Character> = arrayListOf()

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: Character) {
            itemView.apply {
                findViewById<TextView>(R.id.text_view_character_name).text = character.name
                Glide.with(context).load(character.image)
                    .into(findViewById(R.id.image_view))
                setOnClickListener{onClick.invoke(character)}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        )


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    fun updateData(_characters: List<Character>) {
        characters.apply {
            clear()
            addAll(_characters)
        }
        notifyDataSetChanged()

    }

    override fun getItemCount() = characters.size

}