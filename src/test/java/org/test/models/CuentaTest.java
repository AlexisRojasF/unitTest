package org.test.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {


    @Test
    void testNombreCuenta() {
        Cuenta cuenta = new Cuenta("Alexis", new BigDecimal("1000.12345"));

        String esperado = "Alexis";
        String real = cuenta.getPersona();

        assertEquals(esperado,real);
        assertTrue(real.equals("Alexis"));
    }

    @Test
    void testSaldoCuenta() {
        Cuenta cuenta = new Cuenta("Alexis", new BigDecimal("1000.12345"));
        assertEquals(1000.12345,cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testReferenciaCuenta() {

        Cuenta cuenta = new Cuenta("Jhon Doe", new BigDecimal("8900.997"));
        Cuenta cuenta2 = new Cuenta("Jhon Doe", new BigDecimal("8900.997"));

//        assertNotEquals(cuenta2,cuenta);
        assertEquals(cuenta2,cuenta);
    }
}