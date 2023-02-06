package com.devmob.pokedex.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devmob.pokedex.R
import com.devmob.pokedex.databinding.ActivityMainBinding
import com.devmob.pokedex.repository.api.model.PokemonEntity
import com.devmob.pokedex.ui.adapter.ListPokemonAdapter
import com.devmob.pokedex.ui.listener.OnPokemonListener
import com.devmob.pokedex.viewModel.MainViewModel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainVM: MainViewModel
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    private val adapter = ListPokemonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.recyclerListPokemon.layoutManager = LinearLayoutManager(this)
        binding.recyclerListPokemon.adapter = adapter

        mainVM = ViewModelProvider(this)[MainViewModel::class.java]

        val listener = object : OnPokemonListener {
            override fun onCLick(p: PokemonEntity) {
                setPokemon(p)
            }
        }

        adapter.setListener(listener)

        mainVM.requestListPokemon()

        setObserver()
        binding.logOut.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.log_out) {
            firebaseAuth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     *
     * Cria o observador para modificar o recycler view na tela.
     *
     */
    fun setObserver() {
        mainVM.getPokeList().observe(this, Observer {
            adapter.updatePokeList(it)
        })
        mainVM.actualAuxSize().observe(this, Observer {
            if (it == mainVM.size) {
                mainVM.updatePokeList()
            }
        })
    }

    /**
     *
     * Cria um bundle com as informações do pokemon e inicia a activity de exibição do pokemon.
     *
     */
    fun setPokemon(p: PokemonEntity) {
        var types = ""
        for (item in 0 until p.types.size){
            if (item != 0) types += " "
            types += p.types[item].type.name.capitalize()
        }

        val bundle = bundleOf(
            "name" to p.name,
            "id" to p.id.toString(),
            "type" to types,
            "hp" to p.stats[0].base_stat,
            "attack" to p.stats[1].base_stat,
            "defense" to p.stats[2].base_stat,
            "sp_attack" to p.stats[3].base_stat,
            "sp_def" to p.stats[4].base_stat,
            "speed" to p.stats[5].base_stat,
            "img_url" to p.sprite.other.dreamWorld.img
        )

        val intent = Intent(this, PokemonActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}