package org.competidor.junitapp.demo;

import org.competidor.junitapp.demo.exceptions.InsufficientMoneyException;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    private String person;
    private BigDecimal balance;

    public Account() {
    }

    public Account(BigDecimal balance) {
        this.balance = balance;
    }

    public Account(String person, BigDecimal balance) {
        this.person = person;
        this.balance = balance;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void debit(BigDecimal monto){
        BigDecimal newBalance = this.balance = this.balance.subtract(monto);
        if(newBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new InsufficientMoneyException("Insufficient money");
        }
    }

    public void credit(BigDecimal monto){
        this.balance = this.balance.add(monto);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Account)){
            return false;
        }

        Account account = (Account) obj;
        if(this.person == null || this.balance == null ){
            return false;
        }
        return this.person.equals(account.getPerson()) && this.balance.equals(account.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, balance);
    }

}
