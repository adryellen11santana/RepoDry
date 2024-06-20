package com.stackmobile.teste2.view.empresa

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.stackmobile.teste2.databinding.ActivityEmpresaVisualizarServicoBinding


class empresa_visualizar_servico : AppCompatActivity() {
    private  lateinit var recyclerView: RecyclerView
    private lateinit var ordemList: ArrayList<Ordem>
    lateinit var binding: ActivityEmpresaVisualizarServicoBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmpresaVisualizarServicoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            val voltarHome = Intent(this, Tela_empresa::class.java)
            startActivity(voltarHome)
            finish()
        }

        binding.btnBuscar.setOnClickListener{
            recyclerView = binding.recyclerview
            recyclerView.layoutManager = LinearLayoutManager(this)

            ordemList = arrayListOf()

            db.collection("Clientes")
                .get()
                .addOnSuccessListener {
                    //verifica se achou algo no banco
                    if(!it.isEmpty){
                        //se achou, pega cada item e joga no data
                        for(data in it.documents){
                            val ordem:Ordem? = data.toObject(Ordem::class.java)
                            // se não for null, adiciona na lista
                            if(ordem !=null){
                                ordemList.add(ordem)
                            }
                        }
                        recyclerView.adapter = OrAdapter_Empresa(ordemList, this)
                    }
                }
                .addOnFailureListener{
                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                }
        }
    }
}