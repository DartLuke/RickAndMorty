package com.danielpasser.rickandmorty.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danielpasser.rickandmorty.R
import com.danielpasser.rickandmorty.model.Character

class CharactersAdapterPaging(private val onClick: (Character) -> Unit)
    : PagingDataAdapter<Character, CharactersAdapterPaging.CharacterViewHolderPaging>(CharacterComparator) {


    inner class CharacterViewHolderPaging(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: Character) {
            itemView.apply {
                findViewById<TextView>(R.id.text_view_character_name).text = character.name
                Glide.with(context).load(character.image)
                    .into(findViewById(R.id.image_view))
                setOnClickListener { onClick.invoke(character) }
            }
        }
    }

    object CharacterComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem

    }

    override fun onBindViewHolder(holder: CharacterViewHolderPaging, position: Int){
        getItem(position)?.let { holder.bind(it) }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    = CharacterViewHolderPaging(  LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false))
}
