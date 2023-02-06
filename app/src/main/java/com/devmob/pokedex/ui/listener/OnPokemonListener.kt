package com.devmob.pokedex.ui.listener

import com.devmob.pokedex.repository.api.model.PokemonEntity

interface OnPokemonListener {
    fun onCLick(p: PokemonEntity)
}