package com.devmob.pokedex.repository.api.model

import com.google.gson.annotations.SerializedName

/**
 *
 * Modelo para armazenar a lista de recursos recebida da API.
 *
 */
class ListPokemonEntity {
    @SerializedName("results")
    var result: ArrayList<Pokemon> = ArrayList()
}

/**
 *
 * Elemento da lista de recursos que esta sendo recebido.
 *
 */
class Pokemon {
    @SerializedName("name")
    var name: String = ""
}