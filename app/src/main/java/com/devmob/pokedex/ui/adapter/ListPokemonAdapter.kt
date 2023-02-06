package com.devmob.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devmob.pokedex.databinding.PokemonLineBinding
import com.devmob.pokedex.repository.api.model.PokemonEntity
import com.devmob.pokedex.ui.listener.OnPokemonListener
import com.devmob.pokedex.ui.viewHolder.ListPokemonViewHolder

class ListPokemonAdapter: RecyclerView.Adapter<ListPokemonViewHolder>() {

    private var pokeList: List<PokemonEntity> = listOf()
    private lateinit var listener: OnPokemonListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPokemonViewHolder {
        val item = PokemonLineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListPokemonViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: ListPokemonViewHolder, position: Int) {
        holder.bindVH(pokeList[position])
    }

    override fun getItemCount(): Int {
        return  pokeList.count()
    }

    fun updatePokeList(list: List<PokemonEntity>) {
        pokeList = list
        notifyDataSetChanged()
    }

    fun setListener(pokemonListener: OnPokemonListener) {
        listener = pokemonListener
    }
}