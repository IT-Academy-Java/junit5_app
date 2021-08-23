package org.competidor.junitapp.demo;

import java.math.BigDecimal;
import java.util.Objects;

public class Cuenta {

    private String persona;
    private BigDecimal saldo;

    public Cuenta() {
    }

    public Cuenta(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Cuenta(String persona, BigDecimal saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null){
            return false;
        }

        Cuenta cuenta = (Cuenta) o;
        if(this.persona == null || this.saldo == null ){
            return false;
        }
        return this.persona.equals(cuenta.getPersona()) && this.saldo.equals(cuenta.getSaldo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(persona, saldo);
    }
}
