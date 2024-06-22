package com.stackmobile.teste2.view.pesquisar

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.stackmobile.teste2.R
import com.stackmobile.teste2.databinding.ActivityPesquisarBinding
import com.stackmobile.teste2.databinding.ActivityTelaEmpresaBinding
import com.stackmobile.teste2.view.avaliacoes.avaliacoes
import com.stackmobile.teste2.view.empresa.Tela_empresa

class pesquisar : AppCompatActivity() {
    private lateinit var binding: ActivityPesquisarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesquisarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            val voltar = Intent(this, Tela_empresa::class.java)
            startActivity(voltar)
            finish()
        }

    }
}