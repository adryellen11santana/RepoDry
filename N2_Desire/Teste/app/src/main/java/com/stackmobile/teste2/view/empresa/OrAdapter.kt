package com.stackmobile.teste2.view.empresa

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.stackmobile.teste2.R

class OrAdapter(private val ordemList: ArrayList<Ordem>) : RecyclerView.Adapter<OrAdapter.OrAdapter>() {

    private val db = FirebaseFirestore.getInstance()

    inner class OrAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //aqui esta sendo setado os campos pegos da activit
        var email: TextView
        var nome: TextView
        var servico: TextView
        var telefone: TextView
        var status: TextView
        var btn_aceitar: Button
        var btn_recusar: Button

        init {
            email = itemView.findViewById(R.id.email)
            nome = itemView.findViewById(R.id.nome)
            servico = itemView.findViewById(R.id.servico)
            telefone = itemView.findViewById(R.id.telefone)
            status = itemView.findViewById(R.id.status)
            btn_aceitar = itemView.findViewById(R.id.btn_aceitar)
            btn_aceitar.setOnClickListener {
                //aceitar()
                val statusRef = db.collection("Clientes").document("Status")

                statusRef
                    .update("Status", "Em Andamento")
                    .addOnSuccessListener { Log.d(TAG, "Atualizado com sucesso!") }
                    .addOnFailureListener { e -> Log.w(TAG, "ERRO ao atualizar", e) }

            }
            btn_recusar = itemView.findViewById(R.id.btn_recusar)
            btn_recusar.setOnClickListener {
               // recusar()
                val statusRef = db.collection("Clientes").document("Status")

                statusRef
                    .update("Status", "Recusado")
                    .addOnSuccessListener { Log.d(TAG, "Atualizado com sucesso!") }
                    .addOnFailureListener { e -> Log.w(TAG, "ERRO ao atualizar", e) }
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrAdapter {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_lista, parent, false)
        return OrAdapter(itemView)
    }

    override fun getItemCount(): Int {
        return ordemList.size
    }

    override fun onBindViewHolder(holder: OrAdapter, position: Int) {
        holder.email.text = ordemList[position].Email
        holder.nome.text = ordemList[position].Nome
        holder.servico.text = ordemList[position].Servico
        holder.telefone.text = ordemList[position].Telefone
        holder.status.text = ordemList[position].Status
    }

}