package com.stackmobile.teste2.view.cliente

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.stackmobile.teste2.R
import com.stackmobile.teste2.databinding.ActivityAtualizarCadastroBinding

class atualizar_cadastro : AppCompatActivity() {

    private lateinit var binding: ActivityAtualizarCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtualizarCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            val voltar = Intent(this, Tela_cliente::class.java)
            startActivity(voltar)
            finish()
        }
    }
}