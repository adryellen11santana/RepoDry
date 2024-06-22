package com.stackmobile.teste2.view.avaliacoes

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.stackmobile.teste2.R
import com.stackmobile.teste2.databinding.ActivityAvaliacoesBinding
import com.stackmobile.teste2.databinding.ActivityTelaEmpresaBinding
import com.stackmobile.teste2.view.empresa.Tela_empresa
import com.stackmobile.teste2.view.empresa.empresa_visualizar_servico
import com.stackmobile.teste2.view.pesquisar.pesquisar

class avaliacoes : AppCompatActivity() {
    private lateinit var binding: ActivityAvaliacoesBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvaliacoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            val voltar = Intent(this, empresa_visualizar_servico::class.java)
            startActivity(voltar)
            finish()
        }

        binding.avaliacao.rating = 0.0F

        binding.avaliacao.setOnRatingBarChangeListener { ratingBar, rating, fromUser -> rating
            binding.estrelas.text = "" + rating
        }

        binding.btnEnviar.setOnClickListener {
            db.collection("Clientes").document()
                .update(
                    mapOf(
                        "Estrelas" to binding.avaliacao.rating,
                        "Comentario" to binding.editComentario.text.toString()
                    )
                )
                .addOnSuccessListener { Log.d(ContentValues.TAG, "Avaliação salva com sucesso!")
                    binding.estrelas.text = ""
                    binding.avaliacao.rating = 0.0F
                    binding.editComentario.setText("")

                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "ERRO", e) }
        }
    }
}
