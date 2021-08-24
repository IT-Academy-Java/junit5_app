package org.competidor.junitapp.demo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {

  private String name;
  List<Account> accountList;

  public Bank() {
    this.accountList = new ArrayList<Account>();
  }

  public Bank(String name) {
    this.name = name;
    this.accountList = new ArrayList<Account>();
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
    account.setBank(this);
  }

  public void transferMoney(Account accountOrigin, Account destinationAccount, BigDecimal moneyToTransfer){
    accountOrigin.debit(moneyToTransfer);
    destinationAccount.credit(moneyToTransfer);
  }
}
