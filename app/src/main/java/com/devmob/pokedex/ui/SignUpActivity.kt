package com.devmob.pokedex.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devmob.pokedex.R
import com.devmob.pokedex.databinding.ActivitySignUpBinding
import com.devmob.pokedex.viewModel.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var signUpVM: SignUpViewModel
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        signUpVM = ViewModelProvider(this)[SignUpViewModel::class.java]
        setObserver()

        binding.textView.setOnClickListener(this)
        binding.button.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button) {
            val email = binding.email.text.toString()
            val pass = binding.pass.text.toString()
            val confirmPass = binding.confirmPass.text.toString()

            signUpVM.registerUser(firebaseAuth, email, pass, confirmPass)
        } else if (view.id == R.id.textView) {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     *
     * Cria o observador para identificar o se o usuário foi cadastrado ou não. Se foi redireciona para a tela de login.
     *
     */
    private fun setObserver() {
        signUpVM.registerMessage().observe(this, Observer {
            if (it == "true") {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

}