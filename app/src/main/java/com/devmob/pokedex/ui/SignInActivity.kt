package com.devmob.pokedex.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devmob.pokedex.R
import com.devmob.pokedex.databinding.ActivitySignInBinding
import com.devmob.pokedex.viewModel.SignInViewModel
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var signInVM: SignInViewModel
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        checkIfUserIsLogged()

        signInVM = ViewModelProvider(this)[SignInViewModel::class.java]
        setObserver()

        binding.button.setOnClickListener(this)
        binding.textView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button) {
            val email = binding.email.text.toString()
            val pass = binding.pass.text.toString()

            signInVM.loginUser(firebaseAuth, email, pass)
        } else if (view.id == R.id.textView) {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     *
     * Observador que checa se o cadastro foi realizado com sucesso ou não. Se foi, redireciona para a página de login.
     *
     */
    private fun setObserver() {
        signInVM.loginMessage().observe(this, Observer {
            if (it == "true") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    /**
     *
     * Checa se ja existe uma instância de um usuário logado pelo firebase. Se ja existir, pula a tela de login
     *
     */
    fun checkIfUserIsLogged() {
        if (firebaseAuth.currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}