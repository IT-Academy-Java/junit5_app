package org.competidor.junitapp.demo.models;

import org.competidor.junitapp.demo.exceptions.InsufficientMoneyException;
import org.competidor.junitapp.demo.model.Account;
import org.competidor.junitapp.demo.model.Bank;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
  @Test
  void nameAccountTest() {
    Account account = new Account("Elenita", new BigDecimal("1000.12344"));

    String expected = "Elenita";
    String real = account.getPerson();

    assertNotNull(real, () -> "The account can't be null");
    assertEquals(expected, real, () -> "The account name is not what was expected");
    assertTrue(real.equals("Elenita"), () -> "The account name can be the same to the real: " + real);
  }

  @Test
  void accountBalanceTest() {
    Account account = new Account("Elenita", new BigDecimal("1000.12344"));
    assertNotNull(account.getBalance());
    assertEquals(1000.12344, account.getBalance().doubleValue());

    assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
  }

  @Test
  void testAccountReference() {
    Account account1 = new Account("John Doe", new BigDecimal("8900.9997"));
    Account account2 = new Account("John Doe", new BigDecimal("8900.9997"));

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
    Exception exception = assertThrows(InsufficientMoneyException.class, () -> {
      account.debit(new BigDecimal(1500));
    });

    String actual = exception.getMessage();
    String expected = "Insufficient money";

    assertEquals(expected, actual);
  }

  @Test
  void transferMoneyToOtherAccountTest() {
    Account account1 = new Account("John Doe", new BigDecimal("2500"));
    Account account2 = new Account("Jane Doe", new BigDecimal("1500.8989"));

    Bank bank = new Bank();
    bank.setName("Demo Bank");
    bank.transferMoney(account2, account1, new BigDecimal(500));

    assertEquals("1000.8989", account2.getBalance().toPlainString());
    assertEquals("3000", account1.getBalance().toPlainString());
  }

  @Test
  void bankAndAccountRelationshipTest() {
    Account account1 = new Account("John Doe", new BigDecimal("2500"));
    Account account2 = new Account("Jane Doe", new BigDecimal("1500.8989"));

    Bank bank = new Bank();
    bank.addAccount(account1);
    bank.addAccount(account2);

    bank.setName("Demo Bank");
    bank.transferMoney(account2, account1, new BigDecimal(500));

    assertAll(
      () -> {
        assertEquals("1000.8989", account2.getBalance().toPlainString());
      },
      () -> {
        assertEquals("3000", account1.getBalance().toPlainString());
      },
      () -> {
        assertEquals(2, bank.getAccountList().size());
      },
      () -> {
        assertEquals("Demo Bank", account1.getBank().getName());
      },
      () -> {
        assertEquals("Jane Doe", bank.getAccountList()
          .stream()
          .filter(c -> c.getPerson().equals("Jane Doe"))
          .findFirst()
          .get()
          .getPerson()
        );
      },
      () -> {
        assertTrue(
          bank
            .getAccountList()
            .stream()
            .anyMatch(c -> c.getPerson().equals("Jane Doe"))
        );
      }
    );


  }

}