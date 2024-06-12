package com.stackmobile.teste2.view.formlogin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.stackmobile.teste2.R
import com.stackmobile.teste2.databinding.ActivityRecuperarSenhaBinding

class recuperar_senha : AppCompatActivity() {


    private lateinit var emailEditText: EditText
    private lateinit var resetPasswordButton: Button
    private var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_senha)

        emailEditText = findViewById(R.id.edit_email_recuperar)
        resetPasswordButton = findViewById(R.id.btn_enviar)
        auth = FirebaseAuth.getInstance()

        resetPasswordButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            if (email.isEmpty()) {
                emailEditText.error = "Por favor, insira seu e-mail"
                emailEditText.requestFocus()
                //return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "E-mail de redefinição enviado", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(
                            this,
                            "Erro ao enviar e-mail de redefinição",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("ResetPassword", "Error: ${task.exception?.message}")

                    }
                }
        }
    }

}
