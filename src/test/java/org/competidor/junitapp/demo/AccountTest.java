package org.competidor.junitapp.demo;

import org.competidor.junitapp.demo.exceptions.InsufficientMoneyException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void nameAccountTest(){
        // First you have to instantiate the class to test
        Account account = new Account("Elenita", new BigDecimal("1000.12344"));

        String expected = "Elenita";
        String real = account.getPerson();

        assertNotNull(account.getBalance());
        assertEquals(expected, real);
        assertTrue(real.equals("Elenita"));
    }

    @Test
    void accountBalanceTest() {
        Account account = new Account("Elenita", new BigDecimal("1000.12344") );
        assertNotNull(account.getBalance());
        assertEquals(1000.12344, account.getBalance().doubleValue());

        assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testAccountReference() {
        Account account1 = new Account("John Doe", new BigDecimal("8900.9997") );
        Account account2 = new Account("John Doe", new BigDecimal("8900.9997") );

        assertEquals(account2, account1);
    }

    @Test
    void debitAccountTest() {
        Account account = new Account("Elena", new BigDecimal("1000.12345"));
        account.debit(new BigDecimal(100));
        assertNotNull(account.getBalance());
        assertEquals(900, account.getBalance().intValue());
        assertEquals("900.12345", account.getBalance().toPlainString());
    }

    @Test
    void accountCreditTest() {
        Account account = new Account("Elena", new BigDecimal("1000.12345"));
        account.credit(new BigDecimal(100));
        assertNotNull(account.getBalance());
        assertEquals(1100, account.getBalance().intValue());
        assertEquals("1100.12345", account.getBalance().toPlainString());
    }

    @Test
    void insufficientMoneyExceptionTest() {
        Account account = new Account("Elena", new BigDecimal("1000.12345"));
        Exception exception = assertThrows( InsufficientMoneyException.class, ()-> {
            account.debit(new BigDecimal(1500));
        } );

        String actual = exception.getMessage();
        String expected = "Insufficient money";

        assertEquals(expected, actual);
    }
}