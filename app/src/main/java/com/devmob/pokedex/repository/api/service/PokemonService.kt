package com.devmob.pokedex.repository.api.service

import com.devmob.pokedex.repository.api.model.ListPokemonEntity
import com.devmob.pokedex.repository.api.model.PokemonEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    /**
     *
     * Endpoint da API que busca um unico recurso dado um identificador.
     *
     */
    @GET("pokemon/{pokemonId}")
    fun getSinglePokemon(@Path("pokemonId")pokemonId: String): Call<PokemonEntity>

    /**
     *
     * Endpoint da API que busca uma lista de recursos.
     *
     */
    @GET("pokemon?limit=500&offset=0")
    fun getListOfPokemon(): Call<ListPokemonEntity>
}