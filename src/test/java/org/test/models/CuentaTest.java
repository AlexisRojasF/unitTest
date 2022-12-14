package org.test.models;

import org.junit.jupiter.api.Test;
import org.test.exceptions.DineroInsuficienteException;

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

    @Test
    void testDebitoCuenta() {
        Cuenta cuenta = new Cuenta("Alexis", new BigDecimal("1000.12345"));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900,cuenta.getSaldo().intValue());
        assertEquals("900.12345",cuenta.getSaldo().toPlainString());
    }

    @Test
    void testCreditoCuenta() {
        Cuenta cuenta = new Cuenta("Alexis", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100,cuenta.getSaldo().intValue());
        assertEquals("1100.12345",cuenta.getSaldo().toPlainString());
    }

    @Test
    void TestDineroInsuficienteException() {
        Cuenta cuenta = new Cuenta("Alexis", new BigDecimal("1000.12345"));
        Exception exception = assertThrows(DineroInsuficienteException.class,()->{
            cuenta.debito(new BigDecimal(1500));
        });

        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(esperado,actual);
    }

    @Test
    void testTranferirDinero() {
        Cuenta cuenta1 = new Cuenta("Alexis", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Andres", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.setNombre("Bancolombia");
        banco.tranferir(cuenta2,cuenta1,new BigDecimal(500));
        assertEquals("1000.8989",cuenta2.getSaldo().toPlainString());
        assertEquals("3000",cuenta1.getSaldo().toPlainString());
    }
}