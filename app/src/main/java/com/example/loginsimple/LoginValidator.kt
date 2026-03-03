package com.example.loginsimple

class LoginValidator {
    fun validarCredenciales(user: String, pass: String): Boolean {
        // Regla de negocio 1: No pueden estar vacíos
        if (user.isBlank() || pass.isBlank()) return false

        // Regla de negocio 2: Deben tener más de 3 caracteres
        return user.length > 3 && pass.length > 3
    }
}