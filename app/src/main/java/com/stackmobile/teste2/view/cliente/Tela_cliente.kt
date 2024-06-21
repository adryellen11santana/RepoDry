package com.stackmobile.teste2.view.cliente

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.stackmobile.teste2.databinding.ActivityTelaClienteBinding
import com.stackmobile.teste2.view.empresa.empresa_visualizar_servico
import com.stackmobile.teste2.view.formlogin.form_login
import com.stackmobile.teste2.view.home.Home

class Tela_cliente : AppCompatActivity() {

    private lateinit var binding: ActivityTelaClienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val voltarHome = Intent(this, form_login::class.java)
            startActivity(voltarHome)
            finish()
        }

        binding.btnAcompServico.setOnClickListener {
            val empresa = Intent(this, cliente_visualizar_servico::class.java)
            startActivity(empresa)
            finish()
        }

        binding.btnCadServico.setOnClickListener {
            val cliente = Intent(this, cadastro_servico::class.java)
            startActivity(cliente)
            finish()
        }

        binding.btnAtualizar.setOnClickListener {
            val atualizar = Intent(this, atualizar_cadastro::class.java)
            startActivity(atualizar)
            finish()
        }
    }
}
