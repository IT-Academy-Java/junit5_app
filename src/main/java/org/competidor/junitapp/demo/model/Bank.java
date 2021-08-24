package org.competidor.junitapp.demo.model;

import java.math.BigDecimal;
import java.util.List;

public class Bank {

  private String name;
  List<Account> accountList;

  public Bank() {
  }

  public Bank(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Account> getAccountList() {
    return accountList;
  }

  public void setAccountList(List<Account> accountList) {
    this.accountList = accountList;
  }

  public void addAccount(Account account){
    accountList.add(account);
  }

  public void transferMoney(Account accountOrigin, Account destinationAccount, BigDecimal moneyToTransfer){
    accountOrigin.debit(moneyToTransfer);
    destinationAccount.credit(moneyToTransfer);
  }
}
