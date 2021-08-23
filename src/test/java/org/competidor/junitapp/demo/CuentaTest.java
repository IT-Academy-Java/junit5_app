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

        assertNotNull(cuenta.getSaldo());
        assertEquals(esperado, real);
        assertTrue(real.equals("Elenita"));
    }

    @Test
    void testSaldoCuenta() {
        Cuenta cuenta = new Cuenta("Elenita", new BigDecimal("1000.12344") );
        assertNotNull(cuenta.getSaldo());
        assertEquals(1000.12344, cuenta.getSaldo().doubleValue());

        // assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testReferenciaCuenta() {
        Cuenta cuenta1 = new Cuenta("John Doe", new BigDecimal("8900.9997") );
        Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal("8900.9997") );

        //assertNotEquals(cuenta2, cuenta1);
        assertEquals(cuenta2, cuenta1);
    }

    @Test
    void testDebidoCuenta() {
        Cuenta cuenta = new Cuenta("Elena", new BigDecimal("1000.12345"));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testCreditoCuenta() {
        Cuenta cuenta = new Cuenta("Elena", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
    }
}