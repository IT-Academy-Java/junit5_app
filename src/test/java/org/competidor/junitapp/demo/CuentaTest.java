package org.competidor.junitapp.demo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


class CuentaTest {
    @Test
    void testNameCuenta(){
        // primero instanciar la clase a probar
        Cuenta cuenta = new Cuenta("Elenita", new BigDecimal("1000.12344"));

        String esperado = "Elenita";
        String real = cuenta.getPersona();

        assertEquals(esperado, real);
        assertTrue(real.equals("Elenita"));
    }

    @Test
    void testSaldoCuenta() {
        Cuenta cuenta = new Cuenta("Elenita", new BigDecimal("1000.12344") );
        assertEquals(1000.12344, cuenta.getSaldo().doubleValue());

        // assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testReferenciaCuenta() {
        Cuenta cuenta1 = new Cuenta("Elenita", new BigDecimal("1000.12344") );
        Cuenta cuenta2 = new Cuenta("Elenita", new BigDecimal("1000.12344") );

        //assertNotEquals(cuenta2, cuenta1);
        assertEquals(cuenta2, cuenta1);
    }
}