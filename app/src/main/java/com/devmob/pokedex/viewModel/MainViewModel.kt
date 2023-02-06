package com.devmob.pokedex.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devmob.pokedex.repository.api.client.ClientRetrofit
import com.devmob.pokedex.repository.api.model.ListPokemonEntity
import com.devmob.pokedex.repository.api.model.PokemonEntity
import com.devmob.pokedex.repository.api.service.PokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var size = 0
    private var actualSize = MutableLiveData<Int>()
    private var pokeList = MutableLiveData<MutableList<PokemonEntity>>()
    private var auxList = mutableListOf<PokemonEntity>()

    /**
     *
     * Retorna o tamanho da lista de pokemons quando a mesma for modificada.
     *
     */
    fun actualAuxSize(): MutableLiveData<Int> {
        return actualSize
    }

    /**
     *
     * Ordena os pokemons por ID e modifica o elemento liveData que notifica o observador na activity
     *
     * */
    fun updatePokeList() {
        auxList.sortBy { it.id }
        pokeList.value = auxList
    }

    /**
     *
     * Retorna a lista de pokemons quando a mesma for modificada.
     *
     */
    fun getPokeList(): LiveData<MutableList<PokemonEntity>> {
        return pokeList
    }

    /**
     *
     * Busca um recurso na api e o adiciona a lista de elementos a serem exibidos no recycler view.
     *
     * @param id o identificador que será usado para buscar o recurso na api.
     *
     */
    private fun requestNewPokemon(id: String) {
        val apiPokemonService = ClientRetrofit.createService(PokemonService::class.java)
        val pokemon: Call<PokemonEntity> = apiPokemonService.getSinglePokemon(id.trim())
        pokemon.enqueue(object : Callback<PokemonEntity> {
            override fun onResponse(
                call: Call<PokemonEntity>,
                response: Response<PokemonEntity>
            ) {
                auxList.add(response.body()!!)
                actualSize.value = auxList.count()
            }

            override fun onFailure(call: Call<PokemonEntity>, t: Throwable) {
            }
        })
    }

    /**
     *
     * Pede para a api uma lista de pokemons e chama a função requestNewPokemon uma vez para cada pokemon que foi retornado.
     *
     */
    fun requestListPokemon() {
        val apiPokemonService = ClientRetrofit.createService(PokemonService::class.java)
        val listPoke: Call<ListPokemonEntity> = apiPokemonService.getListOfPokemon()
        listPoke.enqueue(object : Callback<ListPokemonEntity> {
            override fun onResponse(
                call: Call<ListPokemonEntity>,
                response: Response<ListPokemonEntity>
            ) {
                size = response.body()!!.result.size
                for (i in 1..size) {
                    requestNewPokemon(i.toString())
                }
            }

            override fun onFailure(call: Call<ListPokemonEntity>, t: Throwable) {
                Toast.makeText(
                    getApplication(),
                    "Falha ao buscar os pokemons",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}