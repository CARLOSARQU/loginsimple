package com.example.loginsimple

import org.junit.Test
import org.junit.Assert.*
class LoginValidatorTest {
    private val validator = LoginValidator()

    @Test
    fun `cuando el usuario esta vacio, el login debe fallar`() {
        val usuario= ""
        val pass = "123456"

        val resultado = validator.validarCredenciales(usuario, pass)

        assertFalse("El login deberia fallar si el login esta vacio", resultado)
    }

    @Test
    fun `cuando la clave es muy corta debe fallar`() {
        val resultado = validator.validarCredenciales("admin", "12")
        assertFalse("La clave no debe pasar", resultado)
    }
}