package com.devmob.pokedex.ui.viewHolder

import android.app.Activity
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.devmob.pokedex.databinding.PokemonLineBinding
import com.devmob.pokedex.repository.api.model.PokemonEntity
import com.devmob.pokedex.ui.listener.OnPokemonListener

class ListPokemonViewHolder(private val binding: PokemonLineBinding, private val listener: OnPokemonListener) : RecyclerView.ViewHolder(binding.root) {

    /**
     *
     * Popula os elementos individuas dentro da recycler view.
     *
     * @param pokemon Instância do modelo PokemonEntity
     */
    fun bindVH(pokemon: PokemonEntity){
        binding.pokemonName.text = pokemon.name.capitalize()

        var types = ""
        for (item in pokemon.types){
            types += item.type.name.capitalize() + " "
        }
        binding.pokemonType.text = types

        SvgLoader.pluck()
            .with(binding.root.context as Activity)
            .load(pokemon.sprite.other.dreamWorld.img, binding.pokemonImage)
        Log.d("TAG", pokemon.sprite.other.dreamWorld.img)

        binding.pokemonItem.setOnClickListener{
            listener.onCLick(pokemon)
        }
    }

}