package com.devmob.pokedex.repository.api.model

import com.google.gson.annotations.SerializedName

/**
 *
 * Entidade de modelo primária da aplicação que armazena as informações recebidas da API.
 *
 * */
class PokemonEntity {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("types")
    var types = ArrayList<Types>()

    @SerializedName("sprites")
    var sprite: Sprite = Sprite()

    @SerializedName("stats")
    var stats = ArrayList<Stats>()

    override fun toString(): String {
        return id.toString()
    }
}

/**
 *
 * Classe para fazer a desestruturação do Tipo de pokemon
 *
 */
class Types {
    @SerializedName("type")
    var type = Type()
}

/**
 *
 * Tipo do pokemon
 *
 */
class Type {
    @SerializedName("name")
    var name: String = ""
}

/**
 *
 * Classe para fazer a desestruturação do elemento de imagem do pokemon
 *
 */
class Sprite {
    @SerializedName("other")
    var other: Other = Other()
}

/**
 *
 * Classe para fazer a desestruturação do elemento de imagem do pokemon
 *
 */
class Other {
    @SerializedName("dream_world")
    var dreamWorld : DreamWorld = DreamWorld()
}

/**
 *
 * Recurso de imagem do pokemon.
 *
 */
class DreamWorld {
    @SerializedName("front_default")
    var img: String = ""
}

/**
 *
 * Status do pokemon.
 *
 */
class Stats {
    @SerializedName("base_stat")
    var base_stat = ""
}