package com.stackmobile.teste2.view.formlogin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.stackmobile.teste2.R
import com.stackmobile.teste2.databinding.ActivityFormLoginBinding
import com.stackmobile.teste2.view.cliente.Tela_cliente
import com.stackmobile.teste2.view.empresa.Tela_empresa
import com.stackmobile.teste2.view.formcadastro.Formcadastro
import com.stackmobile.teste2.view.home.Home

class form_login : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener { view ->

            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {

                val snackbar =
                    Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else{
                auth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener { autenticacao ->
                        if (autenticacao.isSuccessful) {
                            if (email.toString() == "empresa@empresa.com") {
                                empresa()
                                binding.editEmail.setText("")
                                binding.editSenha.setText("")
                            }else{
                                cliente()
                            }
                        }
                    }.addOnFailureListener {
                        val snackbar =
                            Snackbar.make(view, "ERRO ao realizar o login", Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.RED)
                        snackbar.show()
                    }
            }
        }

        binding.btnEsqueceuSenha.setOnClickListener {
            val intent = Intent(this, recuperar_senha::class.java)
            startActivity(intent)
        }


        binding.txtTelaCadastro.setOnClickListener {
            val intent = Intent(this, Formcadastro::class.java)
            startActivity(intent)
        }

    }
    override fun onStart() {
        super.onStart()
        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null) {

        }
    }
    private fun empresa() {
        val intent = Intent(this, Tela_empresa::class.java)
        startActivity(intent)
    }

    private fun cliente() {
        val intent = Intent(this, Tela_cliente::class.java)
        startActivity(intent)
    }

}
