/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author a297pate
 */
public class BankAccount {
    private Double Balance;

    public BankAccount() {
        Balance=100.00;
    }
    
    public BankAccount(double Balance) {
        this.Balance=Balance;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double Balance) {
        this.Balance = Balance;
    }
    
    
    
}
