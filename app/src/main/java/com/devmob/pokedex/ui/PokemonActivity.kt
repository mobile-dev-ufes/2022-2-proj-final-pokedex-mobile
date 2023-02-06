package com.devmob.pokedex.ui

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.ahmadrosid.svgloader.SvgLoader
import com.devmob.pokedex.R
import com.devmob.pokedex.databinding.ActivityPokemonBinding

class PokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonBinding

    private lateinit var pokemonName: String
    private lateinit var pokemonId: String
    private lateinit var pokemonType: List<String>
    private lateinit var pokemonHp: String
    private lateinit var pokemonAttack: String
    private lateinit var pokemonDefense: String
    private lateinit var pokemonSpAttack: String
    private lateinit var pokemonSpDef: String
    private lateinit var pokemonSpeed: String
    private lateinit var pokemonImgUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getArgs()
        insertData()

    }

    /**
     *
     *
     * Faz a leitura dos elementos do bundle.
     *
     */
    private fun getArgs() {
        pokemonName = intent.extras!!.getString("name").toString()
        pokemonId = intent.extras!!.getString("id").toString()
        pokemonType = intent.extras!!.getString("type").toString().split(" ")
        pokemonHp = intent.extras!!.getString("hp").toString()
        pokemonAttack = intent.extras!!.getString("attack").toString()
        pokemonDefense = intent.extras!!.getString("defense").toString()
        pokemonSpAttack = intent.extras!!.getString("sp_attack").toString()
        pokemonSpDef = intent.extras!!.getString("sp_def").toString()
        pokemonSpeed = intent.extras!!.getString("speed").toString()
        pokemonImgUrl = intent.extras!!.getString("img_url").toString()
    }

    /**
     *
     *
     * Popula os elementos da tela com as infromações lidas do bundle.
     *
     * */
    private fun insertData() {
        binding.singlePokemonBackground.background = returnColor(pokemonType[0])
        binding.singlePokemonId.text = pokemonId
        binding.singlePokemonType1.text = pokemonType[0]
        binding.singlePokemonType1.background = returnColor(pokemonType[0])
        if (pokemonType.size > 1) {
            binding.singlePokemonType2.text = pokemonType[1]
            binding.singlePokemonType2.background = returnColor(pokemonType[1])
        } else {
            binding.singlePokemonType2.visibility = View.INVISIBLE
        }
        binding.singlePokemonHp.text = pokemonHp
        binding.singlePokemonAttack.text = pokemonAttack
        binding.singlePokemonDefense.text = pokemonDefense
        binding.singlePokemonSpAttack.text = pokemonSpAttack
        binding.singlePokemonSpDef.text = pokemonSpDef
        binding.singlePokemonSpeed.text = pokemonSpeed
        SvgLoader.pluck()
            .with(binding.root.context as Activity)
            .load(pokemonImgUrl, binding.singlePokemonImage)
    }

    /**
     *
     * Retorna a cor tema do pokemon selecionado.
     *
     * @param type o tipo do pokemon
     *
     * */
    private fun returnColor(type: String): Drawable? {
        return when (type) {
            "Normal" -> ResourcesCompat.getDrawable(resources, R.color.Normal, null)
            "Fighting" -> ResourcesCompat.getDrawable(resources, R.color.Fighting, null)
            "Flying" -> ResourcesCompat.getDrawable(resources, R.color.Flying, null)
            "Poison" -> ResourcesCompat.getDrawable(resources, R.color.Poison, null)
            "Ground" -> ResourcesCompat.getDrawable(resources, R.color.Ground, null)
            "Rock" -> ResourcesCompat.getDrawable(resources, R.color.Rock, null)
            "Bug" -> ResourcesCompat.getDrawable(resources, R.color.Bug, null)
            "Ghost" -> ResourcesCompat.getDrawable(resources, R.color.Ghost, null)
            "Steel" -> ResourcesCompat.getDrawable(resources, R.color.Steel, null)
            "Fire" -> ResourcesCompat.getDrawable(resources, R.color.Fire, null)
            "Water" -> ResourcesCompat.getDrawable(resources, R.color.Water, null)
            "Grass" -> ResourcesCompat.getDrawable(resources, R.color.Grass, null)
            "Electric" -> ResourcesCompat.getDrawable(resources, R.color.Electric, null)
            "Psychic" -> ResourcesCompat.getDrawable(resources, R.color.Psychic, null)
            "Ice" -> ResourcesCompat.getDrawable(resources, R.color.Ice, null)
            "Dragon" -> ResourcesCompat.getDrawable(resources, R.color.Dragon, null)
            "Dark" -> ResourcesCompat.getDrawable(resources, R.color.Dark, null)
            "Fairy" -> ResourcesCompat.getDrawable(resources, R.color.Fairy, null)
            else -> {
                ResourcesCompat.getDrawable(resources, R.color.white, null)
            }
        }
    }
}