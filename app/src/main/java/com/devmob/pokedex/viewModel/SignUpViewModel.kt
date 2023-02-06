package com.devmob.pokedex.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devmob.pokedex.R
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    private var register = MutableLiveData<String>()

    /**
     *
     * Retorna a mensagem de registro do usuário quando a mesma for modificada.
     *
     */
    fun registerMessage(): LiveData<String> {
        return register
    }

    /**
     *
     * Faz as validações para o cadastro de um usuário pelo firebase.
     *
     * @param firebaseAuth agente de autorização do firebase
     * @param email email do usuário
     * @param pass senha fornecida pelo usuário
     * @param confirmPass confirmação da senha do usuário
     *
     */
    fun registerUser(firebaseAuth: FirebaseAuth, email: String, pass: String, confirmPass: String) {
        if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
            if (pass == confirmPass) {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        register.value = it.isSuccessful.toString()
                        Toast.makeText(
                            getApplication(),
                            getApplication<Application>().resources.getString(R.string.register_successful),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.addOnFailureListener {
                    when (it) {
                        is FirebaseAuthWeakPasswordException -> {
                            register.value = getApplication<Application>().resources.getString(R.string.weak_password)
                        }
                        is FirebaseAuthInvalidCredentialsException -> {
                            register.value = getApplication<Application>().resources.getString(R.string.invalid_email)
                        }
                        is FirebaseAuthUserCollisionException -> {
                            register.value = getApplication<Application>().resources.getString(R.string.unavailable_email)
                        }
                        is FirebaseNetworkException -> {
                            register.value = getApplication<Application>().resources.getString(R.string.network_error)
                        }
                        else -> {
                            register.value = getApplication<Application>().resources.getString(R.string.firebase_error_sign_up)
                        }
                    }
                }
            } else {
                register.value = getApplication<Application>().resources.getString(R.string.different_passwords)
            }
        } else {
            register.value = getApplication<Application>().resources.getString(R.string.empty_fields)
        }
    }
}