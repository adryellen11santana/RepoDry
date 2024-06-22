package com.stackmobile.teste2.view.empresa

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.stackmobile.teste2.R
import com.stackmobile.teste2.databinding.ActivityTelaClienteBinding
import com.stackmobile.teste2.databinding.ActivityTelaEmpresaBinding
import com.stackmobile.teste2.view.cliente.cadastro_servico
import com.stackmobile.teste2.view.formlogin.form_login
import com.stackmobile.teste2.view.home.Home
import com.stackmobile.teste2.view.pesquisar.pesquisar

class Tela_empresa : AppCompatActivity() {

        private lateinit var binding: ActivityTelaEmpresaBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityTelaEmpresaBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.btnSair.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                val voltarHome = Intent(this, form_login::class.java)
                startActivity(voltarHome)
                finish()
            }

            binding.btnBuscar.setOnClickListener {
                val empresa = Intent(this, empresa_visualizar_servico::class.java)
                startActivity(empresa)
                finish()
            }

            binding.btnPesquisar.setOnClickListener {
                val buscar = Intent(this, pesquisar::class.java)
                startActivity(buscar)
                finish()
            }
        }
    }
