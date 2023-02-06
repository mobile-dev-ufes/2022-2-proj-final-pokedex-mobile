package com.devmob.pokedex.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devmob.pokedex.R
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class SignInViewModel(application: Application) : AndroidViewModel(application) {
    private var login = MutableLiveData<String>()

    /**
     *
     * Retorna a mensagem de login para exibir ao usuário quando a mesma for modificada.
     *
     */
    fun loginMessage(): LiveData<String> {
        return login
    }

    /**
     *
     * Faz as validações para o login de um usuário pelo firebase.
     *
     * @param firebaseAuth agente de autorização do firebase
     * @param email email do usuário
     * @param pass senha fornecida pelo usuário
     *
     */
    fun loginUser(firebaseAuth: FirebaseAuth, email: String, pass: String) {
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    login.value = it.isSuccessful.toString()
                }
            }.addOnFailureListener {
                when (it) {
                    is FirebaseAuthInvalidUserException -> {
                        login.value = getApplication<Application>().resources.getString(R.string.invalid_credencials)
                    }
                    is FirebaseAuthInvalidCredentialsException -> {
                        login.value = getApplication<Application>().resources.getString(R.string.invalid_credencials)
                    }
                    is FirebaseNetworkException -> {
                        login.value = getApplication<Application>().resources.getString(R.string.network_error)
                    }
                    else -> {
                        login.value = getApplication<Application>().resources.getString(R.string.firebase_error_sign_in)
                    }
                }
            }
        } else {
            login.value = getApplication<Application>().resources.getString(R.string.empty_fields)
        }
    }
}