package com.stackmobile.teste2.view.cliente

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.stackmobile.teste2.databinding.ActivityCadastroServicoBinding
import com.stackmobile.teste2.databinding.ActivityTelaClienteBinding
import com.stackmobile.teste2.view.formlogin.form_login

class cadastro_servico : AppCompatActivity() {

    lateinit var binding: ActivityCadastroServicoBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroServicoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener {

            val clientesMap = hashMapOf(
                "Nome" to binding.editNome.text.toString(),
                "Telefone" to binding.editTelefone.text.toString(),
                "Email" to binding.editEmailCliente.text.toString(),
                "Senha" to binding.editSenhaCliente.text.toString(),
                "Servico" to binding.editServico.text.toString(),
                "Status" to "Aguardando",
                "Valor" to ""
            )

            db.collection("Clientes").document()
                .set(clientesMap).addOnCompleteListener{
                    Log.d("db", "Dados salvos com sucesso!")

                    val snackbar = Snackbar.make(
                        binding.root,
                        "Dados salvos com sucesso!",
                        Snackbar.LENGTH_SHORT
                    ).also {
                        it.setBackgroundTint(Color.BLUE)
                        it.show()
                    }
                    binding.editNome.setText("")
                    binding.editTelefone.setText("")
                    binding.editServico.setText("")
                    binding.editEmailCliente.setText("")
                    binding.editSenhaCliente.setText("")
                    binding.editValor.setText("")
                    binding.editStatus.setText("")

                }.addOnFailureListener{

                }
        }
        binding.btnVoltar.setOnClickListener {
            val voltar = Intent(this, Tela_cliente::class.java)
            startActivity(voltar)
            finish()
        }
    }
}