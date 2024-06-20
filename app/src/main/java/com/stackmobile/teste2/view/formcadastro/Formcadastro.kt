package com.stackmobile.teste2.view.formcadastro

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.Gravity.TOP
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.stackmobile.teste2.databinding.ActivityFormCadastroBinding
import com.stackmobile.teste2.view.cliente.Tela_cliente
import com.stackmobile.teste2.view.empresa.Tela_empresa
import com.stackmobile.teste2.view.formlogin.form_login
import com.stackmobile.teste2.view.home.Home

class Formcadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCadastrar.setOnClickListener { view ->

            val nome = binding.editNome.text.toString()
            val telefone = binding.editTelefone.text.toString()
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                val snackbar =
                    Snackbar.make(view, "Preencha todos os campos!", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else {
                auth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener { cadastro ->
                        if (cadastro.isSuccessful) {
                            login()
                            val snackbar = Snackbar.make(
                                view,
                                "Cadastro realizado com sucesso!",
                                Snackbar.LENGTH_SHORT
                            )
                            snackbar.setBackgroundTint(Color.BLUE)
                            snackbar.show()
                            binding.editNome.setText("")
                            binding.editTelefone.setText("")
                            binding.editEmail.setText("")
                            binding.editSenha.setText("")
                        }

                    }.addOnFailureListener { exception ->
                    val mensagemErro = when (exception) {
                        is FirebaseAuthWeakPasswordException -> "A senha deve conter no mínimo 6 caracteres!"
                        is FirebaseAuthInvalidCredentialsException -> "Digite um e-mail válido!"
                        is FirebaseAuthUserCollisionException -> "Conta já cadastrada!"
                        is FirebaseNetworkException -> "Sem conexão com a internet!"

                        else -> "Erro ao cadastrar usuário!"
                    }

                    val snackbar = Snackbar.make(view, mensagemErro, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()
                }
            }
        }
    }
    private fun login() {
        val intent = Intent(this, form_login::class.java)
        startActivity(intent)
    }

}