package com.stackmobile.teste2.view.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.stackmobile.teste2.databinding.ActivityHomeBinding
import com.stackmobile.teste2.view.cliente.Tela_cliente
import com.stackmobile.teste2.view.cliente.cadastro_servico
import com.stackmobile.teste2.view.empresa.Tela_empresa
import com.stackmobile.teste2.view.formlogin.form_login


class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val voltarHome = Intent(this, form_login::class.java)
            startActivity(voltarHome)
            finish()
        }

        binding.btnEmpresa.setOnClickListener {
            val empresa = Intent(this, Tela_empresa::class.java)
            startActivity(empresa)
            finish()
        }

        binding.btnCliente.setOnClickListener {
            val cliente = Intent(this, Tela_cliente::class.java)
            startActivity(cliente)
            finish()
        }
    }
}