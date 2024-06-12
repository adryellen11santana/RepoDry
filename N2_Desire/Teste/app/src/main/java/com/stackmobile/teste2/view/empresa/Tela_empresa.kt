package com.stackmobile.teste2.view.empresa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.stackmobile.teste2.R
import com.stackmobile.teste2.databinding.ActivityTelaClienteBinding
import com.stackmobile.teste2.databinding.ActivityTelaEmpresaBinding
import com.stackmobile.teste2.view.formlogin.form_login
import com.stackmobile.teste2.view.home.Home


class Tela_empresa : AppCompatActivity() {
    private  lateinit var recyclerView: RecyclerView
    private lateinit var ordemList: ArrayList<Ordem>
    lateinit var binding: ActivityTelaEmpresaBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTelaEmpresaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            val voltarHome = Intent(this, Home::class.java)
            startActivity(voltarHome)
            finish()
        }

        binding.btnBuscar.setOnClickListener{
            recyclerView = binding.recyclerview
            recyclerView.layoutManager = LinearLayoutManager(this)

            ordemList = arrayListOf()

            db.collection("Clientes").get()
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
                        recyclerView.adapter = OrAdapter(ordemList)
                    }
                }
                .addOnFailureListener{
                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                }
        }
    }
}