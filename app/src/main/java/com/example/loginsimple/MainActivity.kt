package com.example.loginsimple

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val validator = LoginValidator() // Usar tu clase de validación

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val txtUser = findViewById<EditText>(R.id.txtUser)
        val txtPass = findViewById<EditText>(R.id.txtPass) // ¡Agregar esta línea!

        btnLogin.setOnClickListener {
            val usuario = txtUser.text.toString()
            val password = txtPass.text.toString()

            if (validator.validarCredenciales(usuario, password)) {
                Toast.makeText(this, "✅ Login exitoso: $usuario", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "❌ Usuario o contraseña inválidos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}